import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import boot.common.UserBean;
import mvc1.MVCTest1;

public class test {

	public static void main(String[] args){
		System.out.println(new Date());
		
		        AnnotationConfigApplicationContext ac =
		        		new AnnotationConfigApplicationContext(MVCTest1.class);
		        
		       System.out.println(ac.getBean(UserBean.class).getClass()); ;
	}
}
