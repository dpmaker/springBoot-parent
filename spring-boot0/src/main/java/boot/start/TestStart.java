package boot.start;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Section(id="testStart",remark="hahaha")
public class TestStart {

	
	@RequestMapping("/start/test")
	public void test(){
		System.out.println(SectionRegister.SECTION_MAP);
	}
	
	@RequestMapping("/start/test2")
	@Section(id="testStart.test2",remark="hahaha222")
	public void test2(){
		System.out.println(SectionMethodRegister.SECTION_MAP);
	}
	
}
