package cn.gok.mapper;

import cn.gok.entity.Commodity;

import java.util.List;

public interface CommodityMapper {
    //列表查询
    public  List<Commodity> list(String searchKey);

    //新增
    public int save(Commodity commodity);

    //编辑
    public int update(Commodity commodity);

    //导入导出
    public List<Commodity> getcommoditys();//查询所有
    public Commodity findById(Long id);

    //删除
    public int delete(Commodity commodity);
}
