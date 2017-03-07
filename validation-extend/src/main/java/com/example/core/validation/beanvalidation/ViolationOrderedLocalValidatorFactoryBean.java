package com.example.core.validation.beanvalidation;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;

import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * extended spring {@link LocalValidatorFactoryBean} that order violations by injected strategy.
 */
public class ViolationOrderedLocalValidatorFactoryBean extends LocalValidatorFactoryBean {
    /**
     * strategy of violation order.
     */
    private Comparator<ConstraintViolation<Object>> violationOrderStrategy;

    /**
     * set {@code violationOrderStrategy}.
     * @param violationOrderStrategy strategy of violation order
     */
    public void setViolationOrderStrategy(Comparator<ConstraintViolation<Object>> violationOrderStrategy) {
        this.violationOrderStrategy = violationOrderStrategy;
    }

    /*
     * (非 Javadoc)
     * @see org.springframework.validation.beanvalidation.SpringValidatorAdapter#processConstraintViolations(java.util.Set,
     * org.springframework.validation.Errors)
     */
    @Override
    protected void processConstraintViolations(Set<ConstraintViolation<Object>> violations, Errors errors) {
        TreeSet<ConstraintViolation<Object>> orderedViolations = new TreeSet<ConstraintViolation<Object>>(violationOrderStrategy);
        orderedViolations.addAll(violations);
        super.processConstraintViolations(orderedViolations, errors);
    }
}
