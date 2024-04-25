package com.example.tests;

import bsh.Interpreter;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.io.InputStream;
import java.io.InputStreamReader;

public class ScriptPolicy extends DynamicTableRenderPolicy {
    @Override
    public void render(XWPFTable xwpfTable, Object o) throws Exception {
        if(o instanceof  ScriptData) {
            ScriptData scriptData = (ScriptData) o;
            try {
                // 创建 BeanShell 解释器
                Interpreter interpreter = new Interpreter();
                try {
                    // 使用类加载器获取资源文件的输入流
                    InputStream inputStream = TestBeanShell.class.getClassLoader().getResourceAsStream(scriptData.getUrl());
                    // 运行 BeanShell 脚本
                    //interpreter.source();
                    interpreter.set("data",scriptData.getData());
                    interpreter.set("table",xwpfTable);
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
}
