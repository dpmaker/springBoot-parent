/**
 * 
 */
package boot.swagger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import boot.jdbc.mybatis.resultMap.ResultMapMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author d_mail_p@sina.com
 *
 */

@Controller
@RequestMapping("/swagger")
public class SwaggerController {


	
	@Autowired
	private ResultMapMapper resultMapMapper;
	
	
	@RequestMapping("getAllBooks")
	@ResponseBody
	public Object getAllBooks(){
		List list=this.resultMapMapper.getAllBooks();
		return list;
	}
	
	
	
	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
	@RequestMapping(value="selectUser/{userId}",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object selectUser(@PathVariable int userId){
		List list=this.resultMapMapper.selectUser(userId);
		return list;
	}
	
}
