package boot.start;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import boot.start.example.TestScanner;

@RestController
@Section(id="testStart",remark="hahaha")
public class TestStart {

	@Autowired
	private TestScanner testScanner;
	
	@RequestMapping("/start/test")
	@ResponseBody
	public Object test(){
		System.out.println(this.testScanner.getScannerTypeMap());
		System.out.println(this.testScanner.getScannerMethodMap());
		Map map=this.testScanner.getScannerMethodMap();
		System.out.println(((Section)map.get("boot.start.en.TestStart2.test2")).remark());
		return this.testScanner.getScannerTypeMap();
	}
	
	@RequestMapping("/start/test2")
	@Section(id="testStart.test2",remark="hahaha222")
	@ResponseBody
	public Object test2(){
		System.out.println(SectionMethodRegister.SECTION_MAP);
		return SectionMethodRegister.SECTION_MAP;
	}
	
}
