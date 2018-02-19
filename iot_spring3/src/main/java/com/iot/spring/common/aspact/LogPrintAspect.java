package com.iot.spring.common.aspact;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.spring.dao.NaverTransDAO;

@Service
@Aspect
public class LogPrintAspect {
	
	@Autowired
	private NaverTransDAO ntdao;
	
	private static final Logger log = LoggerFactory.getLogger(LogPrintAspect.class);
	@Before("execution(* com.iot.spring.controller.*Controller.*(..))")
	public void beforeLog(JoinPoint jp) {
		log.info("@Before => {}", jp);
	}
	
	@Around("execution(* com.iot.spring.controller.*Controller.*(..))")
	public Object aroundLog(ProceedingJoinPoint pjp) throws IOException {
		log.info("@Around begin");
		Object obj = null;
		long startTime = System.currentTimeMillis();
		try {
			obj = pjp.proceed();
		} catch (Throwable e) {
			log.error("@Around error=>{}",e);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("errorMsg", ntdao.getText(e.getMessage()));
			log.error("error=> {}",map);
			return map;
		}
		log.info("@Around end, RunTime : {} ms", (System.currentTimeMillis()-startTime));
		return obj;
	}
	
	@After("execution(* com.iot.spring.controller.*Controller.*(..))")
	public void afterLog(JoinPoint jp) {
		log.info("@After => {}", jp);
	}
}
