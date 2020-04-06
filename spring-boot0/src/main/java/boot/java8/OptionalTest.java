/**
 * 
 */
package boot.java8;

import java.util.Optional;

import bean.User;

/**
 * @author d_mail_p@sina.com
 *
 */
public class OptionalTest {

	
	public static void main(String[] args) {
		OptionalTest t=new OptionalTest();
		
		
		
		
		t.test1();
		t.test2();
	}
	
	
	private void test1(){
		User user=new User();
		user.setName("tom");
		
		if(user!=null){
			System.out.println(user.getName());
		}else {
			System.out.println(" usr is null..");
		}
	}
	
	
	
	private void test2(){
		User user=null;
		Optional<User> u2=Optional.of(user);
		Optional<User> u3=Optional.of(user);
		
		
	}
}
