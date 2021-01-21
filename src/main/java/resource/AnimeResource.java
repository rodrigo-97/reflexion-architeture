package resource;

import DAO.PersistData;
import controllers.AnimeController;
import models.Anime;
import com.google.gson.Gson;
import reflection.ReflectionTable;
import utils.ConfigCors;
import utils.Response;
import utils.ResponseObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "anime", value = "/animes")
public class AnimeResource extends HttpServlet {
    private AnimeController animeController = new AnimeController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            ConfigCors.setAccessControlHeaders(response);
            PrintWriter out = response.getWriter();

            out.println(new Gson().toJson(new PersistData(new Anime()).getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected void doPost (HttpServletRequest request, HttpServletResponse response) {
        try {
            ConfigCors.setAccessControlHeaders(response);
            PrintWriter out = response.getWriter();

            Anime anime = new Anime();
            ReflectionTable.setValueField(anime, "name", request.getParameter("name"));
            ReflectionTable.setValueField(anime, "numEpisodes", Integer.parseInt(request.getParameter("numEpisodes")));

            System.out.println(anime);
            new PersistData(anime).insert();
            out.println(new Gson().toJson(new Response("Deu tudo certo")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            ConfigCors.setAccessControlHeaders(response);
            PrintWriter out = response.getWriter();
            Anime anime = new Anime();
            ReflectionTable.setValueField(anime, "id", Integer.parseInt(request.getParameter("id")));
            ReflectionTable.setValueField(anime, "name", request.getParameter("name"));
            ReflectionTable.setValueField(anime, "numEpisodes", Integer.parseInt(request.getParameter("numEpisodes")));
            System.out.println("put");
            out.println("ok");
            System.out.println(anime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            ConfigCors.setAccessControlHeaders(response);
            PrintWriter out = response.getWriter();
            System.out.println(request.getParameter("id"));
            out.println("okj");
            System.out.println("delete");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ConfigCors.setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
