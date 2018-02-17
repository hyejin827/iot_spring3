package com.iot.spring.common.aspact;

import java.io.IOException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.iot.spring.dao.NaverTransDAO;
import com.iot.spring.vo.NaverMsg;


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
			log.error("error => {}", e);
			ModelAndView mav = new ModelAndView("error/error");
			
			mav.addObject("errorMsg", ntdao.getText(e.getMessage()));
			return mav;
		}
		log.info("@Around end, RunTime : {} ms", (System.currentTimeMillis()-startTime));
		return obj;
	}
	
	@After("execution(* com.iot.spring.controller.*Controller.*(..))")
	public void afterLog(JoinPoint jp) {
		log.info("@After => {}", jp);
	}
}
