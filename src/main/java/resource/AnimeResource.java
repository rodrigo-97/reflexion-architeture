package resource;

import controllers.AnimeController;
import models.Anime;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "anime", value = "/animes")
public class AnimeResource extends HttpServlet {
    private AnimeController animeController = new AnimeController();

    public String getALl() {
        return animeController.getAll();
    }

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

            Anime anime = new Anime();
            anime.setId(1);
            anime.setName("attack on titan");
            anime.setNumEpisodes(12);

             //transform into JSON string
            Gson gson = new Gson();
            responseContent = gson.toJson(anime);

            // set headers and return response
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println(responseContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void doPost (HttpServletRequest request, HttpServletResponse response) {
        try {

        }catch (Exception exception){

        }
    }
}
