package com.indihx.system.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TokenInterceptor extends HandlerInterceptorAdapter {

    private static Map<String , String> viewUrls = new HashMap<String , String>();
    private static Map<String , String> actionUrls = new HashMap<String , String>();
    private Object clock = new Object();
    
    /**
     * 拦截方法，添加or验证token
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String url = request.getRequestURI();
        String method = request.getMethod();
        if(viewUrls.keySet().contains(url) && ((viewUrls.get(url)) == null || viewUrls.get(url).equals(method)))
        {
            TokenHelper.setToken(request);
            return true;
        }
        else if(actionUrls.keySet().contains(url) && ((actionUrls.get(url)) == null || actionUrls.get(url).equals(method)))
        {
            System.out.println("Intercepting invocation to check for valid transaction token.");
            return handleToken(request, response, handler);
        }
        return true;
    }
    
    protected boolean handleToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        synchronized(clock)
        {
            if(!TokenHelper.validToken(request))
            {
                System.out.println("未通过验证...");
                return handleInvalidToken(request, response, handler);
            }
        }
        System.out.println("通过验证...");
        return handleValidToken(request, response, handler);
    }
    
    /**
     * 当出现一个非法令牌时调用
     */
    protected boolean handleInvalidToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        Map<String , Object> data = new HashMap<String , Object>();
        data.put("flag", 0);
        data.put("msg", "请不要频繁操作！");
        writeMessageUtf8(response, data);
        return false;
    }
    
    /**
     * 当发现一个合法令牌时调用.
     */
    protected boolean handleValidToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        return true;
    }
    
    private void writeMessageUtf8(HttpServletResponse response, Map<String , Object> json) throws IOException
    {
        try
        {
            response.setCharacterEncoding("UTF-8");
//            response.getWriter().print(JsonUtil.toJson(json));
        }
        finally
        {
            response.getWriter().close();
        }
    }

}
