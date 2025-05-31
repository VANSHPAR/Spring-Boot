package com.example.TodoApiSpring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMonitorAspect {
    @Around("@annotation(TimeMonitor)") //@around indicates logtime func. executed both after and before execution of method
            public  void logtime(ProceedingJoinPoint joinPoint) {
        try{
           //execute  join point
           joinPoint.proceed();
        }
        catch (Throwable e){
            System.out.println("Something went wrong during execution");
        }
        finally {
            long start = System.currentTimeMillis();  //start time of the code
            long end = System.currentTimeMillis();
            long totaltime = end - start;

            System.out.println("Total time of Execution of the method is: " +totaltime+" ms...");
        }

    }


}
