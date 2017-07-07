package com.gsitm.springproject.aop;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.JoinPoint;

import com.gsitm.springproject.service.WeatherService;

public class ExAdvice {
	private static final Logger log = LoggerFactory.getLogger(ExAdvice.class);
//ProceedingJoinPoint pJoinPoint, 
	
	@Resource(name = "WeatherService")
	private WeatherService weatherService;
	
	public void pointCutBefore(JoinPoint joinPoint) {

		try {
			Object[] args = joinPoint.getArgs();
			for (Object arg : args) {
/*				BoardVO vo = (BoardVO) arg;
				log.info("pointCutBefore.getOt_seq="+vo.getOt_seq());*/
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public void pointCutAfterThrowing(JoinPoint joinPoint, Throwable error) {
		log.info("logAfterThrowing() is running!");
		log.info("hijacked : " + joinPoint.getSignature().getName());
		log.info("Exception : " + error);
		log.info("******");
	}

}