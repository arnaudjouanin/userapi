package net.atos.userapi.logging;

import java.time.LocalDate;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    public static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Before("execution(* net.atos.userapi.controller.*.*(..))")
    public void logIncoming(JoinPoint joinPoint){
        logger.info(LocalDate.now() + " method " + joinPoint.getSignature() + " arguments " + joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* net.atos.userapi.controller.*.*(..))", returning = "result")
    public void logOutgoing(JoinPoint joinPoint, Object result){
        logger.info(LocalDate.now() + " method " + joinPoint.getSignature() + " arguments " + joinPoint.getArgs() + " returned " + result);
    }

}
