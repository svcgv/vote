package com.indihx.elecvote.service;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Component
public class LogService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public void beforeMethod(JoinPoint jp) {
		logger.info("---method " + jp.getSignature().toShortString() +"invoked,param is :"
                + JSON.toJSONString(jp.getArgs()));
		System.out.println("---method " + jp.getSignature().toShortString() +"invoked,param is :"
                + JSON.toJSONString(jp.getArgs()));
	}
	
	public void afterMethod(JoinPoint jp, Object retVal) {
		logger.info("---method " + jp.getSignature().toShortString() +"invoked,result is :"
                +retVal);
		System.out.println("---method " + jp.getSignature().toShortString() +"invoked,result is :"
                +retVal);
	}
	
	public void exceptionMethod(JoinPoint jp, Exception exception) {
		logger.info("---method " + jp.getSignature().toShortString() +"invoked,exception " +
                "is :"+ exception.getMessage());
		System.out.println("---method " + jp.getSignature().toShortString() +"invoked,exception " +
                "is :"+ exception.getMessage());
	}
}
