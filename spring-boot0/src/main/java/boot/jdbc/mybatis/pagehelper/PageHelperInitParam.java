/**
 * 
 */
package boot.jdbc.mybatis.pagehelper;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author d_mail_p@sina.com
 * 这个注解用来给pageHelper组件初始化分页参数，通过拦截器PageHelperInitParamAspect实现aop拦截
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface PageHelperInitParam {
	public String value() default "";
}
