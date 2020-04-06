/**
 * 
 */
package boot.jdbc.mybatis.pagehelper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

/**
 * @author d_mail_p@sina.com
 *
 */
public class PageUtils {
	
	public static String REQUEST_PAGE_NUM="pageNum";
	public static String REQUEST_PAGE_SIZE="pageSize";
	
	public static String DEFAULT_REQUEST_PAGE_SIZE="10";



	 public static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
         PageResult pageResult = new PageResult();
         pageResult.setPageNum(pageInfo.getPageNum());
         pageResult.setPageSize(pageInfo.getPageSize());
         pageResult.setTotalSize(pageInfo.getTotal());
         pageResult.setTotalPages(pageInfo.getPages());
         pageResult.setContent(pageInfo.getList());
         return pageResult;
     }
	 
	 public static PageResult getPageResult2(List<?> list) {
		 PageInfo<?> pageInfo=new PageInfo(list);
         PageResult pageResult = new PageResult();
         pageResult.setPageNum(pageInfo.getPageNum());
         pageResult.setPageSize(pageInfo.getPageSize());
         pageResult.setTotalSize(pageInfo.getTotal());
         pageResult.setTotalPages(pageInfo.getPages());
         pageResult.setContent(pageInfo.getList());
         return pageResult;
     }

}
