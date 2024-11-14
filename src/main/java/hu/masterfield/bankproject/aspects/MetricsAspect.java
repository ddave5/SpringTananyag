package hu.masterfield.bankproject.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import hu.masterfield.bankproject.datatypes.Confirmation;

@Aspect
@Component
public class MetricsAspect {
    
    @Around("execution(* transfer(..)")
    public Object profile(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();

        try {
            return point.proceed();
        } finally { 
            System.out.println("<<" + point + " >> took " + (System.currentTimeMillis() - startTime) + " msec" );
        }

    }

    @AfterReturning(value="execution(*transfer(..)", returning = "conf")
    public void dump(JoinPoint point, Confirmation conf) {
        System.out.println("<< " + point + " >> dump= " + conf);
    }
 
}
