package cn.gok.service.impl;

import cn.gok.entity.Commodity;
import cn.gok.mapper.CommodityMapper;
import cn.gok.service.CommodityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired(required = false)
    private CommodityMapper commodityMapper;
    @Override
    public PageInfo<Commodity> list(String searchKey, String type,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Commodity> list=commodityMapper.list(searchKey,type);
        PageInfo<Commodity> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }


    @Override
    public int save(Commodity commodity) {

        return commodityMapper.save(commodity);
    }



    @Override
    public int update(Commodity commodity) {

        return commodityMapper.update(commodity);
    }



    @Override
    public int delete(Commodity commodity) {

        return commodityMapper.delete(commodity);
    }

    @Override
    public List<Commodity> getcommditys() {
        return commodityMapper.getcommoditys();
    }

    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {
        boolean notnull = false;
        List<Commodity> commoditiesList = new ArrayList<>();
        if(!fileName.matches("^.+\\.(?i)(xls)$")){ //
            throw new Exception("上传文件格式不正确");
        }
        boolean isExcel2007 = true;
        if(fileName.matches("^.+\\.(?i)(xlsx)$")){
            isExcel2007 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2007) {
            wb = new HSSFWorkbook(is);
        }else{
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet != null){
            notnull = true;
        }
        Commodity commodity= null;
        for(int r=2;r<=sheet.getLastRowNum();r++){ //r = 1 表示从第二行开始循环 如果你的第二行开始是数据
            Row row = sheet.getRow(r);//通过sheet表单对象得到行对象
            if(row == null){
                continue;
            }
            //sheet.getLastRowNum() 的值是 10，所以Excel表中的数据至少是10条；不然报错 NullPointerException
            commodity = new Commodity();
            row.getCell(0).setCellType(CellType.STRING);//将每一行第一个单元格设置为字符串类型
            row.getCell(1).setCellType(CellType.STRING);
            row.getCell(2).setCellType(CellType.STRING);
            row.getCell(3).setCellType(CellType.STRING);
            row.getCell(4).setCellType(CellType.STRING);
            String cname =row.getCell(0).getStringCellValue();//得到每一行第一个单元格的值
            if(cname==null || cname.isEmpty()){
                throw new Exception("导入失败(第\"+(r+1)+\"行，商品名称未填写)");
            }
            String number =row.getCell(1).getStringCellValue();//得到每一行第一个单元格的值
            if(number == null || number.isEmpty()){
                throw new Exception("导入失败(第\"+(r+1)+\"行，库存未填写)");
            }
            String price = row.getCell(2).getStringCellValue();//得到每一行的第二个单元格的值
            if(price == null || price.isEmpty()){
                throw new Exception("导入失败(第\"+(r+1)+\"行，价格未填写)");
            }
            String discount = row.getCell(3).getStringCellValue();//得到每一行的第三个单元格的值
            if(discount == null || discount.isEmpty()){
                throw new Exception("导入失败(第\"+(r+1)+\"行，折扣未填写)");
            }

            String sketch = row.getCell(4).getStringCellValue();//得到每一行的第四个单元格的值
            if(sketch == null || sketch.isEmpty()){
                throw new Exception("导入失败(第\"+(r+1)+\"行，简述未填写)");
            }


            //完整的循环一次 就组成了一个对象
            commodity.setCname(cname);
            commodity.setNumber(Integer.valueOf(number));
            commodity.setPrice(Double.valueOf(price));
            commodity.setDiscount(Integer.valueOf(discount));
            commodity.setSketch(sketch);

            commoditiesList.add(commodity);
        }
        for(Commodity commodityResord : commoditiesList){
            Long id = commodityResord.getId();
            Commodity commodity1 = commodityMapper.findById(id);
            if(commodity1 == null){
                commodityMapper.save(commodityResord);
                System.out.println("==>插入："+commodityResord);
            }else{
                commodityMapper.update(commodityResord);
                System.out.println("==>更新："+commodityResord);
            }
        }
        return notnull;
    }

    @Override
    public HSSFWorkbook exportToExcel(List<Commodity> commoditys) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("commodity_info");

        HSSFRow row = null;
        row = sheet.createRow(0);//创建第一行
        row.setHeight((short) 800);// 设置行高
        HSSFCell c00 = row.createCell(0); //创建第一个单元格
        c00.setCellValue("商品管理列表");//设置单元格内容

        //设置标题样式
        c00.setCellStyle(ExcelImportUtils.createTitleCellStyle(wb));

        //合并单元格(firstRow:起始行,lastRow:结束行,firstCol:起始列,lastCol:结束列)
        CellRangeAddress rowRegion = new CellRangeAddress(0,0,0,4);
        sheet.addMergedRegion(rowRegion);

        //创建表头行，并设置样式
        row = sheet.createRow(1); //创建第二行
        row.setHeight((short)500);//设置行高
        String[] row_head = {"商品名称","库存","价格","折扣","简述"};
        for(int i=0;i<row_head.length;i++){ //创建表头
            HSSFCell tempCell = row.createCell(i);
            tempCell.setCellValue(row_head[i]); //设置单元格内容

            //设置表头样式
            tempCell.setCellStyle(ExcelImportUtils.createHeadCellStyle(wb));
        }

        //定义表格内容(每行数据)

        //集合(vips)中有多少个元素就生成多少行
        for(int i=0;i<commoditys.size();i++) {

            row = sheet.createRow(i + 2);
            Commodity commodity = commoditys.get(i);
            for (int j = 0; j < 5; j++) { //每行有4列
                HSSFCell tempCell = row.createCell(j); //设置单元格内容
                //设置内容样式
                tempCell.setCellStyle(ExcelImportUtils.createContentCellStyle(wb));

                if (j == 0) {
                    tempCell.setCellValue(commodity.getCname());
                }  else if (j == 1) {
                    tempCell.setCellValue(commodity.getNumber());
                } else if (j == 2) {
                    tempCell.setCellValue(commodity.getPrice());
                } else if (j == 3) {
                    tempCell.setCellValue(commodity.getDiscount());
                } else if (j == 4) {
                    tempCell.setCellValue(commodity.getSketch());
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
    public int updateImage(String image, Long id) {
        return commodityMapper.updateImage(image,id);
    }

    @Override
    public int updateNumber(Long id) {
        return commodityMapper.updateNumber(id);
    }

}

