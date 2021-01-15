package reflection;

import annotations.Controller;

public class ResourceReflection {

    // TODO: 10/01/2021 alterar tipo de retorno do metodo
    // TODO: 10/01/2021 alterar essa classe para outra coisa sem ser resource

    static String getControllerName (Object table) {
        return table.getClass().getDeclaredAnnotation(Controller.class).name();
    }
}
