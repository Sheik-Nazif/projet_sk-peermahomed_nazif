package com.example.projet_skpeermahomed_nazif.aop;

import com.example.projet_skpeermahomed_nazif.dto.TransferDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Trace les opérations sensibles de virement compte-à-compte via Spring AOP
 * et écrit dans un fichier de log dédié (voir logback-spring.xml).
 */
@Aspect
@Component
public class TransferAuditAspect {

    private static final Logger AUDIT_LOGGER = LoggerFactory.getLogger("TRANSFER_AUDIT");

    @Pointcut("execution(* com.example.projet_skpeermahomed_nazif.service.AccountService.transfer(..))")
    public void transferOperation() { /* pointcut */ }

    @Around("transferOperation() && args(transferDTO)")
    public Object aroundTransfer(ProceedingJoinPoint pjp, TransferDTO transferDTO) throws Throwable {
        // Log avant
        AUDIT_LOGGER.info("START transfer from={} to={} amount={}",
                safe(transferDTO.fromAccountId()), safe(transferDTO.toAccountId()), transferDTO.amount());
        long start = System.currentTimeMillis();
        try {
            Object result = pjp.proceed();
            long took = System.currentTimeMillis() - start;
            AUDIT_LOGGER.info("SUCCESS transfer from={} to={} amount={} took={}ms",
                    safe(transferDTO.fromAccountId()), safe(transferDTO.toAccountId()), transferDTO.amount(), took);
            return result;
        } catch (Throwable ex) {
            long took = System.currentTimeMillis() - start;
            AUDIT_LOGGER.error("FAIL transfer from={} to={} amount={} took={}ms error={}",
                    safe(transferDTO.fromAccountId()), safe(transferDTO.toAccountId()), transferDTO.amount(), took, ex.toString());
            throw ex;
        }
    }

    private Object safe(Object o) { return o == null ? "null" : o; }
}
