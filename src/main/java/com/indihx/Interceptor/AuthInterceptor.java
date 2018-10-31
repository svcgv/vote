package com.indihx.Interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String baseUri = request.getContextPath();

		String url = baseUri + "/view/crm/tologin.jsp";

		String path = request.getServletPath().toLowerCase().replace("//", "/");

		if (path.startsWith("/login")) {// 以login开头就不拦截

		} else {// return false拦截

			return false;

		}

		return super.preHandle(request, response, handler);

	}

	@Override

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		

	}

}