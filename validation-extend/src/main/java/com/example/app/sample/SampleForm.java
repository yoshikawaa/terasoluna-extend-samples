package com.example.app.sample;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.example.core.validation.beanvalidation.annotation.ViolationOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleForm {

    @ViolationOrder(1)
    @NotEmpty
    @Size(min = 3, max = 10)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String name;

    @ViolationOrder(2)
    @NotEmpty
    @Email
    private String email;
}
