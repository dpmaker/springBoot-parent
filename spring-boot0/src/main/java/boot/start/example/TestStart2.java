package boot.start.example;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.infix.lang.infix.antlr.EventFilterParser.time_millis_function_return;

import boot.start.Section;
import boot.start.SectionMethodRegister;
import boot.start.SectionRegister;

@RestController
@Section(id="testStart2",remark="hahaha")
@RequestMapping("/start")
public class TestStart2 {

	@Autowired
	private TestScanner testScanner;
	
	@RequestMapping("/testScan")
	@Section(id="testStart2.test2",remark="hahaha222")
	@ResponseBody
	public Object testScan(){
		System.out.println(SectionMethodRegister.SECTION_MAP);
		Map<Object,Annotation> map=testScanner.getScannerTypeMap();
		testScanner.getScannerMethodMap();
		map.get("boot.start.TestStart");
		((Section)testScanner.getScannerTypeMap().get("boot.start.TestStart")).v();
		return testScanner.getScannerTypeMap();
	}
	
	/*
	
	@RequestMapping("/start2/test")
	@ResponseBody
	public Object test(){
		System.out.println(SectionRegister.SECTION_MAP);
		return SectionRegister.SECTION_MAP;
	}
	
	@RequestMapping("/start2/test2")
	@Section(id="testStart2.test2",remark="hahaha222")
	@ResponseBody
	public Object test2(){
		System.out.println(SectionMethodRegister.SECTION_MAP);
		SectionMethodRegister.SECTION_MAP.get("boot.start.en.TestStart2").remark();
		return SectionMethodRegister.SECTION_MAP;
	}
	
	
	@RequestMapping("/start2/test3")
	@Section(id="testStart2.test3",remark="hahaha222")
	@ResponseBody
	public Object test3(HttpServletRequest request){
		Map map=new HashMap();
		map.put("id", request.getParameter("id"));
		map.put("name", request.getParameter("name"));
		
		Map data=new HashMap();
		data.put("account", 100);
		map.put("data",data);
		return map;
	}
	
	
	public Object test4(Map m){
		Map map=new HashMap();
		map.put("id", m.get("id"));
		map.put("name",m.get("name"));
		
		map.put("account",100);
		return map;
	}
	*/
	
}
