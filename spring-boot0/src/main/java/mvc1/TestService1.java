package mvc1;

import org.springframework.stereotype.Service;

@Service
public class TestService1 {

	public String sayHello(String name){
		System.out.println("Hello,"+name);
		return "Hello,"+name;
	}
}
