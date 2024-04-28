package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
@Target(ElementType.TYPE)  
@Retention(RetentionPolicy.RUNTIME)  
public @interface MyCustomComponent {  
    // 可以定义注解的属性（如果需要的话）  
}