package com.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 9099; // Port for the web server
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new RootHandler());
        server.setExecutor(null); // default executor
        System.out.println("Server running on http://localhost:" + port);
        server.start();
    }

    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String html = "<!DOCTYPE html>" +
                    "<html><head><meta charset='UTF-8'><title>Fortify SCA Helper" +
                    "</title></head><body>" +
                    "<h1>Fortify SCA Language Helper</h1>" +
                    "<label for='lang'>Choose language:</label>" +
                    "<select id='lang'>" +
                    "<option value=''>--Select--</option>" +
                    "<option value='java'>Java</option>" +
                    "<option value='python'>Python</option>" +
                    "<option value='javascript'>JavaScript</option>" +
                    "<option value='csharp'>C#</option>" +
                    "</select>" +
                    "<pre id='info'></pre>" +
                    "<script>" +
                    "const info=document.getElementById('info');" +
                    "document.getElementById('lang').addEventListener('change',function(){" +
                    "let data={};switch(this.value){" +
                    "case 'java':data.ext='.java';data.cmd='sourceanalyzer -b myproj javac *.java';break;" +
                    "case 'python':data.ext='.py';data.cmd='sourceanalyzer -b myproj python *.py';break;" +
                    "case 'javascript':data.ext='.js';data.cmd='sourceanalyzer -b myproj node *.js';break;" +
                    "case 'csharp':data.ext='.cs';data.cmd='sourceanalyzer -b myproj csc *.cs';break;" +
                    "default:info.textContent='';return;}" +
                    "info.textContent='Target extension: '+data.ext+'\nFortify command: '+data.cmd;" +
                    "});" +
                    "</script></body></html>";
            byte[] response = html.getBytes("UTF-8");
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response);
            }
        }
    }
}
