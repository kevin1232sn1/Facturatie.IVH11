package avans.ivh11a1.facturatie.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyExecutionTimeAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyExecutionTimeAspect.class);

    @Around("execution(* avans.ivh11a1.facturatie.service.imp..*())")
    public void logAround(ProceedingJoinPoint joinPoint) {
        System.out.println("Before invoking getName() method");
        Object value = null;
        long startMillis = System.currentTimeMillis();
        try {
            value = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        long duration = System.currentTimeMillis() - startMillis;
        LOGGER.info("(AOP-myExecTime) Call to " + joinPoint.getSignature() + " took " + duration + " ms");
        System.out.println("After invoking getName() method. Return value=" + value);
    }
}