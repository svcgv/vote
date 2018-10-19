/**
 * 
 */
package com.indihx.activiti;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.indihx.comm.exception.BusinessException;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: StartNode.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年4月26日下午6:42:01</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>StartNode.java</p>
 * <p>流程与业务的整合动态代理处理逻辑</p>
 */
@Component
@Transactional
public class StartNode {
	
	private Logger logger = LoggerFactory.getLogger(StartNode.class);
	
	//定义一个属性变量
	private Map<String, Object> variables;
	private String businessKey;
	
	//设置人人员或组
	protected IdentityService identityService;
	@Autowired
    public void setIdentifyService(IdentityService identityService) {
		this.identityService = identityService;
	}
	
	//设置运行流程
	protected RuntimeService runtimeService;
	@Autowired
    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }
	
	/**
	 * 代理构造器
	 * @param businessKey
	 * @param variables
	 * @param runtimeService
	 * @param identityService
	 */
	public void common(String businessKey,Map<String, Object> variables,RuntimeService runtimeService,IdentityService identityService){
		this.variables=variables;
		this.businessKey=businessKey;
		this.runtimeService=runtimeService;
		this.identityService=identityService;
	}
	
	/**
	 * <p>标    题: 物业管理信息系统(PMS)</p>
	 * <p>描    述: StartNode.java</p> 
	 * <p>版    权: Copyright (c) 2018  </p>
	 * <p>公    司: 上海泓智信息科技有限公司</p>
	 * <p>创建时间: 2018年4月26日下午6:50:11</p>
	 * <p>@author zhengwei</p>
	 * <p>@version 1.0</p>
	 * <p>StartNode.java</p>
	 * <p>动态代理类只能代理接口（不支持抽象类），</p>
	 * <p>代理类都需要实现InvocationHandler类，实现invoke方法。</p>
	 * <p>该invoke方法就是调用被代理接口的所有方法时需要调用的，该invoke方法返回的值是被代理接口的一个实现类。</p>
	 */
	public class LogHandler implements InvocationHandler{  	  
	    // 目标对象  
	    private Object targetObject;  
	    //绑定关系，也就是关联到哪个接口（与具体的实现类绑定）的哪些方法将被调用时，执行invoke方法。              
	    public Object newProxyInstanceStart(Object targetObject){  
	        this.targetObject=targetObject;  
	        //该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例    
	        //第一个参数指定产生代理对象的类加载器，需要将其指定为和目标对象同一个类加载器  
	        //第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口  
	        //第三个参数表明这些被拦截的方法在被拦截时需要执行哪个InvocationHandler的invoke方法  
	        //根据传入的目标返回一个代理对象  
	        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),  
	                targetObject.getClass().getInterfaces(),this);  
	    }  
		  
	    @Override  
	    //关联的这个实现类的方法被调用时将被执行  
	    //InvocationHandler接口的方法，proxy表示代理，method表示原对象被调用的方法，args表示方法的参数  
	    public Object invoke(Object proxy, Method method, Object[] args)  
	            throws Throwable {  
	        System.out.println("start-->>");  
	        for(int i=0;i<args.length;i++){  
	            System.out.println(args[i]);  
	        }  
	        Object ret=null;  
	        try{  
	            //原对象方法调用前处理日志信息  
	            System.out.println("satrt-->>");
	        		           	 
	            //启动流程
	            //调用目标方法  
	            
//	            Leave leave=(Leave)args[0];         
	            // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
	            try {
//	            	identityService.setAuthenticatedUserId(leave.getUserId());                                
	            	ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("ChangeCourse", businessKey, variables);
	            	String processInstanceId = processInstance.getId();
	            
//	            	leave.setProcessInstanceId(processInstanceId);
	            	logger.debug("start process of {key={}, bkey={}, pid={}, variables={}}", new Object[]{"ChangeCourse", processInstanceId, variables});
	            } finally {
	                identityService.setAuthenticatedUserId(null);
	            }
//	            args[0]=leave;
	            ret=method.invoke(targetObject, args);  
	            
	            //调用完成当前结点
	    		// >> 办理完第1个任务“提交申请”		
	            //jbpmService.completeFirstTask(pi);  
	    		
	            //原对象方法调用后处理日志信息  
	            System.out.println("success-->>");  
	        }catch(Exception e){  
	            e.printStackTrace();  
	            System.out.println("error-->>");  
		            throw new BusinessException(e.getMessage());
		        }  
		        return ret;  
		    }			
	}	
}
