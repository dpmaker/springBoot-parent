/**
 * 
 */
package boot.jdbc.mybatis.reflesh;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import boot.jdbc.mybatis.resultMap.ResultMappController;

/**
 * @author d_mail_p@sina.com
 *
 */

@Controller
@RequestMapping("refreshMapping")
public class RefreshMappingController {

	@Autowired
	private RefreshMappingService refreshMappingService;
	
	@Autowired
	private ResultMappController resultMappController;
	
	@RequestMapping("/selectBooks")
	@ResponseBody
	public Object selectBooks() throws Exception{
		//this.refreshMappingService.refresh("classpath:mapper/mybatis/ResultMapMapper.xml");
		this.refreshMappingService.refreshAll();
		return this.resultMappController.getAllBooks();
	}
}
