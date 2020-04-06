package boot.start.example;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Section { //在类上面写 @Section(id = "xxx", remark = "xxx") 类似于@Controller的使用

    String id();
    String remark();
	String v() default "1";

}
 