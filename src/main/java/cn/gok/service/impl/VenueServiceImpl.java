package cn.gok.service.impl;

import cn.gok.entity.Venue;
import cn.gok.mapper.VenueMapper;
import cn.gok.service.VenueService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    @Autowired(required = false)
    private VenueMapper venueMapper;
    @Override
    public PageInfo<Venue> list(String currentTime, Integer pageNum, Integer pageSize) throws ParseException {
        PageHelper.startPage(pageNum,pageSize);
        List<Venue> list=venueMapper.list();
        for( int i = 0; i < list.size() ; i++){
            String endTime=dateToStamp(list.get(i).getEndTime());
            String startTime = dateToStamp(list.get(i).getStartTime());
            int res = endTime.compareTo(currentTime);
            int res1 = startTime.compareTo(currentTime);
            if(res <= 0 && res1 >= 0){
                list.get(i).setStatus("1");
            }else{
                list.get(i).setStatus("0");
            }
        }
        PageInfo<Venue> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int save(Venue venue) {
        return venueMapper.save(venue);
    }

    @Override
    public int update(Venue venue) {
        return venueMapper.update(venue);
    }

    @Override
    public int updateImage(String image, Long id) {
        if(id == null){
            return 0;
        }
        return venueMapper.updateImage(image,id);
    }

    @Override
    public int updateStatus(String status, Long id) {
        if (id == null || status == null || status == ""){
            return 0;
        }
        return venueMapper.updateStatus(status,id);
    }

    @Override
    public int delete(Venue venue) {
        return venueMapper.delete(venue);
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
