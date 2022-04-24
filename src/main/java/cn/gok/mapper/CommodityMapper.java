package cn.gok.mapper;

import cn.gok.entity.Commodity;

import java.util.List;

public interface CommodityMapper {
    //列表查询
    public  List<Commodity> list(String searchKey,String type);

    //新增
    public int save(Commodity commodity);

    //编辑
    public int update(Commodity commodity);

    //更新图片
    public int updateImage(String image,Long id);
    //更新商品的数量
    public int updateNumber(Long id);
    //导入导出
    public List<Commodity> getcommoditys();//查询所有
    public Commodity findById(Long id);

    //删除
    public int delete(Commodity commodity);


}
