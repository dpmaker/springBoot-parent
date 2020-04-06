/**
 * 
 */
package boot.jdbc.mybatis.resultMap;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import bean.Book;
import boot.jdbc.mybatis.intercept.InterceptAnnotation;

/**
 * @author d_mail_p@sina.com
 *
 */

@Mapper
public interface ResultMapMapper {

	@InterceptAnnotation(flag=true)
	public List<Book> getAllBooks();
	
	@InterceptAnnotation(flag=true)
	public List selectUser(int userId);
}
