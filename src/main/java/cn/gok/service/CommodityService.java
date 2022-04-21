package cn.gok.service;

import cn.gok.entity.Commodity;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommodityService {
    public PageInfo<Commodity> list(String searchKey, Integer pageNum, Integer pageSize);


    public int save(Commodity commodity);


    public int update(Commodity commodity);


    public int delete(Commodity commodity);

    public List<Commodity> getcommditys();//查询所有

    //导入Excel
    public boolean batchImport(String fileName, MultipartFile file) throws Exception;

    //导出到Excel
    public HSSFWorkbook exportToExcel(List<Commodity> Commoditys);

    public int updateImage(String imagem, Long id);
}
