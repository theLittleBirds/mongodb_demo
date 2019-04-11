package com.zd.interf.dqy_gate.service;

import com.zd.interf.dqy_gate.document.Weather;

import java.util.List;

/*
 *  Creat by zhoudi on 2019/4/10.
 */
public interface weatherService {
    void saveWeather(Weather weather);

    void removeWeather(String id);

    void updateWeather(Weather weather);

    List<Weather> findAll();

    Weather findOne(String id);

    void removeWeatherAll();
}
