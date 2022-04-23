package cn.gok.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Friend {
    private Long id;
    private Long vid;
    private String image;
    private String value;
    private Timestamp createTime;
    private String year;
    private String month;
    private String day;
    private List<String> images;
}
