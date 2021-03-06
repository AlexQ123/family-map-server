package handler;

import service.result.AllEventsResult;
import service.AllEventsService;

import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;
import com.google.gson.Gson;

public class AllEventsHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                Headers reqHeaders = exchange.getRequestHeaders();
                if (reqHeaders.containsKey("Authorization")) {
                    String authtoken = reqHeaders.getFirst("Authorization");

                    AllEventsService service = new AllEventsService();
                    AllEventsResult result = service.allEvents(authtoken);

                    if (result.isSuccess()) {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    }
                    else {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    }

                    Gson gson = new Gson();
                    GSONHelper gsonHelper = new GSONHelper();

                    OutputStream resBody = exchange.getResponseBody();
                    String respData = gson.toJson(result);
                    gsonHelper.writeString(respData, resBody);
                    resBody.close();

                    success = true;
                }
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
