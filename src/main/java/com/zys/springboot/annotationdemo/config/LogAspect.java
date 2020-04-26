    package com.zys.springboot.annotationdemo.config;

    import com.zys.springboot.annotationdemo.entity.SystemLog;
    import com.zys.springboot.annotationdemo.service.SystemLogService;
    import org.aspectj.lang.JoinPoint;
    import org.aspectj.lang.Signature;
    import org.aspectj.lang.annotation.AfterReturning;
    import org.aspectj.lang.annotation.AfterThrowing;
    import org.aspectj.lang.annotation.Aspect;
    import org.aspectj.lang.annotation.Pointcut;
    import org.aspectj.lang.reflect.MethodSignature;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Component;

    import java.lang.reflect.Method;
    import java.util.UUID;

    @Aspect
    @Component("logAspect")
    public class LogAspect {
        @Autowired
        private SystemLogService logService;

        private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

        // 配置织入点
        @Pointcut("@annotation(com.zys.springboot.annotationdemo.config.Log)")
        public void logPointCut() {
        }

        /**
         * 前置通知 用于拦截操作，在方法返回后执行
         *
         * @param joinPoint 切点
         */
        @AfterReturning(pointcut = "logPointCut()")
        public void doBefore(JoinPoint joinPoint) {
            handleLog(joinPoint, null);
        }

        /**
         * 拦截异常操作，有异常时执行
         *
         * @param joinPoint
         * @param e
         */
        @AfterThrowing(value = "logPointCut()", throwing = "e")
        public void doAfter(JoinPoint joinPoint, Exception e) {
            handleLog(joinPoint, e);
        }

        private void handleLog(JoinPoint joinPoint, Exception e) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            SystemLog systemLog = new SystemLog();
            //获取方法名
            String functionName = signature.getDeclaringTypeName() + "." + signature.getName() + "()";
            //获取注解对象
            Log annotation = signature.getMethod().getAnnotation(Log.class);
            if (annotation != null) {
                systemLog.setId(UUID.randomUUID().toString().replace("-", ""));
                systemLog.setMethod(functionName);
                //获取注解中对方法的描述信息
                systemLog.setTitle(annotation.title());
                systemLog.setDescribe(annotation.describe());
                if (e != null) {
                    String err = e.getMessage();
                    if (err != null && err.length() > 4000) {
                        err = err.substring(0, 4000);
                    }
                    systemLog.setError(err);
                }
            }
            //记录到数据库
            logService.createLog(systemLog);
        }

        /**
         * 是否存在注解，如果存在就获取
         */
        private static Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            if (method != null) {
                return method.getAnnotation(Log.class);
            }
            return null;
        }

    }