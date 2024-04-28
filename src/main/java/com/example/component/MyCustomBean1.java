package com.example.component;

import com.example.annotation.MyCustomComponent;
import org.springframework.stereotype.Component;

@Component
@MyCustomComponent
public class MyCustomBean1  implements IBean {
    @Override
    public String getName() {
        return "bean1";
    }
}