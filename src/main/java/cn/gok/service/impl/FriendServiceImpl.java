package cn.gok.service.impl;

import cn.gok.entity.Friend;
import cn.gok.mapper.FriendMapper;
import cn.gok.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired(required = false)
    private FriendMapper friendMapper;


    @Override
    public List<Object> list(String searchKey) {
        String num = "";
        List<Friend> list=friendMapper.list(searchKey);
        ArrayList<Object> arr = new ArrayList<>();
        ArrayList<Friend> drr = new ArrayList<Friend>();

        for(int i = 0; i<list.size();i++){
            list.get(i).setYear(String.valueOf(list.get(i).getCreateTime().getYear()+1900));
            list.get(i).setMonth(String.valueOf(list.get(i).getCreateTime().getMonth()+1));
            list.get(i).setDay(String.valueOf(list.get(i).getCreateTime().getDate()));

            String image = list.get(i).getImage();

            list.get(i).setImages(Arrays.asList(image.split(",")));

            if (num.equals(String.valueOf(list.get(i).getCreateTime().getYear()))){
                drr.add(list.get(i));
            }else {
                drr=new ArrayList<Friend>();
                drr.add(list.get(i));
                arr.add(drr);
                num= String.valueOf(list.get(i).getCreateTime().getYear());
            }
        }

        return arr;
    }

    @Override
    public int updateImage(String image, String id) {

        return friendMapper.updateImage(image,id);
    }

    @Override
    public int save(Friend friend) {
        return friendMapper.save(friend);
    }

    @Override
    public int updateStatus(Friend friend) {
        return friendMapper.updateStatus(friend);
    }

    @Override
    public  List<Friend>  listVid(Friend friend) {
        return friendMapper.listVid(friend);
    }

    @Override
    public int delete(Friend friend) {
        return friendMapper.delete(friend);
    }


}
