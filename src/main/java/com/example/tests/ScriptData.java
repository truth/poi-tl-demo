package com.example.tests;

import lombok.Data;

@Data
public class ScriptData {
    private String url;
    private Object data;

    public static ScriptData build(String script,Object data) {
        ScriptData scriptData = new ScriptData();
        scriptData.setData(data);
        scriptData.setUrl(script);
        return scriptData;
    }
}
