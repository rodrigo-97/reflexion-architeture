package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)

public @interface Methods {
    @interface Get {
        String path();
        String type();
    }

    @interface  Post {
        String path();
    }

    @interface  Put {
        String path();
    }

    @interface  Delete {
        String path();
    }
}