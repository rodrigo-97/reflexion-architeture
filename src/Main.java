import DAO.SQLGenerator;
import controllers.AnimeController;
import models.Anime;
import models.Character;

public class Main {
    public static void main(String[] args) {
        Anime anime = new Anime();
        Character character = new Character();
        AnimeController animeController = new AnimeController();
        SQLGenerator sqlGenerator = new SQLGenerator(anime);

        System.out.println("--- Anime data ---");
        System.out.println(anime.getTableName());
        System.out.println(anime.getFields());
        System.out.println(anime.getPks());
        System.out.println(anime.getFks());
        anime.generateTable();

        System.out.println("--- Character data ---");
        System.out.println(character.getTableName());
        System.out.println(character.getFields());
        System.out.println(character.getPks());
        System.out.println(character.getFks());

        System.out.println(animeController.getAll());
        System.out.println(sqlGenerator.selectStatement());
    }
}
