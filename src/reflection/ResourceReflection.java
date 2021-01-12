package reflection;

import annotations.Controller;
import annotations.Methods;
import annotations.Table;

import java.lang.reflect.Method;

public class ResourceReflection {

    // TODO: 10/01/2021 alterar tipo de retorno do metodo
    // TODO: 10/01/2021 alterar essa classe para outra coisa sem ser resource 
    public static String get (Object table) {
        String tableName = getControllerName(table);
        if (table.getClass().isAnnotationPresent(Methods.Get.class)){
            if (table.getClass().getDeclaredAnnotation(Methods.Get.class).type() == "id"){
                //traz registro do banco pelo id
            }else{
                //traz todos registros
            }
        }
        System.out.println(tableName);
        return null;
    }

    public static boolean insert (Object table) {
        String tableName = getControllerName(table);
        if (table.getClass().isAnnotationPresent(Methods.Post.class)){
            // faz o insert
        }
        return false;
    }

    static String getControllerName (Object table) {
        return table.getClass().getDeclaredAnnotation(Controller.class).name();
    }
}
