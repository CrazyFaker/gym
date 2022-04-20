package cn.gok.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Coach {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createTime;
    private String sex;
    private String name;
    private String describe;
    private String age;
    private String picture;
    private String tel;





}
