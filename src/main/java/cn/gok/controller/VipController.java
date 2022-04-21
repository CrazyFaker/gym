package cn.gok.controller;

import cn.gok.entity.Result;
import cn.gok.entity.Vip;
import cn.gok.service.VipService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/api/vip")
public class VipController {

    @Autowired(required = false)
    private VipService vipService;

    @RequestMapping("/list")
    @ResponseBody
    public Result list(@RequestBody JSONObject json) {
        String searchKey = json.getString("searchKey");
        //给他默认的页码以及默认的每页的数目
        Integer pageNum = json.getInteger("pageNum") == null ? 1 : json.getInteger("pageNum");
        Integer pageSize = json.getInteger("pageSize") == null ? 5 : json.getInteger("pageSize");
        PageInfo<Vip> list = vipService.list(searchKey, pageNum, pageSize);

        return Result.success(list);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result save(@RequestBody Vip vip) {
        Result result = null;
        Vip check1 = vipService.findVipByName(vip.getIdentity());
        if(check1==null) {
            int check = vipService.save(vip);
            if (check > 0) {
                result = Result.success(null);
            } else {
                result = Result.error("保存失败");
            }
        }else {
            result = Result.error("姓名已存在");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Vip vip){
        Result result=null;

            int check = vipService.update(vip);
            if (check > 0) {
                result = Result.success(null);
            } else {
                result = Result.error("更新失败");
            }

        return result;
    }

    @RequestMapping("/del")
    @ResponseBody
    public Result delete(@RequestBody Vip vip) {
        Result result=null;

        int check = vipService.delete(vip);
        if(check>0){
            result=Result.success(null);
        }else {
            result= Result.error("删除失败");
        }

        return result;
    }
    @PostMapping("/import")
    @ResponseBody
    public boolean Import(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        try {
            a = vipService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(a);
        return a;
    }

    @GetMapping("/export")
    @ResponseBody
    public void export(HttpServletResponse response) throws IOException {
        List<Vip> vips = vipService.getvips();
        HSSFWorkbook wb = vipService.exportToExcel(vips);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;filename=vip.xls"); //默认Excel名称
        wb.write(os);
        os.flush();
        os.close();
    }

}
