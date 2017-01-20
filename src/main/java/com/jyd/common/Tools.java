package com.jyd.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

public class Tools {
	public static SimpleDateFormat nyrsfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat nyr = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat nyrsf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static ExecutorService executorService = Executors.newCachedThreadPool();
	public static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(Locale.CHINA);
	public static JSONObject formatStr2JSON(String str) {
		
		return JSONObject.fromObject(str);
	}
	 
	/**
	 * 每个方法权限的校验.checkPermission 注解说明可以使用的权限。不能获取重载的方法
	 * @param cookie_uuid 用户的uuid
	 * @param className 类名
	 * @param methodName 当前方法名
	 * @param methodParmType 方法对应的参数类型
	 * @return
	 * @throws Exception 
	 */
	public static boolean vaildPermission(String cookie_uuid) throws Exception{
		if(StringUtils.isEmpty(cookie_uuid))
			return false;
		
		List<String> per =Constrant.permissionsMap.get(cookie_uuid);
		if(null == per || per.size()<=0)
			return false;
		
		//如果是管理员，具有一切权限
		int size = per.size();
		for(int i=0;i<size;i++){
			if(Constrant.PERMISSION_ADMIN.equalsIgnoreCase(per.get(i)))
				return true;
		}
		
		StackTraceElement[] stack = new Throwable().getStackTrace();
		
		String className = stack[1].getClassName();//调用该方法的，一定是第1个，从0开始计数
		Class classInstance = Class.forName(className);
		
		if(classInstance == null)
			return false;
		
		String methodName = stack[1].getMethodName();//
		
		if(StringUtils.isEmpty(methodName))
			return false;
		
		Method method=null;
		Method[] methods = classInstance.getDeclaredMethods();
		int m_length = methods.length;
		for(int i=0;i<m_length;i++){
			if(methods[i].getName().equalsIgnoreCase(methodName)){
				method=methods[i];
				break;
			}
		}
		
		if(method ==null)
			return false;
		
        Annotation[] annotation = method.getDeclaredAnnotations();
		for(Annotation a : annotation){
			if(!(a instanceof checkPermission))
				continue;

			checkPermission c =(checkPermission)a;
			if(StringUtils.isEmpty(c.value()))
				return false;
			
			String[] needPermissions = c.value().split(",");
			int needSize = needPermissions.length;
			for(int i = 0;i<needSize;i++){
				String needPer = needPermissions[i];
				
				if(StringUtils.isEmpty(needPer))
					continue;
				
				for(int j=0;j<size;j++){
					if(needPer.equalsIgnoreCase(per.get(j)))//找到一个就可以返回
						return true;
				}
			}
		}
		return false;
	}
	
	public static void writeCookie( HttpServletResponse response,String key,String value,int maxAge){
		Cookie cookie = new Cookie(key,value);
		//设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);//关闭浏览器就清除
		response.addCookie(cookie);
	}
	
	public static String readCookie(HttpServletRequest request,String key){
		if(StringUtils.isEmpty(key))
			return null;
		Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
		if(null != cookies){
			for(Cookie cookie : cookies){
				if(key.equals(cookie.getName())){
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	public static String getMd5(String code) {
		String encode = DigestUtils.md5Hex(code);
		return encode;
	}
	
	public static String getRequestIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");  
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("X-Real-IP");  
        }  
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (StringUtils.isEmpty(ip)  || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (StringUtils.isEmpty(ip)  || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (StringUtils.isEmpty(ip)  || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
      //经过nginx转发后，x-forwarded-for 获取的ip可能有多个，他们是逗号分隔的。例如183.16.90.3, 219.133.40.15
		if(StringUtils.isNotEmpty(ip) && ip.indexOf(",")>=0){
			String[] p = ip.split(",");
			if(p.length>=0)
				ip=p[0];
		}
		return  "0:0:0:0:0:0:0:1".equals(ip)|| StringUtils.isEmpty(ip)  ? Constrant.LOCALHOST_IP : ip; 
	}
	
	public static void main(String[] args) {
		//aaa.call();
		
		try {
			Tools.vaildPermission(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
