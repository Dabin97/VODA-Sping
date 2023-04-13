package com.voda.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint; //joinPoint 2개중에 aspectj로 임포트
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BoardAOP {
	//joinPoint 이전에 실행
	//모든리턴타입 com.member 패키지 안에 있는 모든 클래스의 모든 메서드, 매개변수는 모든 형태를 지원
	//서브 패키지까지 전부 포함
	@Before("execution(* com.voda..*.*(..))") //* = 리턴타입, com.member = 패키지, *.* = 모든 클래스(두번째 * = 모든 메서드), (..) = 모든 형태의 매개변수
	public void beforeTest(JoinPoint joinPoint) {
		System.out.println("beforeTest : " +joinPoint.getSignature().getDeclaringType().getSimpleName()
				+ " / " + joinPoint.getSignature().getName());
		System.out.println("beforeTset - args: " +  Arrays.toString(joinPoint.getArgs())); //getArgs() 인자값을 배열에 넣어서 출력한다.
	}
	
	//JoinPoint 이후에 실행
	@After("execution(* com.voda.service.*.*(..))")
	public void afterTest(JoinPoint joinPoint) {
		System.out.println("afterTest : " +joinPoint.getSignature().getDeclaringType().getSimpleName()
				+ " / " + joinPoint.getSignature().getName());
	}
	
	//리턴된 데이터가 없으면 null값을 받아옴
	@AfterReturning(pointcut = "execution(* com.voda.service.*.*(..))", returning = "returnObj")
	public void afterReturning(JoinPoint joinPoint, Object returnObj) {
		System.out.println("afterReturningTest : " +joinPoint.getSignature().getDeclaringType().getSimpleName()
				+ " / " + joinPoint.getSignature().getName());
		System.out.println("afterReturningTest : " + (returnObj == null ? "리턴 결과 없음" : returnObj.toString()));
	}   
	 
	//메서드에서 Exception을 throws하는 경우 실행 - trycatch잡아서 실행한것은 실행되지않는다. 
	@AfterThrowing(pointcut = "execution(* com.voda.service.*.*(..))", throwing = "exception")
	public void afterThrowingTest(JoinPoint joinPoint, Exception exception) { 
		System.out.println("afterThrowingTest : " +joinPoint.getSignature().getDeclaringType().getName());
		System.out.println("afterReturningTest : " + exception.getMessage());
	}   
	
	//메서드 호출 전후에 수행
	@Around("execution(* com.voda.mapper.*.*(..))")
	public Object aroundTest(ProceedingJoinPoint joinPoint) throws Throwable { //around에서만 ProceedingJoinPoint사용가능, around는 before,after모두 커버한다.
		System.out.println("Around Test Start");
		System.out.println(joinPoint.getSignature().getName());
		Object obj = joinPoint.proceed(); //메서드 실행하는 부분
		if(obj != null) System.out.println(obj.toString());
		System.out.println("Around Test End");
		return obj;
	}
	
	
}
