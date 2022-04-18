package cn.gok.controller;

import cn.gok.entity.Commodity;
import cn.gok.entity.Result;
import cn.gok.service.CommodityService;
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

@Controller
@RequestMapping("/api/commodity")
public class CommodityController {
    @Autowired(required = false)
    private CommodityService commodityService;

    @RequestMapping("/list")
    @ResponseBody
    public Result list(@RequestBody JSONObject json) {
        String searchKey = json.getString("searchKey");
        //给他默认的页码以及默认的每页的数目
        Integer pageNum = json.getInteger("pageNum") == null ? 1 : json.getInteger("pageNum");
        Integer pageSize = json.getInteger("pageSize") == null ? 5 : json.getInteger("pageSize");
        PageInfo<Commodity> list = commodityService.list(searchKey, pageNum, pageSize);

        return Result.success(list);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result save(@RequestBody Commodity commodity) {
        Result result = null;

        int check = commodityService.save(commodity);
        if (check > 0) {
            result = Result.success(null);
        } else {
            result = Result.error("保存失败");
        }

        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Commodity commodity){
        Result result=null;

        int check = commodityService.update(commodity);
        if(check>0){
            result=Result.success(null);
        }else {
            result= Result.error("更新失败");
        }

        return result;
    }

    @RequestMapping("/del")
    @ResponseBody
    public Result delete(@RequestBody Commodity commodity) {
        Result result=null;

        int check = commodityService.delete(commodity);
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
            a = commodityService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(a);
        return a;
    }

    @GetMapping("/export")
    @ResponseBody
    public void export(HttpServletResponse response) throws IOException {
        List<Commodity> commodities = commodityService.getcommditys();
        HSSFWorkbook wb = commodityService.exportToExcel(commodities);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;filename=commodity.xls"); //默认Excel名称
        wb.write(os);
        os.flush();
        os.close();
    }

}
