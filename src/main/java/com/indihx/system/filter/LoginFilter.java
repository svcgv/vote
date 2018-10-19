package com.indihx.system.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;



import com.indihx.comm.InitSysConstants;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.service.IUsrInfoService;
import com.indihx.system.service.impl.UsrInfoServiceImpl;

public class LoginFilter implements Filter {
	
	private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);
	
    private IUsrInfoService usrInfoService;/*注入依赖的mapper实现类*/ 

	
	@Override
	public void destroy() {
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;     
		HttpServletResponse res = (HttpServletResponse) response; 
		String requestUrl = req.getRequestURI();
		UsrInfo user = (UsrInfo) req.getSession().getAttribute(InitSysConstants.USER_SESSION);
		req.getSession().setMaxInactiveInterval(1800);
		///login/loginInit
		int two = requestUrl.indexOf("/", (requestUrl.indexOf("/")+1));
		String path = requestUrl.substring(0, two);
		String url = requestUrl.substring((two), requestUrl.length());
		if(url.equals("/jsp/forward.jsp") || url.equals("/jsp/login.jsp")||url.equals("/login/loginInit") || url.equals("/jsp/nosession.jsp")
				||url.equals("/login/outLogin") || url.indexOf("resources") >= 1
				|| url.indexOf("demo") >= 1|| url.indexOf("basic/getLoginPublicKey.do")>=1
				|| url.indexOf("WeChat") >= 1){
			chain.doFilter(request, response);
		}else if(user==null||req.getSession(false)==null){
			res.sendRedirect("http://"+req.getHeader("Host")+path+"/jsp/forward.jsp"); 
			return;
		}else{
			//验证数据库中的session与当前user的session是否一致
			String dbSessionId = usrInfoService.getUserSession(user.getUsrId());
			logger.info("当前操作用户:"+user.getUsrId()+"，用户Session标志:"+dbSessionId);
			if(dbSessionId==null||!dbSessionId.equals(user.getSessionId())){
				res.sendRedirect("http://"+req.getHeader("Host")+path+"/jsp/nosession.jsp"); 
				return;
			}else{
				usrInfoService.modifyAcitveTime(user.getUsrId());
			}
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		ServletContext context = config.getServletContext(); 
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(context); 
		usrInfoService = (UsrInfoServiceImpl)ac.getBean("usrInfoServiceImpl"); 
	}

}
