package resource;

import annotations.Methods;
import controllers.CharacterController;

// TODO: 10/01/2021 Fazer neste pacote a parte da api onde o cliente irá se conectar
public class CharacterResource extends CrudMethods{
    private CharacterController characterController = new CharacterController();

    @Override
    @Methods.Get(path = "/characters", type = "all")
    public String getALl() {
        return characterController.getAll();
    }

    @Override
    @Methods.Get(path = "/character/:id", type = "id")
    public String getById() {
        return characterController.getById(1);
    }

    @Override
    @Methods.Post(path = "/character")
    public String insert() {
        return characterController.insert(new Object());
    }

    @Override
    @Methods.Put(path = "/character")
    public String update() {
        return characterController.update(new Object());
    }

    @Override
    @Methods.Delete(path = "/character/:id")
    public String delete() {
        return characterController.delete(2);
    }
}
