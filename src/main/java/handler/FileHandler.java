package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.*;

public class FileHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                String urlPath = exchange.getRequestURI().toString();
                if (urlPath == null || urlPath.equals("/") || urlPath.equals("/index.html")) {
                    urlPath = "/index.html";
                }
                else if (urlPath.equals("/css/main.css")) {
                    urlPath = "/css/main.css";
                }
                else if (urlPath.equals("/favicon.ico")) {
                    urlPath = "/favicon.ico";
                }
                else {
                    urlPath = "/HTML/404.html";
                }
                String filePath = "web" + urlPath;
                File file = new File(filePath);
                OutputStream respBody = exchange.getResponseBody();

                if (filePath.equals("web/HTML/404.html")) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                }
                else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                }

                Files.copy(file.toPath(), respBody);

                exchange.close();
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
