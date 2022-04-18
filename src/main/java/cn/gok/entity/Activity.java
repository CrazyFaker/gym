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
    private byte[] image;
    private Integer number;
    private Integer price;
    private String endTime;
    private Integer coachId;
    private String coachName;

}
