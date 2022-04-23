package cn.gok.entity;

import lombok.Data;

@Data
public class Venue {
    private Long id;
    private String name;
    private String image;
    private String startTime;
    private String endTime;
    private String createTime;
    private String status;
}
