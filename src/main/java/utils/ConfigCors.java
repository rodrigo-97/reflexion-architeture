package utils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class ConfigCors extends HttpServlet {
    public static void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
    }
}
