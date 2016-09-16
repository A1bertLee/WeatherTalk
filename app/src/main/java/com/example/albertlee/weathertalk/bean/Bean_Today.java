package com.example.albertlee.weathertalk.bean;

import java.util.List;

/**
 * Created by AlbertLee on 2016/9/1 18:21
 */
public class Bean_Today {
//    "today": {
//        "temperature": "22℃~30℃",
//                "weather": "多云转阵雨",
//                "weather_id": {
//            "fa": "01",
//                    "fb": "03"
//        },
//        "wind": "微风",
//                "week": "星期三",
//                "city": "新余",
//                "date_y": "2016年08月31日",
//                "dressing_index": "热",
//                "dressing_advice": "天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。",
//                "uv_index": "中等",
//                "comfort_index": "",
//                "wash_index": "较适宜",
//                "travel_index": "较适宜",
//                "exercise_index": "较适宜",
//                "drying_index": ""
//    }
    private String weather;

    private String city;
    private String date_y;
    private String uv_index;
    private String exercise_index;
    private String dressing_advice;
    private String temp;
    private String temperature;
    private String wind_direction;
    private String wind_strength;
    private String humidity;
    private String time;
    private List<Bean_Future> futureList;

    public List<Bean_Future> getFutureList() {
        return futureList;
    }

    public void setFutureList(List<Bean_Future> futureList) {
        this.futureList = futureList;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate_y() {
        return date_y;
    }

    public void setDate_y(String date_y) {
        this.date_y = date_y;
    }

    public String getUv_index() {
        return uv_index;
    }

    public void setUv_index(String uv_index) {
        this.uv_index = uv_index;
    }

    public String getExercise_index() {
        return exercise_index;
    }

    public void setExercise_index(String exercise_index) {
        this.exercise_index = exercise_index;
    }

    public String getDressing_advice() {
        return dressing_advice;
    }

    public void setDressing_advice(String dressing_advice) {
        this.dressing_advice = dressing_advice;
    }
    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getWind_strength() {
        return wind_strength;
    }

    public void setWind_strength(String wind_strength) {
        this.wind_strength = wind_strength;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


        private String fa;
        private String fb;

        public String getFa() {
            return fa;
        }

        public void setFa(String fa) {
            this.fa = fa;
        }

        public String getFb() {
            return fb;
        }

        public void setFb(String fb) {
            this.fb = fb;
        }
}
