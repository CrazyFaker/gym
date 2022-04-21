package cn.gok.service.impl;

import cn.gok.entity.Friend;
import cn.gok.mapper.FriendMapper;
import cn.gok.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired(required = false)
    private FriendMapper friendMapper;


    @Override
    public List<Object> list() {
        String num = "";
        List<Friend> list=friendMapper.list();
        ArrayList<Object> arr = new ArrayList<>();
        ArrayList<Friend> drr = new ArrayList<Friend>();
        for(int i = 0; i<list.size();i++){
            list.get(i).setYear(String.valueOf(list.get(i).getCreateTime().getYear()+1900));
            list.get(i).setMonth(String.valueOf(list.get(i).getCreateTime().getMonth()+1));
            list.get(i).setDay(String.valueOf(list.get(i).getCreateTime().getDate()));
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
}
