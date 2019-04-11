package com.zd.interf.dqy_gate.controller;

/*
 *  Creat by zhoudi on 2019/4/10.
 */

import com.zd.interf.dqy_gate.document.Weather;
import com.zd.interf.dqy_gate.service.weatherService;
import com.zd.interf.dqy_gate.util.HttpClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("wx")
@Api(tags = {"APItestController_MongoDB增删改查接口测试"},description = "MongoDB基本信息操作")
public class APItestController {

    @Autowired
    private weatherService weatherService;
    /**
     *  测试微信api接口
     */
    @ApiOperation(value = "MongoDB保存操作", notes = "插入一条数据")
    @PostMapping(value = "/wxApiTestSave")
    public String wxApiTestSave(){
        Map<String, String> map = new HashMap<String, String>();
        Long date = 0l;
        try{
            Long startDate = System.currentTimeMillis();
            String url = "https://traffic-api-dev.daqiuyin.com/gateway/findgatewayid";
            String encoding = "utf-8";
            String resutl = HttpClientUtil.doPost(url,map,encoding);
            map.put("gateway_id","702bdbf5ac8e");
            for (int i = 0; i < 100; i++){
                Weather weather = new Weather();
                weather.setUrl(url);
                weather.setDescription(resutl);
                weather.setBy(encoding);
                weather.setTitle("测试插入MongoDB数据库数据"+i);
                weatherService.saveWeather(weather);
            }
            Long endDate = System.currentTimeMillis();
            date = (endDate - startDate);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return date.toString();
    }

    /**
     *  测试微信api接口，根据id删除
     */
    @ApiOperation(value = "根据id删除数据", notes = "根据id删除数据")
    @DeleteMapping(value = "/wxApiTestDelOne")
    public String wxApiTestDelOne(){
        try{
            weatherService.removeWeather("56064886ade2f21f36b03134");
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return "删除成功";
    }
    /**
     *  测试微信api接口，全部删除
     */
    @ApiOperation(value = "全部删除", notes = "全部删除")
    @DeleteMapping(value = "/wxApiTestDelAll")
    public String wxApiTestDelAll(){
        try{
            weatherService.removeWeatherAll();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return "删除成功";
    }
    /**
     *  测试微信api接口，根据id查询
     */
    @ApiOperation(value = "根据id查询数据", notes = "根据id查询数据")
    @GetMapping(value = "/wxApiTestFindOne")
    public String wxApiTestFindOne(){
        Map<String, String> map = new HashMap<String, String>();
        Weather weather = null;
        try{
            weather = weatherService.findOne("56064886ade2f21f36b03134");
            if(weather == null){
                return "查询失败";
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return "查询成功";
    }

    /**
     *  测试微信api接口，全查
     */
    @ApiOperation(value = "查询所有数据", notes = "查询所有数据")
    @GetMapping(value = "/wxApiTestFindAll")
    public Integer wxApiTestFindAll(){
        Map<String, String> map = new HashMap<String, String>();
        List<Weather> weatherList = null;
        try{
            weatherList = weatherService.findAll();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return weatherList.size();
    }
}
