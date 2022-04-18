package cn.gok.service;

import cn.gok.entity.Activity;
import com.github.pagehelper.PageInfo;

public interface ActivityService {
    public PageInfo<Activity> list(String searchKey, Integer pageNum, Integer pageSize);


    public int save(Activity activity);


    public int update(Activity activity);

    public int updateCoach(Activity activity);

    public int delete(Activity activity);




}
