package handler;

import service.request.LoadRequest;
import service.result.LoadResult;
import service.LoadService;

import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;
import com.google.gson.Gson;

public class LoadHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                Gson gson = new Gson();
                GSONHelper gsonHelper = new GSONHelper();

                InputStream reqBody = exchange.getRequestBody();
                String reqData = gsonHelper.readString(reqBody);
                LoadRequest request = (LoadRequest)gson.fromJson(reqData, LoadRequest.class);

                LoadService service = new LoadService();
                LoadResult result = service.load(request);

                if (result.isSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                }
                else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }

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
