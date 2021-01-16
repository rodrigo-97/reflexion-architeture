package resource;

import com.google.gson.Gson;
import models.Character;
import reflection.ReflectionTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

// TODO: 10/01/2021 Fazer neste pacote a parte da api onde o cliente irá se conectar
@WebServlet(name = "character", value = "/characters")
public class CharacterResource extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        // find animes
        // animeController.findAll();

        try {
            // check if id were passed in URI path
            String requestParam;
            String responseContent;
            requestParam = request.getParameter("id");
            if(requestParam == null){
                // find all anime data
                //responseContent = "Não há nada aqui";
            }else {
                // find anime by pk
                //responseContent = "Há algo aqui " + requestParam;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost (HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        Character character = new Character();
        ReflectionTable.setValueField(character, "name", request.getParameter("name"));
        ReflectionTable.setValueField(character, "age", Integer.parseInt(request.getParameter("age")));
        ReflectionTable.setValueField(character, "animeId", Integer.parseInt(request.getParameter("animeId")));

        System.out.println(character);
        try {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            ArrayList<String> res = new ArrayList<>();
            res.add("message");
            res.add("ok");
            out.println(new Gson().toJson(res));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Character character = new Character();
        ReflectionTable.setValueField(character, "name", request.getParameter("name"));
        ReflectionTable.setValueField(character, "age", Integer.parseInt(request.getParameter("age")));
        ReflectionTable.setValueField(character, "animeId", Integer.parseInt(request.getParameter("animeId")));
        ReflectionTable.setValueField(character, "id", Integer.parseInt(request.getParameter("id")));

        System.out.println(character);
        try {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            ArrayList<String> res = new ArrayList<>();
            res.add("message");
            res.add("ok");
            out.println(new Gson().toJson(res));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        System.out.println(request.getParameter("id"));
    }
}
