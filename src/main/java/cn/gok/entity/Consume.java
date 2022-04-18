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
    private Long vip;
    private String vname;
}
