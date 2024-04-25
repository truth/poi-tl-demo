# poi-tl examples 
> This project is the example of the [poi-tl](https://github.com/Sayi/poi-tl)
> You can use this project to learn how to use the poi-tl, and how to use the poi-tl to generate word document. 
> I expaned the poi-tl to support more features, and  use the beanshell to support the dynamic data. the tag of beanshell is `<!beanshellname>`, the content of the tag is the script . the script will be executed when the document is generated.
> If you have any question, please contact me. 
> Email: truthwzl at gmail dot com 

## 1. How to use beanshell 

```
<!beanshellname> 
```
**beanshellname** is the name of the beanshell as fllow:
``` java 
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.Tables;
import com.deepoove.poi.data.style.BorderStyle;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.deepoove.poi.util.TableTools;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.tests.*;

// some functioins 

// data  is the inspect variable of the beanshell.
int titleStartRow=0;
if (null == data) return;
DetailData detailData = (DetailData) data;

// title
String  title = detailData.getTitle();
// table is the XWPFTable variable of the beanshell. 
table.removeRow(0);

// ...  more details are in resources/scripts/data_script.bash .

```
