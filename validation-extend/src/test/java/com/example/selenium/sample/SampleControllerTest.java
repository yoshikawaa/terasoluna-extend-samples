package com.example.selenium.sample;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import static org.openqa.selenium.By.id;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/selenium-context.xml" })
public class SampleControllerTest {
    @Value("http://${selenium.host}:${selenium.port}/${selenium.context}/")
    private String contextRoot;

    @Inject
    private WebDriver driver;

    @Before
    public void before() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(contextRoot + "sample");
    }

    @Test
    public void testViolationOrder() {
        List<String> expected = Arrays.asList("\"\"", "\"name\"", "\"email\"");
        int validate = 10;

        for (int i = 0; i < validate; i++) {
            setText(id("name"), "#");
            setText(id("email"), "a");
            driver.findElement(id("submit")).click();

            String error = driver.findElement(id("null.errors")).getText();
            assertThat(error, stringContainsInOrder(expected));
        }
    }

    private void setText(By by, String text) {
        WebElement e = driver.findElement(by);
        e.clear();
        e.sendKeys(text);
    }

    @After
    public void after() {
        driver.quit();
    }
}
