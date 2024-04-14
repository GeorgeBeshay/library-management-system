package maids.cc.src.Aspects;

import maids.cc.src.BusinessLogic.Utilities.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@ComponentScan(basePackages = {"maids.cc.src"})
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(public * maids.cc.src.Endpoints.BookEndpoint.*(..)) || " +
            "execution(public * maids.cc.src.Endpoints.PatronEndpoint.*(..)) || " +
            "execution(public * maids.cc.src.Endpoints.BorrowingEndpoint.*(..))")
    private void publicEndpoints() {
    }

    @Around(value = "publicEndpoints()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Logger.logMsgFrom(className, ">> %s() - %s".formatted(methodName, Arrays.toString(args)), -1);
        Object result = joinPoint.proceed();
        Logger.logMsgFrom(className, ">> %s() - %s".formatted(methodName, result), -1);
        return result;
    }

}
