package cn.gok.entity;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class User {
  private Long id;
  private String name;
  private String sex;
  private String userName;
  private String password;
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  private String createTime;
  private DateTime time;
  private String phone;


}
