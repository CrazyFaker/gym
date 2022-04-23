package cn.gok.service.impl;

import cn.gok.entity.Vip;
import cn.gok.mapper.VipMapper;
import cn.gok.service.VipService;
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
public class VipServiceImpl implements VipService {

    @Autowired(required = false)
    private VipMapper vipMapper;

    @Override
    public PageInfo<Vip> list(String searchKey, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Vip> list=vipMapper.list(searchKey);
        PageInfo<Vip> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Vip findVipByName(String identity) {
            return vipMapper.findVipByName(identity);

    }

    @Override
    public int save(Vip vip) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
        if(vip.getId() != null ) {
            return 0;
        }

        return vipMapper.save(vip);

    }

    @Override
    public int update(Vip vip) {
        if(vip.getId() != null ) {
            return vipMapper.update(vip);
        } else {
            return 0;
        }

    }



    @Override
    public int delete(Vip vip) {
            if(vip.getId() !=null) {
                return vipMapper.delete(vip);
            }else{
                return 0;
            }

    }

    @Override
    public List<Vip> getvips() {
        return vipMapper.getvips();
    }
    //导入
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {
        boolean notnull = false;
        List<Vip> vipsList = new ArrayList<>();
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
        Vip vip= null;
        for(int r=2;r<=sheet.getLastRowNum();r++){ //r = 1 表示从第二行开始循环 如果你的第二行开始是数据
            Row row = sheet.getRow(r);//通过sheet表单对象得到行对象
            if(row == null){
                continue;
            }
            //sheet.getLastRowNum() 的值是 10，所以Excel表中的数据至少是10条；不然报错 NullPointerException
            vip = new Vip();
            row.getCell(0).setCellType(CellType.STRING);//将每一行第一个单元格设置为字符串类型
            row.getCell(1).setCellType(CellType.STRING);
            row.getCell(2).setCellType(CellType.STRING);
            row.getCell(3).setCellType(CellType.STRING);
            row.getCell(4).setCellType(CellType.STRING);
            row.getCell(5).setCellType(CellType.STRING);
            String name =row.getCell(0).getStringCellValue();//得到每一行第一个单元格的值
            if(name==null || name.isEmpty()){
                throw new Exception("导入失败(第\"+(r+1)+\"行，名字未填写)");
            }
            String sex =row.getCell(1).getStringCellValue();//得到每一行第一个单元格的值
            if(sex == null || sex.isEmpty()){
                throw new Exception("导入失败(第\"+(r+1)+\"行，性别未填写)");
            }
            String tel = row.getCell(2).getStringCellValue();//得到每一行的第二个单元格的值
            if(tel == null || tel.isEmpty()){
                throw new Exception("导入失败(第\"+(r+1)+\"行，电话未填写)");
            }
            String address = row.getCell(3).getStringCellValue();//得到每一行的第三个单元格的值
            if(address == null || address.isEmpty()){
                throw new Exception("导入失败(第\"+(r+1)+\"行，地址未填写)");
            }

            String age = row.getCell(4).getStringCellValue();//得到每一行的第四个单元格的值
            if(age == null || age.isEmpty()){
                throw new Exception("导入失败(第\"+(r+1)+\"行，年龄未填写)");
            }
            String identity = row.getCell(5).getStringCellValue();//得到每一行的第四个单元格的值
            if(identity == null || identity.isEmpty()){
                throw new Exception("导入失败(第\"+(r+1)+\"行，身份证未填写)");
            }



            //完整的循环一次 就组成了一个对象
            vip.setName(name);
            vip.setSex(sex);
            vip.setTel(tel);
            vip.setAddress(address);
            vip.setAge(age);
            vip.setIdentity(identity);

            vipsList.add(vip);
        }
        for(Vip vipResord : vipsList){
            Long id = vipResord.getId();
            Vip vip1 = vipMapper.findById(id);
            if(vip1 == null){
                vipMapper.save(vipResord);
                System.out.println("==>插入："+vipResord);
            }else{
                vipMapper.update(vipResord);
                System.out.println("==>更新："+vipResord);
            }
        }
        return notnull;
    }

    //导出
    @Override
    public HSSFWorkbook exportToExcel(List<Vip> vips) { //将集合中的数据存储到Execl工作簿中
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("vip_info");

        HSSFRow row = null;
        row = sheet.createRow(0);//创建第一行
        row.setHeight((short) 800);// 设置行高
        HSSFCell c00 = row.createCell(0); //创建第一个单元格
        c00.setCellValue("会员管理列表");//设置单元格内容

//设置标题样式
        c00.setCellStyle(ExcelImportUtils.createTitleCellStyle(wb));

//合并单元格(firstRow:起始行,lastRow:结束行,firstCol:起始列,lastCol:结束列)
        CellRangeAddress rowRegion = new CellRangeAddress(0,0,0,5);
        sheet.addMergedRegion(rowRegion);

        //创建表头行，并设置样式
        row = sheet.createRow(1); //创建第二行
        row.setHeight((short)500);//设置行高
        String[] row_head = {"名字","性别","电话","地址","年龄","身份证"};
        for(int i=0;i<row_head.length;i++){ //创建表头
            HSSFCell tempCell = row.createCell(i);
            tempCell.setCellValue(row_head[i]); //设置单元格内容

            //设置表头样式
            tempCell.setCellStyle(ExcelImportUtils.createHeadCellStyle(wb));
        }

        //定义表格内容(每行数据)

        //集合(vips)中有多少个元素就生成多少行
        for(int i=0;i<vips.size();i++) {

            row = sheet.createRow(i + 2);
            Vip vip = vips.get(i);
            for (int j = 0; j < 6; j++) { //每行有4列
                HSSFCell tempCell = row.createCell(j); //设置单元格内容
                //设置内容样式
                tempCell.setCellStyle(ExcelImportUtils.createContentCellStyle(wb));

                if (j == 0) {
                    tempCell.setCellValue(vip.getName());
                }  else if (j == 1) {
                    tempCell.setCellValue(vip.getSex());
                } else if (j == 2) {
                    tempCell.setCellValue(vip.getTel());
                } else if (j == 3) {
                    tempCell.setCellValue(vip.getAddress());
                } else if (j == 4) {
                    tempCell.setCellValue(vip.getAge());
                } else if (j == 5) {
                    tempCell.setCellValue(vip.getIdentity());
                }
            }
        }
        // sheet.setDefaultRowHeight((short)(16.5*20)); 设置默认行高
        //列宽自适应
        for(int i=0;i<6;i++){
            sheet.autoSizeColumn(i);
        }
        return wb;
    }

    @Override
    public int updatePhone(Vip vip) {
        return vipMapper.updatePhone(vip);
    }

    @Override
    public int updateImage(String image, Long id) {
        return vipMapper.updateImage(image,id);
    }

}
