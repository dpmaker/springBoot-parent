/**
 * 
 */
package boot.jdbc.mybatis.pagehelper;

/**
 * @author d_mail_p@sina.com
 * 在pageHelper组件中，使用了PageHelperInitParamAnnotion注解，但是在reqest中没有定义相关的参数
 *
 */
public class NoPageParamException extends Exception{

	public NoPageParamException(String msg){
		super(msg);
	}
}
