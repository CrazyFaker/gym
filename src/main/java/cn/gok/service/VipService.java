package cn.gok.service;


import cn.gok.entity.Vip;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VipService {
    //查
    public PageInfo<Vip> list(String searchKey, Integer pageNum, Integer pageSize);

    //查找名字是否相同
    public Vip findVipByName(String identity) ;

    //增
    public int save(Vip vip);

    //更新
    public int update(Vip vip);


    //删
    public int delete(Vip vip);

    public List<Vip> getvips();//查询所有

    //导入Excel
    public boolean batchImport(String fileName, MultipartFile file) throws Exception;

    //导出到Excel
    public HSSFWorkbook exportToExcel(List<Vip> vips);
}
