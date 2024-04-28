package com.example.config;

import com.example.annotation.MyCustomComponent;
import com.example.component.IBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;  
import org.springframework.context.ApplicationContextAware;  
import org.springframework.stereotype.Component;  
  
import java.util.HashMap;  
import java.util.Map;  

@Slf4j
@Component  
public class MyCustomComponentFactory implements ApplicationContextAware {  
  
    private ApplicationContext applicationContext;  
    private Map<String, Object> customComponents = new HashMap<>();  
  
    @Override  
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {  
        this.applicationContext = applicationContext;  
        // 初始化时搜集所有带有自定义注解的bean  
        initCustomComponents();  
    }  
  
    private void initCustomComponents() {  
        // 获取所有带有自定义注解的bean  
        String[] beanNames = applicationContext.getBeanNamesForType(IBean.class);
        for (String beanName : beanNames) {  
            if (applicationContext.findAnnotationOnBean(beanName, MyCustomComponent.class) != null) {
                Object bean = applicationContext.getBean(beanName);
                log.debug(beanName+","+bean);
                customComponents.put(beanName, bean);  
            }  
        }  
    }  
  
    public Map<String, Object> getCustomComponents() {  
        return customComponents;  
    }  
  
    public Object getCustomComponent(String beanName) {  
        return customComponents.get(beanName);  
    }  
}