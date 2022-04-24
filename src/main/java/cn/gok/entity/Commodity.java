package cn.gok.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Commodity {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createTime;
    private String cname;
    private Integer number;
    private String sketch;
    private Double price;
    private Integer discount;
    private String image;
    private String type;




}
