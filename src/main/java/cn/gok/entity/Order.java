package cn.gok.entity;

import cn.hutool.core.date.DateTime;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Order {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createTime;
    private DateTime time;
    private Integer orderNumber;
    private String realTime;
    private Integer vip;


}
