package cn.gok.service;

import java.util.List;

public interface FriendService {
    public List<Object> list(String searchKey);

    public int updateImage(String image,String id);


}
