package reflection;
import annotations.FieldTable;
import annotations.Table;
import models.Anime;
import utils.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ReflectionTable {

    public static String getTableName (Object table) {
        if (table.getClass().isAnnotationPresent(Table.class)){
            return table.getClass().getDeclaredAnnotation(Table.class).name();
        }else{
            return table.getClass().getSimpleName().toLowerCase();
        }
    }

    /*
     ** This part will get all attributes from table [pk, fk, normal attribute]
     */
    public static ArrayList<String> getFields (Object table) {
        ArrayList<String> fields = new ArrayList<>();

        for(Field field: table.getClass().getDeclaredFields()){
            String fieldName = field.getDeclaredAnnotation(FieldTable.class).columnName();

            //if is not pk or fk
            if (field.isAnnotationPresent(FieldTable.class)){
                boolean isKey = field.getDeclaredAnnotation(FieldTable.class).isPk() ||
                        field.getDeclaredAnnotation(FieldTable.class).isFk();

                if(!isKey){
                    fields.add(fieldName);
                }
            }
        }
        return fields;
    }

    public static  ArrayList<String> getPks (Object table){
        ArrayList<String> pks = new ArrayList<>();

        for(Field field: table.getClass().getDeclaredFields()){
            if (field.isAnnotationPresent(FieldTable.class)){
                boolean isPk = field.getDeclaredAnnotation(FieldTable.class).isPk();
                String fieldName = field.getDeclaredAnnotation(FieldTable.class).columnName();
                if (isPk){
                    pks.add(fieldName);
                }
            }
        }
        return pks;
    }

    public static ArrayList<String> getFks (Object table){
        ArrayList<String> fks = new ArrayList<>();

        for(Field field: table.getClass().getDeclaredFields()){
            if (field.isAnnotationPresent(FieldTable.class)){
                boolean isPk = field.getDeclaredAnnotation(FieldTable.class).isFk();
                String fieldName = field.getDeclaredAnnotation(FieldTable.class).columnName();
                if (isPk){
                    fks.add(fieldName);
                }
            }
        }
        return fks;
    }

    /*
    ** This part will get the type of attributes from table [pk, fk, normal attribute]
     */
    public static ArrayList<String> getTypeFields (Object table) {
        ArrayList<String> typeFields = new ArrayList<>();

        for(Field field: table.getClass().getDeclaredFields()){
            //if is not pk or fk
            if (field.isAnnotationPresent(FieldTable.class)){
                if (field.getType().equals(String.class)){
                    typeFields.add("VARCHAR(256)");
                }else if (field.getType().equals(Integer.class)){
                    typeFields.add("INTEGER");
                }
            }
        }
        return typeFields;
    }

    /*
     ** This part will set the value of attributes from table [pk, fk, normal attribute]
     */
    public static void setValueField(Object table, String attributeName, Object attributeValue) {
        String methodName = "set" + StringUtils.getFirstCharacter(attributeName);

        try {
            Field field = table.getClass().getDeclaredField(attributeName);
            Method method = table.getClass().getMethod(methodName, new Class[]{field.getType()});
            method.invoke(table, attributeValue);
        } catch (NoSuchMethodException | NoSuchFieldException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /*
     ** This part will get the value of attributes from table [pk, fk, normal attribute]
     */
    public static void getValueField(Object table, String attributeName) {
        String methodName = "get" + StringUtils.getFirstCharacter(attributeName);

        try {
            Method method = table.getClass().getMethod(methodName);
            method.invoke(table);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Anime anime = new Anime();
        anime.setName("Attack on Titan");

        getValueField(anime, "name");
        System.out.println(anime.toString());
    }
}
