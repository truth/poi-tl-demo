package com.example.component;

import com.example.annotation.MyCustomComponent;
import org.springframework.stereotype.Component;

@Component
@MyCustomComponent
public class MyCustomBean2 implements IBean {

    @Override
    public String getName() {
        return "bean2";
    }
}