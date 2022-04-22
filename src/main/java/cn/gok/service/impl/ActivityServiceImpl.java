package cn.gok.service.impl;

import cn.gok.entity.Activity;
import cn.gok.mapper.ActivityMapper;
import cn.gok.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired(required = false)
    private ActivityMapper activityMapper;
    @Override
    public PageInfo<Activity> list(String searchKey, Integer pageNum, Integer pageSize) throws ParseException {
        PageHelper.startPage(pageNum,pageSize);
        List<Activity> list=activityMapper.list(searchKey);
        for( int i = 0; i < list.size() ; i++){
            String startTime=dateToStamp(list.get(i).getStartTime());
            String currentTime = String.valueOf(System.currentTimeMillis());
            int res = startTime.compareTo(currentTime);
            if(res < 0){
                activityMapper.delete(list.get(i).getId());
            }
        }
        list=activityMapper.list(searchKey);
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
    public int updateNumber(Long id) {
        return activityMapper.updateNumber(id);
    }


    @Override
    public int delete(Long id) {

        return  activityMapper.delete(id);
    }

    @Override
    public int updateImage(String image, Long id) {
        System.out.println(image+"-------------");
        return activityMapper.updateImage(image,id);
    }

    /**
     * 时间转换成时间戳,参数和返回值都是字符串
     * @param  s
     * @return res
     * @throws ParseException
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        //设置时间模版
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
}
