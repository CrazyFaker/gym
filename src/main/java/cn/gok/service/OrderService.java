package cn.gok.service;

import cn.gok.entity.Order;
import com.github.pagehelper.PageInfo;

public interface OrderService {

    public PageInfo<Order> list(String searchKey, Integer pageNum, Integer pageSize);


    public int save(Order order,Long id);


    public int update(Order order);


    public int delete(Order order);

}
