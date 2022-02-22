package handler;

import service.result.FillResult;
import service.FillService;

import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;
import com.google.gson.Gson;

public class FillHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                Gson gson = new Gson();
                GSONHelper gsonHelper = new GSONHelper();

                String username;
                int generations;

                String urlPath = exchange.getRequestURI().toString();
                String toParse = urlPath.substring(6);
                if (toParse.contains("/")) {
                    String[] parameters = toParse.split("/");
                    username = parameters[0];
                    generations = Integer.parseInt(parameters[1]);
                }
                else {
                    username = toParse;
                    generations = 4;
                }
                FillService service = new FillService();
                FillResult result = service.fill(username, generations);

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
