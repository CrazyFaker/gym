package cn.gok.mapper;

import cn.gok.entity.Venue;

import java.util.List;

public interface VenueMapper {

    //列表查询
    public List<Venue> list();

    //新增
    public int save(Venue venue);

    //更新
    public int update(Venue venue);

    //更新图片
    public int updateImage(String image,Long id);

    //删除
    public int delete(Venue venue);

    //更新场馆状态
    public int updateStatus(String status,Long id);
}
