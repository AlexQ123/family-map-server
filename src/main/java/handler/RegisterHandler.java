package handler;

import service.request.RegisterRequest;
import service.result.RegisterResult;
import service.RegisterService;

import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;
import com.google.gson.Gson;

public class RegisterHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                Gson gson = new Gson();
                GSONHelper gsonHelper = new GSONHelper();

                // opening input stream, getting request body, deserializing json string
                InputStream reqBody = exchange.getRequestBody();
                String reqData = gsonHelper.readString(reqBody);
                RegisterRequest request = (RegisterRequest)gson.fromJson(reqData, RegisterRequest.class);

                // performing the service
                RegisterService service = new RegisterService();
                RegisterResult result = service.register(request);

                // sending status code and any defined headers
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                // opening output stream, serializing the result and writing to resBody
                OutputStream resBody = exchange.getResponseBody();
                String respData = gson.toJson(result);
                gsonHelper.writeString(respData, resBody);
                resBody.close();

                success = true;
            }
            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                exchange.getResponseBody().close();
            }
        }
        catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }

}
