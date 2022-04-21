package cn.gok.mapper;

import cn.gok.entity.Activity;


import java.util.List;

public interface ActivityMapper {
    //列表查询
    public List<Activity> list(String searchKey);

    //新增
    public int save(Activity activity);

    //新增
    public int saveImage(String image);

    //编辑
    public int update(Activity activity);

    //更新活动所属教练
    public int updateCoach(Activity activity);

    //删除
    public int delete(Long id);

    //查询是否过期
    public String listExpire(Long id);
}
