package utils;

public class StringUtils {
    public static String getFirstCharacter (String field){
        return field.substring(0, 1).toUpperCase().concat(field.substring(1));
    }

    public static String removeUnderscore (String field){
        if(field.indexOf("_") > 1) {
            return field.replace(
                    field.substring(field.indexOf("_"), field.indexOf("_") + 2),
                    field.substring(field.indexOf("_")+ 1 ,field.indexOf("_") + 2).toUpperCase()
            );
        }else {
            return field;
        }
    }

    public static void main(String[] args) {
        System.out.println(getFirstCharacter("rodrigo"));
    }
}

