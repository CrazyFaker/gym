package cn.gok.service;

import cn.gok.entity.Friend;

import java.util.List;

public interface FriendService {
    public List<Object> list(String searchKey);

    public int updateImage(String image,String id);

    public int save(Friend friend);
}
