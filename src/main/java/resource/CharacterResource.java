package resource;

import DAO.PersistData;
import com.google.gson.Gson;
import controllers.AnimeController;
import models.Anime;
import models.Character;
import reflection.ReflectionTable;
import utils.ConfigCors;
import utils.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "character", value = "/characters")
public class CharacterResource extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            ConfigCors.setAccessControlHeaders(response);
            PrintWriter out = response.getWriter();

            out.println(new Gson().toJson(new PersistData(new Character()).getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost (HttpServletRequest request, HttpServletResponse response) {
        try {
            ConfigCors.setAccessControlHeaders(response);
            PrintWriter out = response.getWriter();

            Character character = new Character();
            ReflectionTable.setValueField(character, "name", request.getParameter("name"));
            ReflectionTable.setValueField(character, "age", Integer.parseInt(request.getParameter("age")));
            ReflectionTable.setValueField(character, "animeId", Integer.parseInt(request.getParameter("animeId")));

            System.out.println(character);
            new AnimeController().insert(character);
            out.println(new Gson().toJson(new Response("Deu tudo certo")));
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

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ConfigCors.setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
