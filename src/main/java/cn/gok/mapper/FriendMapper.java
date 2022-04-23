package cn.gok.mapper;

import cn.gok.entity.Friend;

import java.util.List;

public interface FriendMapper {
    //列表查询
    public List<Friend> list(String searchKey);

    public int updateImage(String image,String id);

    public int save(Friend friend);

}
