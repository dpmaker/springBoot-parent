/**
 * 
 */
package boot.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.Test2;

/**
 * @author d_mail_p@sina.com
 *
 */
public class LambdaTest {

	public static void main(String[] args) {
		LambdaTest test=new LambdaTest();
		 
		Runnable r=()->System.out.println("test");
		TestInterface t=(a,b)-> a*b;
		
		System.out.println(t.execute(2, 3));
		test.test2();
		
	}
	
	
	private void test2(){
		List list=Arrays.asList(2,7,3,6,1,3,34,1,2,3);
		
		Collections.sort(list, (a,b)-> Integer.valueOf(a.toString())-Integer.valueOf(b.toString()));
		System.out.println(list);
	}
	
	 
	
	
}
