package cn.gok.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Consume {
    private Long id;
    private String name;
    private String status;
    private Integer number;
    private Double price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createTime;
    private String vip;
    private String vname;
    private String sketch;
    private String remark;
    private String mention;
    private String image;
}
