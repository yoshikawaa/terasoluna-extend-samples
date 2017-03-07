package com.example.app.sample;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("sample")
public class SampleController {

    @ModelAttribute
    public SampleForm sampleForm() {
        return new SampleForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "sample/home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String validate(@Validated SampleForm form, BindingResult result) {
        if (result.hasErrors()) {
            return index();
        }
        return "redirect:/sample";
    }
}
