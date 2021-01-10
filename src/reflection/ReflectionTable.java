package reflection;

import annotations.FieldTable;
import annotations.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ReflectionTable {
    public static String getTableName (Object table) {
        if (table.getClass().isAnnotationPresent(Table.class)){
            return table.getClass().getDeclaredAnnotation(Table.class).name();
        }else{
            return table.getClass().getSimpleName().toLowerCase();
        }
    }

    public static ArrayList<String> getFields (Object table) {
        ArrayList<String> fields = new ArrayList<>();

        for(Field field: table.getClass().getDeclaredFields()){
            String fieldName = field.getDeclaredAnnotation(annotations.FieldTable.class).columnName();

            //if is not pk or fk
            boolean isKey =
                            field.getDeclaredAnnotation(annotations.FieldTable.class).isPk() ||
                            field.getDeclaredAnnotation(annotations.FieldTable.class).isFk();

            if(!isKey){
                fields.add(fieldName);
            }
        }
        return fields;
    }

    public static  ArrayList<String> getPks (Object table){
        ArrayList<String> pks = new ArrayList<>();

        for(Field field: table.getClass().getDeclaredFields()){
            boolean isPk = field.getDeclaredAnnotation(annotations.FieldTable.class).isPk();
            String fieldName = field.getDeclaredAnnotation(annotations.FieldTable.class).columnName();
            if (isPk){
                pks.add(fieldName);
            }
        }
        return pks;
    }

    public static ArrayList<String> getFks (Object table){
        ArrayList<String> fks = new ArrayList<>();

        for(Field field: table.getClass().getDeclaredFields()){
            boolean isPk = field.getDeclaredAnnotation(annotations.FieldTable.class).isFk();
            String fieldName = field.getDeclaredAnnotation(annotations.FieldTable.class).columnName();
            if (isPk){
                fks.add(fieldName);
            }
        }
        return fks;
    }

    public static String generateSql (){
        return null;
    }

}
