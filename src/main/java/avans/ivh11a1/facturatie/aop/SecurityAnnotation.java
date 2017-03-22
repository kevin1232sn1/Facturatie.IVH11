package avans.ivh11a1.facturatie.aop;

import avans.ivh11a1.facturatie.domain.administration.Role;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by kevin on 21-3-2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityAnnotation {
    Role[] allowedRole();
}
