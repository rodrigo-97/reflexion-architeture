import annotations.Table;
import models.Anime;
import models.Character;

public class Main {
    public static void main(String[] args) {
        Anime anime = new Anime();
        Character character = new Character();

        System.out.println("--- Anime data ---");
        System.out.println(anime.getTableName());
        System.out.println(anime.getFields());
        System.out.println(anime.getPks());
        System.out.println(anime.getFks());

        System.out.println("--- Character data ---");
        System.out.println(character.getTableName());
        System.out.println(character.getFields());
        System.out.println(character.getPks());
        System.out.println(character.getFks());
    }
}
