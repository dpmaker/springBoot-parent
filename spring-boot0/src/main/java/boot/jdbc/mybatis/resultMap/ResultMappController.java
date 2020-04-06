/**
 * 
 */
package boot.jdbc.mybatis.resultMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.*;


/**
 * @author d_mail_p@sina.com
 *
 */

@Controller
@RequestMapping("/result")
public class ResultMappController {

	
	@Autowired
	private ResultMapMapper resultMapMapper;
	
	
	@RequestMapping("getAllBooks")
	@ResponseBody
	public Object getAllBooks(){
		List list=this.resultMapMapper.getAllBooks();
		return list;
	}
	
		
	@RequestMapping("selectUser/{userId}")
	@ResponseBody
	public Object selectUser(@PathVariable int userId){
		List list=this.resultMapMapper.selectUser(userId);
		return list;
	}
	
	
}
