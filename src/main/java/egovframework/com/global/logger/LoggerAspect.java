package egovframework.com.global.logger;

import egovframework.com.global.DataSource;
import egovframework.com.global.DynamicContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

@Aspect
@Order(value=1)
public class LoggerAspect {
    protected Logger log = LoggerFactory.getLogger(LoggerAspect.class);
    static String name = "";
    static String type = "";
     
    @Around("execution(* office..web.*Controller.*(..)) or execution(* office..service.impl.*Impl.*(..)) or execution(* office..service.impl.*DAO.*(..))")
    public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
        type = joinPoint.getSignature().getDeclaringTypeName();
        
		 
        if (type.indexOf("Controller") > -1) {
            name = "Controller : ";
        }
        else if (type.indexOf("Service") > -1) {
            //service단에서 데이터 소스를 변경할 경우
        	final String methodName = joinPoint.getSignature().getName();
        	final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        	Method method = methodSignature.getMethod();
        	if(method.getDeclaringClass().isInterface()){
        		method = joinPoint.getTarget().getClass().getDeclaredMethod(methodName, method.getParameterTypes());
        	}

        	DataSource dataSource = (DataSource) method.getAnnotation(DataSource.class);
        	
        	if(dataSource != null) {
        	    //데이터소스 셋팅
        		DynamicContextHolder.setDynamicType(dataSource.value());
        	}
        	
            name = "ServiceImpl : ";
        }
        else if (type.indexOf("DAO") > -1) {
            name = "DAO : ";
        }
        
        log.info(name + type + "." + joinPoint.getSignature().getName() + "()");
        
        return joinPoint.proceed();
    }
}

