package cn.gok.mapper;

import cn.gok.entity.Activity;


import java.util.List;

public interface ActivityMapper {
    //列表查询
    public List<Activity> list(String searchKey);

    //列表查询
    public List<Activity> listActivity(Long id);

    //新增
    public int save(Activity activity);


    public int updateImage(String image,Long id);

    //编辑
    public int update(Activity activity);

    //更新活动所属教练
    public int updateCoach(Activity activity);

    //更新活动人数
    public int updateNumber(Long id);

    //删除
    public int delete(Long id);

    //查询是否过期
    public String listExpire(Long id);
}
