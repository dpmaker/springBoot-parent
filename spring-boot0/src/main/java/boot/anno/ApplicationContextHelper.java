package boot.anno;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
public class ApplicationContextHelper  implements ApplicationContextAware {

	private static ApplicationContext ctx;
	private static List<String> containers=new ArrayList<String>();
	
	private static  List<Class<? extends Annotation>> clazzs=new ArrayList();
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx=applicationContext;
	}
	
	
	public static String[] getAllBeans(){
		if(!ApplicationContextHelper.containers.isEmpty())
			return (String[])ApplicationContextHelper.containers.toArray();
		
		for(Class c : clazzs){
			containers.addAll(Arrays.asList(ApplicationContextHelper.ctx.getBeanNamesForAnnotation(c)));
		}
		
		return (String[])ApplicationContextHelper.containers.toArray();
	}
	
	public static ApplicationContext getCtx(){
		return ApplicationContextHelper.ctx;
	}

}
