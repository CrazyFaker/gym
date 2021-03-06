package cn.gok.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Activity {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createTime;
    private String activityName;
    private String startTime;
    private String image;
    private Integer number;
    private Integer price;
    private String duration;
    private Integer coachId;
    private String coachName;
    private String is_Delete;
    private String introduction;
    private String type;
    private Long vid;
    private Long aid;
}
