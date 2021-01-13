package controllers;

import annotations.Controller;
import reflection.ResourceReflection;

@Controller(name = "anime") // O nome deve ser o mesmo nome da anotation @Table
public class AnimeController {
    public String getAll (){
        return ResourceReflection.get(this);
    }

    public String getById (Integer id){
        return null;
    }

    public String delete (Integer id){
        return null;
    }

    public String insert (Object model){
        return null;
    }

    public String update (Object model){
        return null;
    }
}
