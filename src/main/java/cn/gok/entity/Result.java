package cn.gok.entity;

import lombok.Data;

@Data
public class Result {
    private static final Integer CODE_SUCCESS = 0;
    //失败的返回标识
    private static final Integer CODE_ERROR = -1;
    //返回标识
    private Integer code;
    //返回信息
    private String msg;
    //返回数据
    private Object data;
    //成功的返回对象
    public static Result success(Object data){
        Result result = new Result();
        result.setCode(CODE_SUCCESS);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    //失败的返回对象
    public static Result error(String msg){
        Result result = new Result();
        result.setCode(CODE_ERROR);
        result.setMsg(msg);
        return result;
    }
}
