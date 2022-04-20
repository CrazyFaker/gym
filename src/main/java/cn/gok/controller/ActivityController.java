package cn.gok.controller;


import cn.gok.entity.Activity;
import cn.gok.entity.Result;
import cn.gok.service.ActivityService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin
@Controller
@RequestMapping("/api/activity")
public class ActivityController {
    @Autowired(required = false)
    private ActivityService activityService;

    @RequestMapping("/list")
    @ResponseBody
    public Result list(@RequestBody JSONObject json) throws ParseException {
        String searchKey = json.getString("searchKey");
        //给他默认的页码以及默认的每页的数目
        Integer pageNum = json.getInteger("pageNum") == null ? 1 : json.getInteger("pageNum");
        Integer pageSize = json.getInteger("pageSize") == null ? 5 : json.getInteger("pageSize");
        PageInfo<Activity> list = activityService.list(searchKey, pageNum, pageSize);

        return Result.success(list);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result save(@RequestBody Activity activity) {
        Result result = null;


        int check = activityService.save(activity);
        if (check > 0) {
            result = Result.success(null);
        } else {
            result = Result.error("保存失败");
        }

        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Activity activity){
        Result result=null;

        int check = activityService.update(activity);
        if(check>0){
            result=Result.success(null);
        }else {
            result= Result.error("更新失败");
        }

        return result;
    }


    @RequestMapping("/updatecoach")
    @ResponseBody
    public Result updateCoach(@RequestBody Activity activity){
        Result result=null;

        int check = activityService.updateCoach(activity);
        if(check>0){
            result=Result.success(null);
        }else {
            result= Result.error("更新失败");
        }

        return result;
    }

    @RequestMapping("/del")
    @ResponseBody
    public Result delete(@RequestParam Long id) {
        Result result=null;

        int check = activityService.delete(id);
        if(check>0){
            result=Result.success(null);
        }else {
            result= Result.error("删除失败");
        }

        return result;
    }
}
