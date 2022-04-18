package cn.gok.mapper;


import cn.gok.entity.Order;


import java.util.List;

public interface OrderMapper {

    //列表查询
    public  List<Order> list(String searchKey);

    //新增
    public int save(Order order);

    //编辑
    public int update(Order order);

    //更新教练所属会员
    public int updateCoach(Order order);

    //删除
    public int delete(Order order);
}
