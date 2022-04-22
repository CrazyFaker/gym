package cn.gok.service.impl;

import cn.gok.entity.ActivityVip;
import cn.gok.mapper.ActivityVipMapper;
import cn.gok.service.ActivityVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityVipServiceImpl implements ActivityVipService {
    @Autowired
    private ActivityVipMapper activityVipMapper;
    @Override
    public int save(ActivityVip activityVip) {
        return activityVipMapper.save(activityVip);
    }
}
