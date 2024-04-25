package com.example.tests;

import bsh.Interpreter;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 调用beanshell脚本,来生成word文件。
 * 所有脚本逻辑和TestPoi相同，语法上做了调整。
 */
public class TestBeanShell {
    public static void main(String[] args) {
        try {
            // 创建 BeanShell 解释器
            Interpreter interpreter = new Interpreter();
            try {
                // 使用类加载器获取资源文件的输入流
                InputStream inputStream = TestBeanShell.class.getClassLoader().getResourceAsStream("scripts/poi_script.bsh");
                // 运行 BeanShell 脚本
                //interpreter.source();
                interpreter.eval(new InputStreamReader((inputStream)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 调用脚本中的方法
            //interpreter.eval("greet(\"John\")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
