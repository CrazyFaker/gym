package cn.gok.mapper;

import cn.gok.entity.User;

import java.util.List;

public interface UserMapper {
    /**
     * 用户注册
     * @param user 用户注册的信息
     * @return
     */
    public int addUser(User user);
    /**
     * 通过账号查找用户是否存在，用于用户注册
     * @param userName 账号
     * @return 用户存在或null
     */
    public User findUserByAccount(String userName);

    /**
     * 忘记密码
     * @return
     * */
    public int reset(User user);

    //登录
    public User login(User user);
    //查询身份
    public User searchType(User user);
    //查询
    public List<User> list(String searchKey);
    //删除
    public int del(User user);
    //更新
    public int update(User user);
    //详情
    public User detail(User user);
}
