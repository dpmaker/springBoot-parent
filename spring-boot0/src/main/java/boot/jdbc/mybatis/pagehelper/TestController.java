/**
 * 
 */
package boot.jdbc.mybatis.pagehelper;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author d_mail_p@sina.com
 *
 */

@Controller
@RequestMapping(value="/pagehelper")
public class TestController {

	@Autowired
	private PageHelpMapper pageHelpMapper;
	
	@RequestMapping(value="/test")
	@ResponseBody
	@PageHelperInitParam
	public Object testPageHelper( HttpServletRequest req){
		/*
		PageRequest pageRequest=new PageRequest();
		pageRequest.setPageNum(Integer.valueOf(req.getParameter("pageNum")));
		pageRequest.setPageSize(Integer.valueOf(req.getParameter("pageSize")));
	*/
		
		//PageHelper.startPage(Integer.valueOf(req.getParameter("pageNum")), Integer.valueOf(req.getParameter("pageSize")));
		
		List<Map> personnels = pageHelpMapper.selectPage();
		 
       // return PageUtils.getPageResult(pageRequest,getPageInfo(pageRequest));

        return PageUtils.getPageResult2(personnels);
		//return getPageInfo(pageRequest);
        }
	
	
	private PageInfo<Map> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Map> personnels = pageHelpMapper.selectPage();
        return new PageInfo<Map>(personnels);
  }
  

}
