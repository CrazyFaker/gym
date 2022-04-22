package cn.gok.service;

import cn.gok.entity.Activity;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;

public interface ActivityService {
    //列表查询
    public PageInfo<Activity> list(String searchKey, Integer pageNum, Integer pageSize) throws ParseException;

    //新增
    public int save(Activity activity);

    //更新
    public int update(Activity activity);

    //更新教练
    public int updateCoach(Activity activity);

    public int updateNumber(Long id);

    //删除
    public int delete(Long id);

    //更新图片
    public int updateImage(String image, Long id);





}
