package resource;

import annotations.ApiRest;
import annotations.Methods;
import controllers.AnimeController;

// TODO: 10/01/2021 Fazer esta classse virar api, n sabemos como, mas tem q ser feito kk xS
@ApiRest(baseURL = "http://localhost:3000")
public class AnimeResource extends CrudMethods{
    private AnimeController animeController = new AnimeController();

    @Override
    @Methods.Get(path = "/animes", type = "all")
    public String getALl() {
        return animeController.getAll();
    }

    @Override
    @Methods.Get(path = "/anime/:id", type = "id")
    public String getById() {
        return animeController.getById(1);
    }

    @Override
    @Methods.Post(path = "/anime")
    public String insert() {
        return animeController.insert(new Object());
    }

    @Override
    @Methods.Put(path = "/anime")
    public String update() {
        return animeController.update(new Object());
    }

    @Override
    @Methods.Delete(path = "/anime/:id")
    public String delete() {
        return animeController.delete(2);
    }
}
