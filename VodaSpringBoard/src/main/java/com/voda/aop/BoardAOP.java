//
//  package com.voda.aop;
//  
//  import java.util.Arrays;
//  import org.aspectj.lang.JoinPoint;
//  import org.aspectj.lang.ProceedingJoinPoint;
//  import org.aspectj.lang.annotation.After; 
//  import org.aspectj.lang.annotation.AfterReturning; 
//  import org.aspectj.lang.annotation.AfterThrowing; 
//  import org.aspectj.lang.annotation.Around; 
//  import org.aspectj.lang.annotation.Aspect; 
//  import org.aspectj.lang.annotation.Before; 
//  import org.springframework.stereotype.Component;
//  
//  @Component
//  
//  @Aspect public class BoardAOP { 
//
//  @Before("execution(* com.voda..*.*(..))") 
//  public void beforeTest(JoinPoint joinPoint) { 
//	  System.out.println("beforeTest : "
//	  +joinPoint.getSignature().getDeclaringType().getSimpleName() + " / " +
//	  joinPoint.getSignature().getName()); System.out.println("beforeTset - args: "
//	  + Arrays.toString(joinPoint.getArgs()));  
//  }
//  
// //JoinPoint 이후에 실행
//  
//  @After("execution(* com.voda.service.*.*(..))") 
//  public void afterTest(JoinPoint joinPoint) { 
//	  System.out.println("afterTest : "
//	  +joinPoint.getSignature().getDeclaringType().getSimpleName() + " / " +
//	  joinPoint.getSignature().getName()); 
//	  }
//  
// //리턴된 데이터가 없으면 null값을 받아옴
//  
//  @AfterReturning(pointcut = "execution(* com.voda.service.*.*(..))", returning
//  = "returnObj") 
//  public void afterReturning(JoinPoint joinPoint, Object returnObj) {
//	  System.out.println("afterReturningTest : "
//	  +joinPoint.getSignature().getDeclaringType().getSimpleName() + " / " +
//	  joinPoint.getSignature().getName());
//	  System.out.println("afterReturningTest : " + (returnObj == null ? "리턴 결과 없음"
//	  : returnObj.toString())); 
//  }
//  
//  //메서드에서 Exception을 throws하는 경우 실행 - trycatch잡아서 실행한것은 실행되지않는다.
//  
//  @AfterThrowing(pointcut = "execution(* com.voda.service.*.*(..))", throwing = "exception") 
//  public void afterThrowingTest(JoinPoint joinPoint, Exception exception) { 
//	  System.out.println("afterThrowingTest : "
//	  +joinPoint.getSignature().getDeclaringType().getName());
//	  System.out.println("afterReturningTest : " + exception.getMessage()); 
//  }
//  
//  //메서드 호출 전후에 수행
// 
//  @Around("execution(* com.voda.mapper.*.*(..))") public Object aroundTest(ProceedingJoinPoint joinPoint) throws Throwable { 
//	  System.out.println("Around Test Start");
//	  System.out.println(joinPoint.getSignature().getName()); Object obj =
//	  joinPoint.proceed(); //메서드 실행하는 부분 if(obj != null)
//	  System.out.println(obj.toString()); System.out.println("Around Test End");
//	  return obj; 
//  }
// 
//  
//  }
//
