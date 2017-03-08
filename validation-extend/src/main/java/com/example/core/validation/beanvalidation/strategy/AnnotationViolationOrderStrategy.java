package com.example.core.validation.beanvalidation.strategy;

import java.lang.reflect.AnnotatedElement;
import java.util.Comparator;

import javax.validation.ConstraintViolation;

import org.springframework.util.StringUtils;

import com.example.core.validation.beanvalidation.annotation.ViolationOrder;
import com.example.core.validation.beanvalidation.ViolationOrderedLocalValidatorFactoryBean;

/**
 * strategy class of {@link ViolationOrderedLocalValidatorFactoryBean}.
 */
public class AnnotationViolationOrderStrategy implements Comparator<ConstraintViolation<?>> {

    /*
     * (非 Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(ConstraintViolation<?> left, ConstraintViolation<?> right) {
        String l = getPropertyPathString(left);
        String r = getPropertyPathString(right);

        int annotatedOrder = getAnnotatedOrder(left, l).compareTo(getAnnotatedOrder(right, r));
        return (annotatedOrder == 0) ? l.compareTo(r) : annotatedOrder;
    }

    /**
     * get property path.
     * @param violation target violation
     * @return property path
     */
    protected String getPropertyPathString(ConstraintViolation<?> violation) {
        return violation.getPropertyPath().toString();
    }

    /**
     * get annotated violation order.
     * @param violation target violation
     * @param path property path
     * @return annotated violation order
     */
    protected Integer getAnnotatedOrder(ConstraintViolation<?> violation, String path) {
        AnnotatedElement element = null;
        try {
            element = (StringUtils.isEmpty(path)) ? violation.getRootBeanClass() : violation.getRootBeanClass().getDeclaredField(path);
        } catch (NoSuchFieldException | SecurityException e1) {
            // will not reach here.
        }

        if (element.isAnnotationPresent(ViolationOrder.class)) {
            ViolationOrder annotation = element.getAnnotation(ViolationOrder.class);
            return Math.min(annotation.value(), annotation.order());
        } else {
            return Integer.MAX_VALUE;
        }
    }
}
