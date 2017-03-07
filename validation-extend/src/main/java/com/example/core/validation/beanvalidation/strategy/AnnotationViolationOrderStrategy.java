package com.example.core.validation.beanvalidation.strategy;

import java.util.Comparator;

import javax.validation.ConstraintViolation;

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
        ViolationOrder annotation = null;
        try {
            annotation = violation.getRootBeanClass().getDeclaredField(path).getAnnotation(ViolationOrder.class);
        } catch (NoSuchFieldException | SecurityException e1) {
            // will not reach here.
        }
        return (annotation == null) ? Integer.MAX_VALUE : Math.min(annotation.value(), annotation.order());
    }
}
