package ru.gothmog.urbanplanner.core.audit;

/**
 * @author d.grushetskiy
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AuditAnnotation {

    /**
     * Отображать ли изменения этого класса в списке истории изменений
     */
    boolean visibleInHistory() default true;

    /**
     * Как объекты такого класса будут выводиться в списке истории изменений
     */
    String title() default "";

}
