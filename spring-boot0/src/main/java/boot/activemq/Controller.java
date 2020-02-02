package boot.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequestMapping("/aq")
public class Controller {
	@Autowired
	Producer producer;
	
	@RequestMapping("/test1/{name}")
	public String get(@PathVariable String name){
		producer.send(name);
		return "ss";
	}
	
	@RequestMapping("/test2/{name}")
	public String get2(@PathVariable String name){
		producer.send2(name);
		return "ss";
	}

}
