package cn.gok.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class Vip {
    private Long id;
    private String name;
    private String sex;
    private String tel;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private String createTime;
    private String age;
    private String identity;

}
