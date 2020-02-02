package boot.anno;

import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import junit.framework.Assert;

@RestController
@MyAnnotation(value="main class test value...")
public class AnnotationController {

	/**
	 * 随便写写
	 * @param name：页面传入的名称
	 * @return
	 */
	@MyAnnotation(value="test value...")
	@RequestMapping("/anno/{name}")
	public String getAnno(@PathVariable String name){
		System.out.println("running...");
		return name;
	}
	
	@RequestMapping("/getAll")
	public String getAll(){
		String a="";
		String[] b=ApplicationContextHelper.getAllBeans();
		for(String bn :b){
			if(!bn.equals("annotationController")){
				continue;
			}
			a+=bn+"  ";
			String c=ApplicationContextHelper.getCtx().getType(bn).getName();
			c=c.substring(0,c.indexOf("$$EnhancerBySpringCGLIB"));
			Class clazz=null;
			try {
				clazz=Class.forName(c);
				Method[] methods=clazz.getMethods();
				for(Method m : methods){
					
					
					m.getAnnotations();
					RequestMapping r=m.getAnnotation(RequestMapping.class);
					
					if(r!=null){
						a+="类名为："+c+"-----";
						a+="bean名为："+bn+"-----";
						a+="注解值为："+r.value()[0]+"------";
						a+="方法名为："+m.getName()+"----";
						a+="返回值类型为："+m.getReturnType().getName();
					}
					
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		return a;
	}
}
