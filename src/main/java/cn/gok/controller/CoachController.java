package cn.gok.controller;


import cn.gok.entity.Coach;
import cn.gok.entity.Result;
import cn.gok.service.CoachService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@Controller
@RequestMapping("/api/coach")
public class CoachController {

    @Autowired(required = false)
    private CoachService coachService;

    @RequestMapping("/list")
    @ResponseBody
    public Result list(@RequestBody JSONObject json) {
        String searchKey = json.getString("searchKey");
        //给他默认的页码以及默认的每页的数目
        Integer pageNum = json.getInteger("pageNum") == null ? 1 : json.getInteger("pageNum");
        Integer pageSize = json.getInteger("pageSize") == null ? 5 : json.getInteger("pageSize");
        PageInfo<Coach> list = coachService.list(searchKey, pageNum, pageSize);

        return Result.success(list);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result save(@RequestBody Coach coach) {
        Result result = null;


        int check = coachService.save(coach);
        if (check > 0) {
            result = Result.success(null);
        } else {
            result = Result.error("保存失败");
        }

        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Coach coach){
        Result result=null;

        int check = coachService.update(coach);
        if(check>0){
            result=Result.success(null);
        }else {
            result= Result.error("更新失败");
        }

        return result;
    }

    @RequestMapping("/del")
    @ResponseBody
    public Result delete(@RequestBody Coach coach) {
        Result result=null;

        int check = coachService.delete(coach);
        if(check>0){
            result=Result.success(null);
        }else {
            result= Result.error("删除失败");
        }

        return result;
    }
    @PostMapping("/import")
    @ResponseBody
    public boolean exImport(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        try {
            a = coachService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return a;
    }

    @GetMapping("/export")
    @ResponseBody
    public void export(HttpServletResponse response) throws IOException {
        List<Coach> coachs = coachService.getcoachs();
        HSSFWorkbook wb = coachService.exportToExcel(coachs);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;filename=coach.xls"); //默认Excel名称
        wb.write(os);
        os.flush();
        os.close();
    }
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload( MultipartFile file, HttpServletRequest request)  {
        Calendar currTime = Calendar.getInstance();
        String time = String.valueOf(currTime.get(Calendar.YEAR))+String.valueOf((currTime.get(Calendar.MONTH)+1));
        String path ="d:"+ File.separator+"img"+File.separator+time;
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        suffix = suffix.toLowerCase();
        if(suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png") || suffix.equals(".gif")){

            String fileName = UUID.randomUUID().toString()+suffix;
            File targetFile = new File(path, fileName);

            if(!targetFile.getParentFile().exists()){    //注意，判断父级路径是否存在
                targetFile.getParentFile().mkdirs();
            }
            long size = 0;
            //保存
            try {
                file.transferTo(targetFile);
                size = file.getSize();
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject result = new JSONObject();
            result.put("fileUrl", "/img/"+time+fileName);
            result.put("url", "/img/"+time+fileName);
            result.put("state", "SUCCESS");
            result.put("title", fileName);
            result.put("original", fileName);
            result.put("type", suffix);
            result.put("size", size);
            return result.toString();
        }else{
            JSONObject result = new JSONObject();
            result.put("ss", false);
            result.put("msg", "格式不支持");
            return result.toString();
        }

    }




}
