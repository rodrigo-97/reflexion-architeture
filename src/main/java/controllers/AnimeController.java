package controllers;

import DAO.PersistData;
import annotations.Controller;
import com.google.gson.Gson;
import models.Anime;
import models.TableData;
import reflection.ResourceReflection;

@Controller(name = "anime") // O nome deve ser o mesmo nome da anotation @Table
public class AnimeController {
    public String getAll (){
        return new Gson().toJson(new PersistData(new Anime()).getData());
    }

    public String getById (Integer id){
        return null;
    }

    public boolean delete (Integer id){
//        if (new PersistData(id).deele()){
//            return true;
//        }else{
//            return false;
//        }
        return false;
    }

    public boolean insert (Object model){
        if (new PersistData((TableData) model).insert()){
            return true;
        }else{
            return false;
        }
    }

    public String update (Object model){
        return null;
    }
}
