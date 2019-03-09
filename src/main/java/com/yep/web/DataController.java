package main.java.com.yep.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.yep.pojo.Test;
import main.java.com.yep.pojo.Test.RowsBean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

@Controller
public class DataController extends BaseController {

    @RequestMapping(value="/data-load")
    public void dataLoad(HttpServletRequest request,HttpServletResponse response){
        
        Test dataJson = new Test();
        RowsBean bean = new RowsBean();
        dataJson.setTotal(1);
        bean.setAttr1("aaa");
        bean.setItemid("啊啊啊");
        bean.setListprice(12.00);
        bean.setProductid("ccc");
        bean.setProductname("ddd");
        bean.setStatus("eee");
        bean.setUnitcost(13.00);
        List<RowsBean> rows = new ArrayList<RowsBean>();
        rows.add(bean);
        dataJson.setRows(rows);
        
        this.outJson1(response, dataJson);
        
//        try {
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("text/html; charset=utf-8");
//            PrintWriter out = response.getWriter();
//            out.print(JSONObject.toJSON(dataJson));
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
