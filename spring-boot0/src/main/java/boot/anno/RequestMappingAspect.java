package boot.anno;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@Aspect
public class RequestMappingAspect {

	
	//@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RestController)")
	public void MyRequestMappingAspect(){};
	
	@After("MyRequestMappingAspect()")
	public void execute(JoinPoint joinPoint){
	MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
	System.out.println("RequestMappingAspect working...."  );
	/*
		Method method = methodSignature.getMethod();
		method.getName();
		joinPoint.getArgs();
		method.getAnnotations();
		RequestMapping annotation = method.getAnnotation(RequestMapping.class);
		String[] value = annotation.value();
		
		System.out.println("request mapping 111"  + value);
		*/
	}
}
