package pl.lechowicz.performance_trace;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomPerformanceInterceptor extends AbstractMonitoringInterceptor {

    private final AtomicInteger slowTransactionCount = new AtomicInteger(0);
    public CustomPerformanceInterceptor(boolean useDynamicLogger) {
        setUseDynamicLogger(useDynamicLogger);
    }

    @Override
    protected Object invokeUnderTrace(MethodInvocation invocation, Log log) throws Throwable {

        String name = createInvocationTraceName(invocation);
        long start = System.currentTimeMillis();
        log.debug("Method " + name + " execution started at: " + new Date());
        try {
            return invocation.proceed();
        } finally {
            long end = System.currentTimeMillis();
            long time = end - start;
            log.info("Method " + name + " executed: " + time + " ms");
            log.debug("Method " + name + " execution ended at: " + new Date());

            //Additional logger for slow execution
            if (time > 50) {
                log.warn("Method execution longer than 50ms!, Total Slow Transaction count " + slowTransactionCount.incrementAndGet());
            }

        }
    }
}
