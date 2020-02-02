package boot.anno;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAnnoAspect {

	@Pointcut("@annotation(boot.anno.MyAnnotation)")
	public void myAnnoCut() {
	}

	@Before("myAnnoCut()")
	public void before(JoinPoint  joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		
		Method method = methodSignature.getMethod();
		method.getName();
		joinPoint.getArgs();
		method.getAnnotations();
		MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
		String value = annotation.value();
		System.out.println("before " + value);
		

	}
	
	@After("myAnnoCut()")
	public void after(JoinPoint  joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		((AnnotationController)joinPoint.getTarget()).getClass().getAnnotations();
		Method method = methodSignature.getMethod();
		method.getName();
		joinPoint.getArgs();
		method.getAnnotations();
		MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
		String value = annotation.value();
		System.out.println("after " + value);
		

	}
}
