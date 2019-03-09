package main.java.com.yep.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import main.java.com.yep.dao.MenuInfoMapper;
import main.java.com.yep.service.MenuService;


@Service("menuService")
public class MenuServiceImpl implements MenuService {
    
    @Resource(name="menuDao")
    MenuInfoMapper menuInfoMapper;

    @Override
    public Object selectMenuInfo() {
        // TODO Auto-generated method stub
        //取得数据
        List<HashMap<Object, Object>> hashMaps = menuInfoMapper.selectAllMenuInfo();
        //定义一个Map集合 存储按指定顺序排列好的菜单
        HashMap<String, List<HashMap<Object,Object>>> temp = new HashMap<String, List<HashMap<Object,Object>>>();
        
        for (HashMap<Object,Object> map : hashMaps) {
            //如果temp的键包含这个parentid
            if(temp.containsKey(map.get("parentid").toString())){
                //那么把所有相同parentid的数据全部添加到该parentid键下
                temp.get(map.get("parentid").toString()).add(map);
            }else{
                //初始化temp  第一次用
                List<HashMap<Object,Object>> list = new ArrayList<HashMap<Object,Object>>();
                list.add(map);
                temp.put(map.get("parentid").toString(), list);
            }
        }
        //定义一个完整菜单列表
        ArrayList<HashMap<Object,Object>> array = new ArrayList<HashMap<Object,Object>>();
        
        for (HashMap<Object, Object> hashMap : hashMaps) {
            //如果temp中的键与当前id一致
            if(temp.containsKey(hashMap.get("id").toString())){
                //说明temp是当前id菜单的子菜单
                hashMap.put("children", temp.get(hashMap.get("id").toString()));
            }
            //遇到顶级菜单就添加进完整菜单列表
            if(hashMap.get("parentid").toString().equals("0")){
                array.add(hashMap);
            }
            
        }
        return JSONArray.toJSON(array);
    }

}
