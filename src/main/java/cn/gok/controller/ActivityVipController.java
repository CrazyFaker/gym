package cn.gok.controller;

import cn.gok.entity.ActivityVip;
import cn.gok.entity.Result;
import cn.gok.service.ActivityVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
@RequestMapping("/api/activity_vip")
public class ActivityVipController {
    @Autowired
    private ActivityVipService activityVipService;
    @RequestMapping("/add")
    @ResponseBody
    public Result save(@RequestBody ActivityVip activityVip) {
        Result result = null;


        int check = activityVipService.save(activityVip);
        if (check > 0) {
            result = Result.success(null);
        } else {
            result = Result.error("保存失败");
        }

        return result;
    }
}
