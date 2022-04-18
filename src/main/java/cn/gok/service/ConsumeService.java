package cn.gok.service;

import cn.gok.entity.Consume;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public interface ConsumeService {
    public PageInfo<Consume> list(String searchKey, Integer pageNum, Integer pageSize);


    public int save(Consume consume);


    //导出到Excel
    public HSSFWorkbook exportToExcel(List<Consume> consumes);

    public int delete(Consume consume);

    public List<Consume> getconsumes();//查询所有
}
