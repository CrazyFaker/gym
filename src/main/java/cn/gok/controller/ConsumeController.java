package cn.gok.controller;


import cn.gok.entity.Consume;
import cn.gok.entity.Result;
import cn.gok.service.ConsumeService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/api/consume")
public class ConsumeController {

    @Autowired(required = false)
    private ConsumeService consumeService;

    @RequestMapping("/list")
    @ResponseBody
    public Result list(@RequestBody JSONObject json) {
        String searchKey = json.getString("searchKey");
        //给他默认的页码以及默认的每页的数目
        Integer pageNum = json.getInteger("pageNum") == null ? 1 : json.getInteger("pageNum");
        Integer pageSize = json.getInteger("pageSize") == null ? 5 : json.getInteger("pageSize");
        PageInfo<Consume> list = consumeService.list(searchKey, pageNum, pageSize);

        return Result.success(list);
    }

    @RequestMapping("/listVip")
    @ResponseBody
    public Result listVip(@RequestParam Long vid) {
        List<Consume> check = consumeService.listVip(vid);
        return Result.success(check);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result save(@RequestBody Consume consume) {
        Result result = null;

        int check = consumeService.save(consume);
        if (check > 0) {
            result = Result.success(null);
        } else {
            result = Result.error("保存失败");
        }

        return result;
    }


    @RequestMapping("/del")
    @ResponseBody
    public Result delete(@RequestBody Consume consume) {
        Result result=null;

        int check = consumeService.delete(consume);
        if(check>0){
            result=Result.success(null);
        }else {
            result= Result.error("删除失败");
        }

        return result;
    }

    @GetMapping("/export")
    @ResponseBody
    public void export(HttpServletResponse response) throws IOException {
        List<Consume> consumes = consumeService.getconsumes();
        HSSFWorkbook wb = consumeService.exportToExcel(consumes);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;filename=consume.xls"); //默认Excel名称
        wb.write(os);
        os.flush();
        os.close();
    }

}
