package utils;

public class StringUtils {
    public static String getFirstCharacter (String field){
        return field.substring(0, 1).toUpperCase().concat(field.substring(1));
    }

    public static void main(String[] args) {
        System.out.println(getFirstCharacter("rodrigo"));
    }
}

