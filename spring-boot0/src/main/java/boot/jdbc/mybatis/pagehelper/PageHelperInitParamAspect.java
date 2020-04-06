/**
 * 
 */
package boot.jdbc.mybatis.pagehelper;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageHelper;

import boot.anno.AnnotationController;
import boot.anno.MyAnnotation;

/**
 * @author d_mail_p@sina.com
 * 这个aop用来在PageHelperInitParamAnnotion注解的方法前，添加分页参数的设置，从监听器中取出request，获取其中的参数
 */

@Service
@Aspect
public class PageHelperInitParamAspect {

	@Pointcut("@annotation(boot.jdbc.mybatis.pagehelper.PageHelperInitParam)")
	public void PageHelperInitParamAnnotion() {
	}

	@Before("PageHelperInitParamAnnotion()")
	public void before(JoinPoint  joinPoint) throws NoPageParamException {
		
		HttpServletRequest req=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String pageNum=req.getParameter(PageUtils.REQUEST_PAGE_NUM);
		if(pageNum==null || pageNum.isEmpty()){
			throw new NoPageParamException("未定义pageHelper中的pageNum参数.");
		}
		String pageSize=req.getParameter(PageUtils.REQUEST_PAGE_SIZE);
		if(pageSize==null || pageSize.isEmpty()){
			pageSize=PageUtils.DEFAULT_REQUEST_PAGE_SIZE;
		}
		
		PageHelper.startPage(Integer.valueOf(pageNum), Integer.valueOf(pageSize));
		
		System.out.println(new Date()+"  PageHelperInitParamAnnotion is working... pageNum is "+pageNum+" , pageSize is "+pageSize);

	}
	
	@After("PageHelperInitParamAnnotion()")
	public void after(JoinPoint  joinPoint) {
		
	}
}
