package cn.gok.controller;

import cn.gok.entity.Result;
import cn.gok.entity.User;
import cn.gok.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户管理
 * */
@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 用户注册
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result adduser(@RequestBody User user) {
        Result result = null;
        // 查看是否存在同账号的另一个用户
        User check = userService.findUserByAccount(user.getUserName());
        if (check == null){
            // 判断插入语句执行是否成功
            int affected = userService.addUser(user);
            if(affected>0){
               result= Result.success(null);
            }else {
                result= Result.error("保存失败");
            }

        }else {
            result=Result.error("此用户名已存在");
        }

        return result;
    }
    /**
     * 忘记密码
     * @return
     * */
    @RequestMapping("/reset")
    @ResponseBody
    public Result reset(@RequestBody User user){
       int user1= userService.reset(user);
       Result result=null;
        if(user1>0){
           result= Result.success(null);
        }else {
            result=Result.error("保存失败");
        }
        return result;
    }

    //登录
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestBody User user){
        Result result=null;
        User user2=userService.login(user);
        if(user2!=null){
            result=Result.success(null);
        }else{
            result=Result.error("登入失败");
        }
        return result;
    }



    //查询
    @RequestMapping("/list")
    @ResponseBody
    public Result list(@RequestBody JSONObject json){
        String searchKey=json.getString("searchKey");
        Integer pageNum=json.getInteger("pageNum")==null?1:json.getInteger("pageNum");
        Integer pageSize=json.getInteger("pageSize")==null?5:json.getInteger("pageSize");
        PageInfo<User> list=userService.list(searchKey,pageNum,pageSize);
        return Result.success(list);
    }

    //删除
    @ResponseBody
    @RequestMapping("/del")
    public Result del(@RequestBody User user){
        userService.del(user);
        Result result=null;
        int user1=userService.del(user);
        if(user1>0){
            result=Result.error("失败");
        }else{
            result=Result.success(null);
        }
        return result;

    }



    //更新
    @ResponseBody
    @RequestMapping("/update")
    public Result update(@RequestBody User user){
        userService.update(user);
        Result result=null;
        int user1=userService.update(user);
        if(user1>0){
            result=Result.success(null);
        }else{
            result=Result.error("更新失败");
        }
        return result;

    }

    //详情
    @ResponseBody
    @RequestMapping("/detail")
    public Result detail(@RequestBody User user){
        return Result.success((userService.detail(user)));
    }

}

