package pl.lechowicz.performance_trace.monitor;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopConfiguration {
    @Pointcut("execution(public String pl.lechowicz.performance_trace.employee.EmployeeService.getFullName(..)))")
    public void monitor() { }

    @Bean
    public PerformanceMonitorInterceptor performanceMonitorInterceptor() {
        return new PerformanceMonitorInterceptor(true);
    }

    @Bean
    public Advisor performanceMonitorAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("pl.lechowicz.performance_trace.monitor.AopConfiguration.monitor()");
        return new DefaultPointcutAdvisor(pointcut, performanceMonitorInterceptor());
    }

    // custom performance monitoring intercepter
    @Pointcut("execution(public int pl.lechowicz.performance_trace.employee.EmployeeService.createEmployee(..))")
    public void customMonitor() { }

    @Bean
    public CustomPerformanceInterceptor customPerformanceMonitorInterceptor() {
        return new CustomPerformanceInterceptor(true);
    }
    @Bean
    public Advisor customPerformanceMonitorAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("pl.lechowicz.performance_trace.monitor.AopConfiguration.customMonitor()");
        return new DefaultPointcutAdvisor(pointcut, customPerformanceMonitorInterceptor());
    }
}