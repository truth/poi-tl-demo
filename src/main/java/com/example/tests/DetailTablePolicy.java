package com.example.tests;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.deepoove.poi.util.TableTools;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.ArrayList;
import java.util.List;

public class DetailTablePolicy extends DynamicTableRenderPolicy {

  private  void insertOneRow(XWPFTable table,int rowIndex,String content, int cells) throws Exception {
    XWPFTableRow insertNewTableRow = table.insertNewTableRow(rowIndex);
    List<String> list = new ArrayList<>();

    for(int n=0;n<cells;n++) {
      list.add(content);
      insertNewTableRow.createCell();
    }
    RowRenderData row0 = Rows.of(list.toArray(new String[0])).textColor("000000")
            .bgColor("FFFFFF").center().create();
    TableRenderPolicy.Helper.renderRow(insertNewTableRow,row0);
    TableTools.mergeCellsHorizonal(table,rowIndex,0,cells);
  }
  @Override
  public void render(XWPFTable table, Object data) throws Exception {
    int titleStartRow=0;
    if (null == data) return;
    DetailData detailData = (DetailData) data;

    // title
    String  title = detailData.getTitle();
    if (null != title) {
      //table.removeRow(titleStartRow);
      table.removeRow(0);
      // 1. 插入标题
      XWPFTableRow insertNewTableRow = table.insertNewTableRow(titleStartRow);
      titleStartRow++;

      RowRenderData row0 = Rows.of(title,"","","","","").textColor("FFFFFF")
              .bgColor("4472C4").center().create();
      for(int n=0;n<6;n++) insertNewTableRow.createCell();
      TableRenderPolicy.Helper.renderRow(insertNewTableRow,row0);
      TableTools.mergeCellsHorizonal(table,0,0,6);

      // 外循环
      for (int i = 0; i < detailData.getOuterLoop().size(); i++) {
        OuterLoop outerLoop = detailData.getOuterLoop().get(i);
        List<MyOption>  options  = outerLoop.getOptions();
        List<MyOption>  options2 = outerLoop.getOptions2();
        int rows = options.size()>options2.size()?options.size():options2.size();
        insertOneRow(table,titleStartRow,"内循环标题"+i,6);titleStartRow++;
        int startRow = titleStartRow;
        //内循环
        for(int iRow=0;iRow<rows;iRow++) {
          XWPFTableRow row2 = table.insertNewTableRow(titleStartRow); titleStartRow++;
          for(int n=0;n<6;n++) row2.createCell();
          String col1 = iRow==0?outerLoop.getTitle1():" ";
          String col2=options.size()>iRow?options.get(iRow).getKey():" ";
          String col3=options.size()>iRow?options.get(iRow).getValue():" ";

          String col4 = iRow==0?outerLoop.getTitle2():" ";
          String col5 = options2.size()>iRow?options2.get(iRow).getKey():" ";
          String col6 = options2.size()>iRow?options2.get(iRow).getValue():" ";
          RowRenderData dataRow = Rows.of(col1,col2,col3, col4,col5,col6)
                    .textColor("000000").bgColor("FFFFFF").center().create();
          if(row2.getTableCells().size()==dataRow.getCells().size()){
            TableRenderPolicy.Helper.renderRow(row2,dataRow);
          }else {
            System.out.println(row2.getTableCells().size()+","+dataRow.getCells().size());
          }
        }
        // 合并单元格
        TableTools.mergeCellsVertically(table,0,startRow,titleStartRow-1);
        TableTools.mergeCellsVertically(table,3,startRow,titleStartRow-1);
      }
    }
  }
}