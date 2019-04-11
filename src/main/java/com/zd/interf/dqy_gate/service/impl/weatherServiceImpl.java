package com.zd.interf.dqy_gate.service.impl;

/*
 *  Creat by zhoudi on 2019/4/10.
 */

import com.zd.interf.dqy_gate.document.Weather;
import com.zd.interf.dqy_gate.service.weatherService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("weatherService")
public class weatherServiceImpl  implements weatherService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void saveWeather(Weather weather) {
        mongoTemplate.save(weather);
    }

    @Override
    public void removeWeather(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query,Weather.class);
    }

    @Override
    public void removeWeatherAll() {
        Query query = new Query(Criteria.where("by").is("utf-8"));
        mongoTemplate.remove(query,Weather.class);
    }

    @Override
    public void updateWeather(Weather weather) {

    }

    @Override
    public Weather findOne(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Weather weather = mongoTemplate.findOne(query,Weather.class);
        return weather;
    }

    @Override
    public List<Weather> findAll() {
        List<Weather> weatherList = mongoTemplate.findAll(Weather.class,"weatherCollection");
        return weatherList;
    }
}
