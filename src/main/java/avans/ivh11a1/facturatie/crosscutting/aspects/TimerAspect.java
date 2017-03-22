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
public class TimerAspect {
    //@Pointcut("execution(* avans.ivh11a1.facturatie..*(..))") //Globaal
    @Pointcut("@annotation(avans.ivh11a1.facturatie.crosscutting.annotations.Timer) && execution(* avans.ivh11a1.facturatie..*(..))") //Met gebruik van @Timer
    public void TimerMethod() {
    }

    @Around("TimerMethod()")
    public Object TimerAroundAndAfter(ProceedingJoinPoint joinPoint) throws Throwable {
        long startMillis = System.currentTimeMillis();
        System.out.println("[*INFO*][TIMER] Starting timer for: " + joinPoint.getSignature());

        Object retVal = joinPoint.proceed();
        long duration = System.currentTimeMillis() - startMillis;
        System.out.println("[*INFO*][TIMER] Stopping timer for: " + joinPoint.getSignature() + " took " + duration + " ms");

        return retVal;
    }
}
