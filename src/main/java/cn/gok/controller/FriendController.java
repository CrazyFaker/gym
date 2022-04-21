package cn.gok.controller;

import cn.gok.entity.Result;
import cn.gok.service.FriendService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/api/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;
    @RequestMapping("/list")
    @ResponseBody
    public Result list(@RequestBody JSONObject json) throws ParseException {

        List<Object> list = friendService.list();

        return Result.success(list);
    }
}
