package Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by danil on 21.06.2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String name();
    String type();
    boolean isPrKey();
}
