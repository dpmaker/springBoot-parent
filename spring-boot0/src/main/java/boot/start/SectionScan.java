package boot.start;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import boot.start.example.TestScanner;


@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
//@Import({SectionRegister.class})
@Import({TestScanner.class})
public @interface SectionScan { // 启动类上面@SectionScan(basePackages={"扫描的类路径"}) 类似@ComponentScan(basePackages = {"com.migu.*"})

	String[] value() default {};
}
