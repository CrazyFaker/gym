package cn.gok.mapper;


import cn.gok.entity.Vip;

import java.util.List;

public interface VipMapper {
    //列表查询
    public  List<Vip> list(String searchKey);

    //新增
    public int save(Vip vip);

    //编辑
    public int update(Vip vip);

    //查找名字是否相同
    public Vip findVipByName(String name) ;


    //删除
    public int delete(Vip vip);

    //导入导出
    public List<Vip> getvips();//查询所有
    public Vip findById(Long id);
}

