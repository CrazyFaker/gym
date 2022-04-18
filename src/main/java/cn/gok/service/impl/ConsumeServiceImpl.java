package cn.gok.service.impl;

import cn.gok.entity.Consume;
import cn.gok.mapper.ConsumeMapper;
import cn.gok.service.ConsumeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumeServiceImpl implements ConsumeService {

    @Autowired(required = false)
    private ConsumeMapper consumeMapper;

    @Override
    public PageInfo<Consume> list(String searchKey, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Consume> list=consumeMapper.list(searchKey);
        PageInfo<Consume> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int save(Consume consume) {
        if (consume.getVname() != null || consume.getVname() !="" ){
            return consumeMapper.save(consume);
        }else{
            return 0;
        }

    }

    @Override
    public HSSFWorkbook exportToExcel(List<Consume> consumes) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("cosume_info");

        HSSFRow row = null;
        row = sheet.createRow(0);//创建第一行
        row.setHeight((short) 800);// 设置行高
        HSSFCell c00 = row.createCell(0); //创建第一个单元格
        c00.setCellValue("消费管理列表");//设置单元格内容

        //设置标题样式
        c00.setCellStyle(ExcelImportUtils.createTitleCellStyle(wb));

        //合并单元格(firstRow:起始行,lastRow:结束行,firstCol:起始列,lastCol:结束列)
        CellRangeAddress rowRegion = new CellRangeAddress(0,0,0,4);
        sheet.addMergedRegion(rowRegion);

        //创建表头行，并设置样式
        row = sheet.createRow(1); //创建第二行
        row.setHeight((short)500);//设置行高
        String[] row_head = {"商品名称","购买数量","价格","总消费","所属人"};
        for(int i=0;i<row_head.length;i++){ //创建表头
            HSSFCell tempCell = row.createCell(i);
            tempCell.setCellValue(row_head[i]); //设置单元格内容

            //设置表头样式
            tempCell.setCellStyle(ExcelImportUtils.createHeadCellStyle(wb));
        }

        //定义表格内容(每行数据)

        //集合(vips)中有多少个元素就生成多少行
        for(int i=0;i<consumes.size();i++) {

            row = sheet.createRow(i + 2);
            Consume consume = consumes.get(i);
            for (int j = 0; j < 5; j++) { //每行有4列
                HSSFCell tempCell = row.createCell(j); //设置单元格内容
                //设置内容样式
                tempCell.setCellStyle(ExcelImportUtils.createContentCellStyle(wb));

                if (j == 0) {
                    tempCell.setCellValue(consume.getName());
                }  else if (j == 1) {
                    tempCell.setCellValue(consume.getNumber());
                } else if (j == 2) {
                    tempCell.setCellValue(consume.getPrice());
                } else if (j == 3) {
                    tempCell.setCellValue(consume.getPrice()*consume.getNumber());
                } else if (j == 4) {
                    tempCell.setCellValue(consume.getVname());
                }
            }
        }
        // sheet.setDefaultRowHeight((short)(16.5*20)); 设置默认行高
        //列宽自适应
        for(int i=0;i<5;i++){
            sheet.autoSizeColumn(i);
        }
        return wb;

    }


    @Override
    public int delete(Consume consume) {
        return consumeMapper.delete(consume);
    }

    @Override
    public List<Consume> getconsumes() {
        return consumeMapper.getconsumes();
    }
}
