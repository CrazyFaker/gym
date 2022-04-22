package cn.gok.controller;


import cn.gok.entity.Result;
import cn.gok.entity.Venue;
import cn.gok.service.VenueService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.UUID;

@CrossOrigin
@Controller
@RequestMapping("/api/venue")
public class VenueController {

    @Autowired
    private VenueService venueService;

    @RequestMapping("/list")
    @ResponseBody
    public Result list(@RequestBody JSONObject json) throws ParseException {
        String searchKey = json.getString("searchKey");
        //给他默认的页码以及默认的每页的数目
        Integer pageNum = json.getInteger("pageNum") == null ? 1 : json.getInteger("pageNum");
        Integer pageSize = json.getInteger("pageSize") == null ? 5 : json.getInteger("pageSize");
        PageInfo<Venue> list = venueService.list(searchKey, pageNum, pageSize);

        return Result.success(list);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result save(@RequestBody Venue venue) {
        Result result = null;


        int check = venueService.save(venue);
        if (check > 0) {
            result = Result.success(null);
        } else {
            result = Result.error("保存失败");
        }

        return result;
    }



    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Venue venue){
        Result result=null;

        int check = venueService.update(venue);
        if(check>0){
            result=Result.success(null);
        }else {
            result= Result.error("更新失败");
        }

        return result;
    }

    @RequestMapping("/update_status")
    @ResponseBody
    public Result update(@RequestParam String status ,@RequestParam Long id){
        Result result=null;

        int check = venueService.updateStatus(status,id);
        if(check>0){
            result=Result.success(null);
        }else {
            result= Result.error("更新场馆状态失败");
        }

        return result;
    }

    @RequestMapping("/del")
    @ResponseBody
    public Result delete(@RequestParam Venue venue) {
        Result result=null;

        int check = venueService.delete(venue);
        if(check>0){
            result=Result.success(null);
        }else {
            result= Result.error("删除失败");
        }

        return result;
    }
    /**
     　　* @description: 图片上传
     　　*/
    @Value("${upload-path}")
    private String realPath;

    @ResponseBody
    @RequestMapping(value ="upload", method = RequestMethod.POST)
//    图片是以content-type为multipart/form-data的格式上传的，所以使用spring-mvc可以通过使用参数的形式以二进制的格式获取到该图片。
    public String upload(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file, Venue venue) throws IOException {
        System.out.println("执行upload");
        System.out.println(file);
        Long id = venue.getId();
        request.setCharacterEncoding("UTF-8");
//        log.info("执行图片上传");
        String pdNo = request.getParameter("pdNo");
//        log.info("pdNo:"+pdNo);
        String path ;
        String type ;
        String avator;
        if(!file.isEmpty()) {
//            log.info("成功获取照片");
            String fileName = file.getOriginalFilename();
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
//            log.info("图片初始名称为：" + fileName + " 类型为：" + type);
            if (type != null) {
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {

//                    log.info("图片根路径："+realPath);
                    // 自定义的文件名称
                    Calendar rightNow=Calendar.getInstance();
                    Integer year = rightNow.get(Calendar.YEAR);
                    Integer month = rightNow.get(Calendar.MONTH)+1; //第一个月从0开始，所以得到月份＋1
                    Integer day = rightNow.get(rightNow.DAY_OF_MONTH);

                    String date="("+year+"-"+month+"-"+day+")";
//                    String trueFileName =pdNo+"-"+date+fileName.substring(fileName.lastIndexOf("."));
                    String trueFileName = UUID.randomUUID()+pdNo+"-"+date+".jpg";//把图片都变成jpg格式，按需求决定该不该格式
//                    log.info("图片自定义名称为：" + trueFileName + " 类型为：" + type);
                    // 设置存放图片文件的路径
                    path = realPath +trueFileName;
//                    log.info("存放图片文件的路径:" + path);
                    //判断文件父目录是否存在
                    File dest=new File(path);
                    if(!dest.getParentFile().exists()){
                        dest.getParentFile().mkdir();
                    }
                    //保存文件
                    file.transferTo(new File(path));
//                    log.info("文件成功上传到指定目录下");
                    avator=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + trueFileName;
//                    log.info("数据库存放图片文件的路径:" + avator);
                }else {
//                    log.info("不是我们想要的文件类型,请按要求重新上传");
                    return "error";
                }
            }else {
//                log.info("文件类型为空");
                return "error";
            }
        }else {
//            log.info("没有找到相对应的文件");
            return "error";
        }
        venueService.updateImage(avator,id);
        return avator;//返回图片访问路径，可以把这个连接存到数据库里，小程序端以后就可以直接访问图片了
    }

}
