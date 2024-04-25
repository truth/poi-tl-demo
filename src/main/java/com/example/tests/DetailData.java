package com.example.tests;

import com.alibaba.fastjson2.JSON;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DetailData {
    private String title;
    private List<OuterLoop> outerLoop;

    public static DetailData generateData() {
        DetailData detailData = new DetailData();
        detailData.setTitle("表格标题");
        List<OuterLoop> loops = new ArrayList<>();
        for(int i=0;i<5;i++) {
            OuterLoop outer = new OuterLoop();
            outer.setTitle1("子标题1");
            List<MyOption> options = new ArrayList<>();
            for(int j1=0;j1<5;j1++) {
                MyOption option = new MyOption();
                option.setKey("key"+j1);
                option.setValue("value"+j1);
                options.add(option);
            }
            outer.setOptions(options);

            outer.setTitle2("子标题2");
            List<MyOption> options2 = new ArrayList<>();
            for(int j1=0;j1<4;j1++) {
                MyOption option = new MyOption();
                option.setKey("key"+j1);
                option.setValue("value"+j1);
                options2.add(option);
            }
            outer.setOptions2(options2);
            loops.add(outer);
        }
        detailData.setOuterLoop(loops);
        System.out.println(JSON.toJSONString(detailData));
        return detailData;
    }
}
