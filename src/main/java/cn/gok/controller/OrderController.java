package cn.gok.controller;


import cn.gok.entity.Order;
import cn.gok.entity.Result;
import cn.gok.service.OrderService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
@RequestMapping("/api/order")
public class OrderController {

    @Autowired(required = false)
    private OrderService orderService;

    @RequestMapping("/list")
    @ResponseBody
    public Result list(@RequestBody JSONObject json) {
        String searchKey = json.getString("searchKey");
        //给他默认的页码以及默认的每页的数目
        Integer pageNum = json.getInteger("pageNum") == null ? 1 : json.getInteger("pageNum");
        Integer pageSize = json.getInteger("pageSize") == null ? 5 : json.getInteger("pageSize");
        PageInfo<Order> list = orderService.list(searchKey, pageNum, pageSize);

        return Result.success(list);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result save(@RequestBody Order order) {
        Result result = null;


            int check = orderService.save(order);
            if (check > 0) {
                result = Result.success(null);
            } else {
                result = Result.error("保存失败");
            }

        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Order order){
        Result result=null;

            int check = orderService.update(order);
            if(check>0){
                result=Result.success(null);
            }else {
                result= Result.error("更新失败");
            }

        return result;
    }

    @RequestMapping("/del")
    @ResponseBody
    public Result delete(@RequestBody Order order) {
        Result result=null;

        int check = orderService.delete(order);
        if(check>0){
            result=Result.success(null);
        }else {
            result= Result.error("删除失败");
        }

        return result;
    }


//    @PostMapping("/import")
//    @ResponseBody
//    public boolean exImport(@RequestParam("file") MultipartFile file) {
//        boolean a = false;
//        String fileName = file.getOriginalFilename();
//        System.out.println(fileName);
//        try {
//            a = loopholeService.batchImport(fileName, file);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(a);
//        return a;
//    }
//
//    @GetMapping("/export")
//    @ResponseBody
//    public void export(HttpServletResponse response) throws IOException {
//        List<Loophole> loopholes = loopholeService.getloopholes();
//        HSSFWorkbook wb = loopholeService.exportToExcel(loopholes);
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//        OutputStream os = response.getOutputStream();
//        response.setHeader("Content-disposition", "attachment;filename=loophole.xlsx"); //默认Excel名称
//        wb.write(os);
//        os.flush();
//        os.close();
//    }


}
