package cn.gok.service;

import cn.gok.entity.Activity;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;

public interface ActivityService {
    public PageInfo<Activity> list(String searchKey, Integer pageNum, Integer pageSize) throws ParseException;


    public int save(Activity activity);


    public int update(Activity activity);

    public int updateCoach(Activity activity);

    public int delete(Long id);




}
