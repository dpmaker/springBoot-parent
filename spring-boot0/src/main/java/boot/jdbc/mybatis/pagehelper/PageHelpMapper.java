/**
 * 
 */
package boot.jdbc.mybatis.pagehelper;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author d_mail_p@sina.com
 *
 */

@Mapper
public interface PageHelpMapper {
	List<Map> selectPage();
}
