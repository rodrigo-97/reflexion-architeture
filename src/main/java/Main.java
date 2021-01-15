import DAO.SQLGenerator;
import controllers.AnimeController;
import models.Anime;
import models.Character;
import reflection.ReflectionTable;

public class Main {
    public static void main(String[] args) {
        Anime anime = new Anime();
        Character character = new Character();
        AnimeController animeController = new AnimeController();
        SQLGenerator sqlGenerator = new SQLGenerator(anime);

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
        System.out.println(sqlGenerator.selectStatement());

        ReflectionTable.setValueField(anime, "name", "Attack on Titan");
        ReflectionTable.setValueField(character, "name", "BBBBBBBBBBBBBBBBBBBb");
        System.out.println(anime.getName());
        System.out.println(character.getName());
    }
}
