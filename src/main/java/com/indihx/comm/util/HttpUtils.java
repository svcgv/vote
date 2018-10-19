package com.indihx.comm.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * http帮助类
 * @author Administrator
 *
 */
public class HttpUtils {
 
	
	/**
	 * 下载文件
	 * @param url
	 * @param param
	 * @return
	 */
	public static void executeDownload(String url, String path, String file){
		
		String result = "";
		
		if(url.startsWith("https")){
			httpsDownload(url, path, file);
		}else{
			httpDownload(url, path, file);
		}
		
	}
	/**
	 * get通用请求方法
	 * @param url
	 * @param param
	 * @return
	 */
	public static String executeGet(String url){
		
		String result = "";
		
		if(url.startsWith("https")){
			result = httpsGet(url);
		}else{
			result = httpGet(url);
		}
		
		return result;
	}
	
	/**
	 * post通用请求方法
	 * @param url
	 * @param param
	 * @return
	 */
	public static String executePost(String url,Map<String,String> param){
		String result = "";
		
		if(url.startsWith("https")){
			result = httpsPost(url,param);
		}else{
			result = httpPost(url,param);
		}
		
		return result;
	}
	
	
	/**
	 * get请求
	 * @param url 请求地址
	 * @return
	 */
	public static void httpDownload(String url, String path, String filename){
		HttpClient client = new DefaultHttpClient();
		
		HttpGet post = new HttpGet(url);
		String result = "";
		try {
			HttpResponse res = client.execute(post);
			HttpEntity entity = res.getEntity();
			if(entity != null) {
		        BufferedInputStream is = new BufferedInputStream(entity.getContent());
		        File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();
				}
		        BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(path+filename)); 
		        byte[] buffer = new byte[4096];
		        int len = -1;
		        while((len = is.read(buffer) )!= -1){
		            fos.write(buffer, 0, len);
		        }
		        fos.close();
		        is.close();
		        //files.add(file);
		    }
			//result = EntityUtils.toString(res.getEntity(),"utf-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(client!=null){
				client.getConnectionManager().shutdown();  
			}
		}
	}

	/**
	 * https get请求
	 * @param url
	 * @return
	 */
	public static Header[]  httpsDownload(String url, String path, String filename){
		HttpClient client = new DefaultHttpClient();
		Map<String, String>  map = new HashMap<>();
        String result = "";
        X509TrustManager xtm = new X509TrustManager(){   //创建TrustManager
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() { return null; }
        };

        SSLContext ctx;
        Header[] headers = null;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[]{xtm}, null);

            //创建SSLSocketFactory
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
            client.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
            HttpGet get = new HttpGet(url);
            HttpResponse httpResponse = client.execute(get);
            headers = httpResponse.getAllHeaders();
            HttpEntity entity = httpResponse.getEntity();
			if(entity != null) {
				Header header = entity.getContentType();
				System.out.println(header.getName()+" : "+header.getValue());
				//map.put(header.getName(), header.getValue());
				String ext = header.getValue().split("/")[1];
		        BufferedInputStream is = new BufferedInputStream(entity.getContent());
		        File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();
				}
		        BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(path+filename+"."+ext)); 
		        byte[] buffer = new byte[4096];
		        int len = -1;
		        while((len = is.read(buffer) )!= -1){
		            fos.write(buffer, 0, len);
		        }
		        fos.close();
		        is.close();
		        //files.add(file)
		    }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	if(client!=null){
				client.getConnectionManager().shutdown();  
			}
        }
		return headers;

	}
	
	
	/**
	 * get请求
	 * @param url 请求地址
	 * @return
	 */
	public static String httpGet(String url){
		HttpClient client = new DefaultHttpClient();
		
		HttpGet post = new HttpGet(url);
		String result = "";
		try {
			HttpResponse res = client.execute(post);
			result = EntityUtils.toString(res.getEntity(),"utf-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(client!=null){
				client.getConnectionManager().shutdown();  
			}
		}
		return result;
	}

	/**
	 * https get请求
	 * @param url
	 * @return
	 */
	public static String httpsGet(String url){
		HttpClient client = new DefaultHttpClient();
		
        String result = "";
        X509TrustManager xtm = new X509TrustManager(){   //创建TrustManager
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() { return null; }
        };

        SSLContext ctx;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[]{xtm}, null);

            //创建SSLSocketFactory
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
            client.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
            HttpGet get = new HttpGet(url);
            HttpResponse httpResponse = client.execute(get);
            result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	if(client!=null){
				client.getConnectionManager().shutdown();  
			}
        }

        return result;
	}
	
	/**
	 * https post请求
	 * @param url 请求地址
	 * @param param 参数
	 * @return
	 */
	public static String httpsPost(String url,Map<String,String> param){
		
		HttpClient client = new DefaultHttpClient();
		
		String result = "";
		X509TrustManager xtm = new X509TrustManager(){   //创建TrustManager 
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {} 
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {} 
            public X509Certificate[] getAcceptedIssuers() { return null; } 
        };
		
		SSLContext ctx;
		try {
			ctx = SSLContext.getInstance("TLS");
			ctx.init(null, new TrustManager[]{xtm}, null); 
	         
	        //创建SSLSocketFactory 
	        SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
	        client.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
	        HttpPost post = new HttpPost(url);

	        //创建参数队列
	        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	        if(param!=null&&param.size()!=0){
	        	for (Entry<String, String> set : param.entrySet()) {  
	        		String key = set.getKey();
	        		String value = set.getValue()==null?"":set.getValue();
	        		formparams.add(new BasicNameValuePair(key, value));
	        	}
	        }

            post.setEntity(new UrlEncodedFormEntity(formparams, HTTP.UTF_8));  

	        //result = EntityUtils.toString(post.getEntity());
            HttpResponse httpResponse = client.execute(post);
            result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
        	if(client!=null){
				client.getConnectionManager().shutdown();  
			}
        }
       
		return result;
	}
	
	/**
	 * https post请求
	 * @param url 请求地址
	 * @param param 参数
	 * @return
	 */
	public static String httpPost(String url,Map<String,String> param){
		
		HttpClient client = new DefaultHttpClient();
		
		String result = "";
		try {
		
	        HttpPost post = new HttpPost(url);

	        // 创建参数队列
	        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	        if(param!=null&&param.size()!=0){
	        	for (Entry<String, String> set : param.entrySet()) {  
	        		String key = set.getKey();
	        		String value = set.getValue()==null?"":set.getValue();
	        		formparams.add(new BasicNameValuePair(key, value));
	        	}
	        }

            post.setEntity(new UrlEncodedFormEntity(formparams, HTTP.UTF_8));  
            
	        //result = EntityUtils.toString(post.getEntity());
	        
	        HttpResponse httpResponse = client.execute(post);
            result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
        	if(client!=null){
				client.getConnectionManager().shutdown();  
			}
        }
        
		return result;
	}
	

	/**
	 * https post请求
	 * @param url 请求地址
	 * @param param 参数
	 * @return
	 */
	public static String httpsPost(String url){
		
		HttpClient client = new DefaultHttpClient();
		
		String result = "";
		X509TrustManager xtm = new X509TrustManager(){   //创建TrustManager 
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {} 
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {} 
            public X509Certificate[] getAcceptedIssuers() { return null; } 
        };
		
		SSLContext ctx;
		try {
			ctx = SSLContext.getInstance("TLS");
			ctx.init(null, new TrustManager[]{xtm}, null); 
	         
	        //创建SSLSocketFactory 
	        SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
	        client.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
	        HttpPost post = new HttpPost(url);

	        //result = EntityUtils.toString(post.getEntity());
	        HttpResponse httpResponse = client.execute(post);
            result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
        	if(client!=null){
				client.getConnectionManager().shutdown();  
			}
        }
        
		return result;
	}
	


	/**
	 * http post请求
	 * @param url 请求地址
	 * @return
	 */
	public static String httpPost(String url){
		
		HttpClient client = new DefaultHttpClient();
		
		String result = "";
		
		try {
	        HttpPost post = new HttpPost(url);
	        //result = EntityUtils.toString(post.getEntity());
	        HttpResponse httpResponse = client.execute(post);
            result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
        	if(client!=null){
				client.getConnectionManager().shutdown();  
			}
        }
          
		return result;
	}
	
 

    /**
     * json方式的https请求
     * @param url
     * @param content
     * @return
     */
    public static String httpsJsonPost(String url,String content){

    	HttpClient client = new DefaultHttpClient();
    	
        String result = "";
        X509TrustManager xtm = new X509TrustManager(){   //创建TrustManager
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() { return null; }
        };

        SSLContext ctx;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[]{xtm}, null);

            //创建SSLSocketFactory
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
            client.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
            HttpPost post = new HttpPost(url);
            StringEntity s = new StringEntity(content, "UTF-8");   // 中文乱码在此解决
            s.setContentType("application/json");
            post.setEntity(s);  //设置内容

            HttpResponse httpResponse = client.execute(post);

            result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
        	if(client!=null){
				client.getConnectionManager().shutdown();  
			}
        }

        return result;
    }

    /**
     * json方式的https请求
     * @param url
     * @param content 请求内容
     * @param head 请求头信息
     * @return
     */
    public static String httpsJsonPost(String url,String content,Map<String,String> head){

    	HttpClient client = new DefaultHttpClient();
    	
        String result = "";
        X509TrustManager xtm = new X509TrustManager(){   //创建TrustManager
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() { return null; }
        };

        SSLContext ctx;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[]{xtm}, null);

            //创建SSLSocketFactory
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
            client.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
            HttpPost post = new HttpPost(url);
            StringEntity s = new StringEntity(content, "UTF-8");   // 中文乱码在此解决
            s.setContentType("application/json");
            post.setEntity(s);  //设置内容

            //设置head
            if(head!=null&&head.size()!=0){
                Object[] attr = head.keySet().toArray();
                for(Object obj:attr){
                    String key = String.valueOf(obj);
                    String value = head.get(String.valueOf(obj));
                    post.addHeader(key,value);
                }
            }

            HttpResponse httpResponse = client.execute(post);

            result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
        	if(client!=null){
				client.getConnectionManager().shutdown();  
			}
        }

        return result;
    }
    
    public static void main(String[] args) {
    	/*获取token
    	String res = httpsGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx0faa55f1a53f29c5&secret=a9a586b82bfb51e89a35a51a443d517f");
    	System.out.println("res: "+res);*/
    	
    	
    	String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=12_e8cnD_ZyvSot0WzF5aNPxuiyL0InCR_wvuO2XDO3SfIhdtBfZxzrFKX3kbkphNaBF5KmCZ-WGmL48eg6xOxuquqBhbJmzMsYgNXq0gJgHeypaE6pPnO8KLt05tHY81lXsccTzHzHOM4E5qvAZTGaAAAGAN&media_id=4sSycH0BU-QE2x8ExD_bup1kYNhbOCF5Xmms8SlfzrLVghF_Ir1gL8P02I5rGPMz";
    	Header [] headers = httpsDownload(url, "d:/vote/test/", "pic");
    	for (Header header: headers) {
    		System.out.println(header.getName()+" : "+header.getValue());
    		if (header.getName().equals("Content-Type")) {
    			System.out.println(header.getValue().split("/")[1]);
    		}
    		if (header.getName().equals("Content-disposition")) {
    			String tmp = header.getValue();
    			System.out.println("filename: " + tmp.substring(tmp.indexOf("filename")+10, tmp.length()-1));
    			//System.out.println("filename: "+tmp);
    		}
    	}
    	
    	/*
    	String test = "attachment; filename=\"4sSycH0BU-QE2x8ExD_bup1kYNhbOCF5Xmms8SlfzrLVghF_Ir1gL8P02I5rGPMz.jpg\"";
    	System.out.println(test.indexOf("filename"));
    	System.out.println(test.length());
    	int from = test.indexOf("filename");
    	int to = test.length();
    	System.out.println(test.substring(from, to));
    	*/
    }
}