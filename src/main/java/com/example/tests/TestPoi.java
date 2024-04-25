package com.example.tests;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.Tables;
import com.deepoove.poi.data.style.BorderStyle;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import com.example.tests.DetailData;
import com.example.tests.ImageRow;
import com.example.utils.PathUtil;
import lombok.Data;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TestPoi {
    public static void main(String[] args) {
        test0();
    }
    public static void test0() {
        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
        Configure config = Configure.builder().addPlugin('!',new ScriptPolicy())
                .bind("images", policy).bind("labors", policy).bind("detail_table", new ScriptPolicy()).build();

        XWPFTemplate template = XWPFTemplate.compile(PathUtil.getDocsRoot()+"\\template.docx",config).render(
                new HashMap<String, Object>(){{
                    put("title", "Hi, poi-tl Word模板引擎");
                    put("image", PathUtil.getDefaultImg());
                    put("table0", Tables.of(new String[][] {
                            new String[] { "00", "01" },
                            new String[] { "10", "11" }
                    }).border(BorderStyle.DEFAULT).create());

                    List<ImageRow> rows = new ArrayList<ImageRow>();
                    for(int i=0;i<5;i++){
                        ImageRow imageRow = new ImageRow();
                        imageRow.setImage0(PathUtil.getDefaultImg());
                        imageRow.setImage1(PathUtil.getDefaultImg());
                        imageRow.setTable0(Tables.of(new String[][] {
                                new String[] { "00", "01" },
                                new String[] { "10", "11" }
                        }).border(BorderStyle.DEFAULT).create());
//                    List<String> cols = new ArrayList<>();
//                    cols.add("C:\\Users\\truth\\Desktop\\logo.png");
//                    cols.add("C:\\Users\\truth\\Desktop\\logo.png");
                        rows.add(imageRow);
                    }

                    put("images", rows);
                    //动态表格
                    put("detail_table", ScriptData.build("scripts/data_script.bsh",DetailData.generateData()));
                    put("detail_table1", ScriptData.build("scripts/data_script.bsh",DetailData.generateData()));
                }});
        try {
            template.writeAndClose(new FileOutputStream(PathUtil.getDocsRoot()+"\\output.docx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void test1 () throws IOException {
        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph para = doc.createParagraph();
        XWPFRun createRun = para.createRun();
        createRun.setText("{{?list}}\n");
        createRun = para.createRun();
        createRun.setText("\nindex:{{_index+1}}");
        createRun = para.createRun();
        createRun.setText("\n_is_first:{{_is_first}}");
        createRun = para.createRun();
        createRun.setText("\n_is_last:{{_is_last?\"last\":\"\"}},item:{{=#this}}");
        createRun = para.createRun();
        createRun.setText("\n_has_next:{{_has_next}}");
        createRun = para.createRun();
        createRun.setText("\n_is_even_item:{{_is_even_item}}");
        createRun = para.createRun();
        createRun.setText("\n_is_odd_item:{{_is_odd_item}}");
        createRun = para.createRun();
        createRun.setText("{{/list}}");

        XWPFTemplate template = XWPFTemplate.compile(XWPFTestSupport.readInputStream(doc), Configure.builder().useSpringEL().build());
        template.render(new HashMap<String, Object>() {
            {
                put("list", Arrays.asList("1", "2", "3", "4"));
            }
        });
        XWPFDocument newDocument = XWPFTestSupport.readNewDocument(template);
        String text = newDocument.getParagraphArray(0).getText();
        System.out.println(text);
//        assertEquals(
//                "index:1_is_first:true_is_last:false_has_next:true_is_even_item:false_is_odd_item:trueindex:2_is_first:false_is_last:false_has_next:true_is_even_item:true_is_odd_item:falseindex:3_is_first:false_is_last:false_has_next:true_is_even_item:false_is_odd_item:trueindex:4_is_first:false_is_last:true_has_next:false_is_even_item:true_is_odd_item:false",
//                text);
    }
}
