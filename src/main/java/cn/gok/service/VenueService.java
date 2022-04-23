package cn.gok.service;

import cn.gok.entity.Venue;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;

public interface VenueService {

    //列表查询
    public PageInfo<Venue> list(String currentTime, Integer pageNum, Integer pageSize) throws ParseException;

    //新增
    public int save(Venue venue);

    //更新
    public int update(Venue venue);

    //更新图片
    public int updateImage(String image,Long id);

    //更新场馆状态
    public int updateStatus(String status,Long id);

    //删除
    public int delete(Venue venue);
}
