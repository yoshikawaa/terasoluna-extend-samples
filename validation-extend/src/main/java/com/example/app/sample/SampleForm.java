package com.example.app.sample;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.ScriptAssert;

import com.example.core.validation.beanvalidation.annotation.ViolationOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ViolationOrder(1)
@ScriptAssert(lang = "javascript", script = "_this.name.equals(_this.email)")
public class SampleForm {

    @ViolationOrder(2)
    @NotEmpty
    @Size(min = 3, max = 10)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String name;

    @NotEmpty
    @Email
    private String email;
}
