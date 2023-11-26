package sample.helidon.processor;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

@Logged
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class LoggingInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);
    @Inject
    ImportantClassChecker importance;

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        Class<?> clazz = ctx.getMethod().getDeclaringClass();
        Level level = importance.isImportant(clazz) ? Level.WARN : Level.INFO;
        try {
            log.atLevel(level).setMessage("Starting work").log();
            return ctx.proceed();
        } finally {
            log.atLevel(level).setMessage("Work finished").log();
        }
    }
}
