package cn.gok.service;


import cn.gok.entity.Coach;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface CoachService {
    public PageInfo<Coach> list(String searchKey, Integer pageNum, Integer pageSize);


    public int save(Coach coach);


    public int update(Coach coach);


    public int delete(Coach coach);

    //导入Excel
    public boolean batchImport(String fileName, MultipartFile file) throws Exception;

    //导出到Excel
    public HSSFWorkbook exportToExcel(List<Coach> coachs);

    public List<Coach> getcoachs();//查询所有


}
