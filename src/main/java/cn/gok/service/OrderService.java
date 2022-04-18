package cn.gok.service;

import cn.gok.entity.Order;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OrderService {

    public PageInfo<Order> list(String searchKey, Integer pageNum, Integer pageSize);


    public int save(Order order);


    public int update(Order order);


    public int delete(Order order);

}
