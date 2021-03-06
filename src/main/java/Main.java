import DAO.PersistData;
import DAO.SQLGenerator;
import controllers.AnimeController;
import models.Anime;
import models.Character;
import models.TableData;
import reflection.ReflectionTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SplittableRandom;

public class Main {
    public static void main(String[] args) {
        Anime anime = new Anime();
        Character character = new Character();
        AnimeController animeController = new AnimeController();

        anime.setNumEpisodes(23);
        anime.setName("alalala");
        anime.setId(1);

        System.out.println("--- Anime data ---");
        System.out.println(anime.getTableName());
        System.out.println(anime.getFields());
        System.out.println(anime.getPks());
        System.out.println(anime.getFks());
        System.out.println(anime.getTypeFields());

        System.out.println("\r\n--- Character data ---");
        System.out.println(character.getTableName());
        System.out.println(character.getFields());
        System.out.println(character.getPks());

        System.out.println(character.getFks());
        System.out.println(character.getTypeFields());

        System.out.println(animeController.getAll());
        System.out.println("_______________________");
        PersistData psd = null;
        ReflectionTable.setValueField(anime,"name","");
        ReflectionTable.setValueField(anime,"numEpisodes",null);
        Anime anime2 = new Anime();
        // System.out.println(psd.getData());
        psd =  new PersistData(anime2);
        ReflectionTable.setValueField(anime2,"id",null);
        ReflectionTable.setValueField(anime2,"name","danganronpa");
        ReflectionTable.setValueField(anime2,"numEpisodes",20);
        psd =  new PersistData(anime2);
        System.out.println(anime2);
        psd.insert();
        System.out.println(psd.getData());
        ReflectionTable.setValueField(anime2,"id",1);
        ReflectionTable.setValueField(anime2,"name","some nome");
        ReflectionTable.setValueField(anime2,"numEpisodes",2000);
        psd =  new PersistData(anime2);
        psd.update();
        System.out.println(psd.getData());
        psd.delete();
        System.out.println(psd.getData());
    }
}
