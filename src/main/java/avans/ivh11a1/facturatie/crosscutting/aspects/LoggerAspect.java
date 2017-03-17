package avans.ivh11a1.facturatie.crosscutting.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by Tinne on 17-3-2017.
 */

@Component
@Aspect
public class LoggerAspect {
    //@Pointcut("execution(* avans.ivh11a1.facturatie..*(..))") //Globaal
    @Pointcut("@annotation(avans.ivh11a1.facturatie.crosscutting.annotations.Logger) && execution(* avans.ivh11a1.facturatie..*(..))") //Met gebruik van @Logger
    public void LoggingMethod() {
    }

    @Before("LoggingMethod()")
    public void loggingBefore(JoinPoint joinPoint) {
        System.out.println("[*INFO*][LOGGER] Currently executing: " + joinPoint.getSignature());
    }

    @Around("LoggingMethod()")
    public Object loggingAroundAndAfter(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("[*INFO*][LOGGER] Before execution: " + pjp.getSignature());

        Object retVal = pjp.proceed();

        System.out.println("[*INFO*][LOGGER] After execution: " + pjp.getSignature());

        return retVal;
    }
}
