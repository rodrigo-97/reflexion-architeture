package resource;

import annotations.Methods;
import com.google.gson.Gson;
import controllers.AnimeController;
import models.Anime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "anime", value = "/animes")
public class AnimeResource extends HttpServlet {
    private AnimeController animeController = new AnimeController();

    @Methods.Get(path = "/animes", type = "all")
    public String getALl() {
        return animeController.getAll();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Anime anime = new Anime();
        anime.setId(1);
        anime.setName("attack on titan");
        anime.setNumEpisodes(12);

        Gson re = new Gson();


        String res = re.toJson(anime);

        response.setContentType("Application/Json");
        PrintWriter out = response.getWriter();


        String json = "{\"name\": \"rodrigo\", idade: {}}";


        out.println(res);
    }

    @Methods.Get(path = "/anime/:id", type = "id")
    public String getById() {
        return animeController.getById(1);
    }

    @Methods.Post(path = "/anime")
    public String insert() {
        return animeController.insert(new Object());
    }

    @Methods.Put(path = "/anime")
    public String update() {
        return animeController.update(new Object());
    }

    @Methods.Delete(path = "/anime/:id")
    public String delete() {
        return animeController.delete(2);
    }
}
