package cn.gok.service;

import cn.gok.entity.User;
import com.github.pagehelper.PageInfo;

public interface UserService {
    public int addUser(User user) ;

    public User findUserByAccount(String userName) ;

    public int reset(User user);

    //登录
    public User login(User user);

    //查询
    public PageInfo<User> list(String searchKey, Integer pageNum, Integer pageSize);
    //删除
    public int del(User user);
    //更新
    public int update(User user);
    //详情
    public User detail(User user);
}
