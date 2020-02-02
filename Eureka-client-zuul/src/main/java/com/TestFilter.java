package com;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;
import org.springframework.stereotype.Component;

import com.netflix.ribbon.proxy.annotation.Content;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class TestFilter extends ZuulFilter{

	@Override
	public Object run() {
		RequestContext context=RequestContext.getCurrentContext();
		HttpServletRequest request=context.getRequest();
		Object token=request.getParameter("token");
		if(token==null){
			context.setSendZuulResponse(false);
			context.setResponseStatusCode(404);
			try {
				context.getResponse().getWriter().write("no token ...");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
