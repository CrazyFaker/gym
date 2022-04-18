package cn.gok.service.impl;

import cn.gok.entity.Activity;
import cn.gok.mapper.ActivityMapper;
import cn.gok.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired(required = false)
    private ActivityMapper activityMapper;
    @Override
    public PageInfo<Activity> list(String searchKey, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Activity> list=activityMapper.list(searchKey);
        PageInfo<Activity> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }


    @Override
    public int save(Activity activity) {

        return  activityMapper.save(activity);
    }



    @Override
    public int update(Activity activity) {

        return  activityMapper.update(activity);
    }

    @Override
    public int updateCoach(Activity activity) {
        return activityMapper.updateCoach(activity);
    }


    @Override
    public int delete(Activity activity) {

        return  activityMapper.delete(activity);
    }
}
