package avans.ivh11a1.facturatie.aop;

import avans.ivh11a1.facturatie.domain.Exception.SecurityException;
import avans.ivh11a1.facturatie.domain.administration.Role;
import avans.ivh11a1.facturatie.service.UserAdministrationService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kevin on 21-3-2017.
 */
@Aspect
@Component
public class SecurityInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityInterceptor.class);
    private final UserAdministrationService userService;

    @Autowired
    public SecurityInterceptor(UserAdministrationService userService) {
        LOGGER.debug("Security Interceptor created");
        this.userService = userService;
    }

    @Before("((@within(SecurityAnnotation) || @annotation(SecurityAnnotation)))")
    public void checkSecurity(JoinPoint joinPoint) throws SecurityException {
        LOGGER.info("Intercepting creation of a component");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        if (!userService.isLoggedIn()){
            throw new SecurityException("You need to log in to view these components", "NotLoggedIn");
        }
        SecurityAnnotation myAnnotation = joinPoint.getTarget().getClass().getAnnotation(SecurityAnnotation.class);
        if (myAnnotation == null) {
            myAnnotation = method.getAnnotation(SecurityAnnotation.class);
        }

        boolean securityAnnotationPresent = (myAnnotation != null);

        if (securityAnnotationPresent) {
            boolean userHasRole = verifyRole(myAnnotation);
            if (!userHasRole) {
                String msg = "Current user doesn't have permission to have this component created";
                LOGGER.info(msg);
                throw new SecurityException(msg, "NoPermission");
            }
        }
        LOGGER.info("Current user has required permissions for creating a component");
    }

    /**
     * The function verifies if the current user has sufficient privilages to
     * have the component built
     *
     * @param annotation
     * @return
     */
    private boolean verifyRole(Annotation annotation) {
        LOGGER.info("Security annotation is present so checking if the user can use it");
        SecurityAnnotation annotationRule = (SecurityAnnotation) annotation;
        List<Role> requiredRolesList = Arrays.asList(annotationRule.allowedRole());
        Role userRole = userService.getUserRole();
        return requiredRolesList.contains(userRole);
    }
}
