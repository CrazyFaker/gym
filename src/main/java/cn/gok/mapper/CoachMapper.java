package cn.gok.mapper;

import cn.gok.entity.Coach;

import java.util.List;

public interface CoachMapper {
    //列表查询
    public  List<Coach> list(String searchKey);

    //新增
    public int save(Coach coach);

    //编辑
    public int update(Coach coach);

    //更新活动人数
    public int updateImage(String image,Long id);


    //删除
    public int delete(Coach coach);

    //导入导出
    public List<Coach> getcoachs();//查询所有
    public Coach findById(Long id);


}
