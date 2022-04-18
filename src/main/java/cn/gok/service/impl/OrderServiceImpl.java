package cn.gok.service.impl;

import cn.gok.entity.Coach;

import cn.gok.entity.Order;
import cn.gok.mapper.OrderMapper;
import cn.gok.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired(required = false)
    private OrderMapper orderMapper;
    @Override
    public PageInfo<Order> list(String searchKey, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Order> list=orderMapper.list(searchKey);
        PageInfo<Order> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }


    @Override
    public int save(Order order) {

        return orderMapper.save(order);
    }



    @Override
    public int update(Order order) {

        return   orderMapper.update(order);
    }



    @Override
    public int delete(Order order) {

        return  orderMapper.delete(order);
    }
}
