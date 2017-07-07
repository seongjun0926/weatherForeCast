package com.gsitm.springproject.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
 
@Aspect
@Component
public class AnnotationAOP {
	private static final Logger logger = LoggerFactory.getLogger(AnnotationAOP.class);

	 @Before("execution(* read*(..))")  // 반환형에 상관없고, 메소드이름이 set으로 시작하고, 인자가 String인 함수가 나오기 전에 아래 advice가 실행된다.
	 public void readAll(){
		 logger.info("AnnotationAOP.@Before=====");  //advice
	 }
	 
	 // 반환형에 상관없고, 메소드이름이 read으로 시작하고, 인자가 int인 함수가 나오고나서 무조건 실행된다. 마치 finally처럼. 예외가 있던없던, 아래 advice가 실행된다.
	 @After("execution(* read*(int))")  
	 public void getAll(){
		 logger.info("AnnotationAOP.@After=====");
	 }
	 
	 @Before("execution(* board(..))") // 반환형에 상관없고, 인자도 상관없는 getAge라는 함수가 나오기 전에
	 public void boardOnly(){
		 logger.info("AnnotationAOP.@Before=====");
	 }	 
	
	 @AfterReturning(returning="str", value="execution(* boardDetail(..))") //boardDetail함수가 실행되면 얻는 리턴값을 str에 받아서 advice에 전달.
	 public void boardDetail(String str){  //advice는 리턴값인 str를 받아서 사용가능
		 logger.info("AnnotationAOP.@AfterReturning=====" + str);
	 }
}