/**
 * 
 */
package boot.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author d_mail_p@sina.com
 *
 */

@Controller
@RequestMapping(value="/testAction")
public class TestActionController {

	@RequestMapping(value="/test1")
	@ResponseBody
	//@TestActionAnnotation
	public void test1(){
		System.out.println("aaaaa");
	}
}
