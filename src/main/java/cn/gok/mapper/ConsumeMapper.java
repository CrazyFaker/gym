package cn.gok.mapper;

import cn.gok.entity.Consume;

import java.util.List;

public interface ConsumeMapper {
    //列表查询
    public List<Consume > list(String searchKey);

    //新增
    public int save(Consume consume);

    //删除
    public int delete(Consume consume);

    //导入导出
    public List<Consume> getconsumes();//查询所有
}