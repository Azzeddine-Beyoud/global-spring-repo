package com.global.book.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Aspect
@Component
public class MeaurmentAspect {

	Logger log = LoggerFactory.getLogger(MeaurmentAspect.class);
	
	@Around(value = "execution(* com.global.book.service..*(..))")
	public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable{
		
		Long startTime = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder("KPI:");
		sb.append("[").append(joinPoint.getKind()).append("]\tfou: ").append(joinPoint.getSignature())
			.append("\twithArgs: ").append("(").append(joinPoint.getArgs()).append(")");
		
//		.append(StringUtils.join(joinPoint.getArgs(), ",")).append(")");
		sb.append("\ttook: ");
		
		Object returnValue = joinPoint.proceed();
		log.info(sb.append(System.currentTimeMillis()-startTime).append(" ms. ").toString());
		
		return returnValue;		
		
	}
	

}
