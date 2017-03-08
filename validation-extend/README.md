# validation-extend

The Extension and sample application for ordering `ConstraintViolation`.

## Component

* ViolationOrderedLocalValidatorFactoryBean

    The extension of　`LocalValidatorFactoryBean` that order `ConstraintViolation` using violation order strategy.

* AnnotationViolationOrderStrategy

    The strategy for `ViolationOrderedLocalValidatorFactoryBean` that order by `ViolationOrder#order` ascending.
    If `ViolationOrder` not found, order by alphabet ascending.

* ViolationOrder

    The annotation to define violation order.

## Way to Use

* Define bean as follows:

```xml
<bean id="validator" class="com.example.core.validation.beanvalidation.ViolationOrderedLocalValidatorFactoryBean">
	<property name="violationOrderStrategy">
		<bean class="com.example.core.validation.beanvalidation.strategy.AnnotationViolationOrderStrategy" />
	</property>
</bean>
```

* Apply to Spring MVC as follows:

```xml
<mvc:annotation-driven validator="validator">
```

* Annotate models as follows:

```java
@ViolationOrder(1)
public class SampleForm {
    @ViolationOrder(2)
    private String name;
}
```