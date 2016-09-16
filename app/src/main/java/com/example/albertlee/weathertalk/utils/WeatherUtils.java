package com.example.albertlee.weathertalk.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.albertlee.weathertalk.bean.Bean_Future;
import com.example.albertlee.weathertalk.bean.Bean_Today;
import com.thinkland.juheapi.common.JsonCallBack;
import com.thinkland.juheapi.data.weather.WeatherData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by AlbertLee on 2016/9/3 19:36
 */
public class WeatherUtils {

    private Bean_Today bean_today;
    private Context mContext;
    ArrayList<Bean_Future> futureList;
    SQLiteDatabase sqldb;
    DbHelper helper;


    public WeatherUtils(Context context){
        mContext = context;
        helper=new DbHelper(context);
        sqldb = helper.getWritableDatabase();
    }

    public void getData(String city) {
        WeatherData data = WeatherData.getInstance();
        data.getByCitys(city, 2, new JsonCallBack() {

            @Override
            public void jsonLoaded(JSONObject jsonObject) {
                Log.i("JSON_WEATHER:",jsonObject.toString());
                parserWeather(jsonObject);
                insertData(bean_today);
            }
        });

    }
    private void parserWeather(JSONObject s) {
        try {
            int resultcode = s.getInt("resultcode");
            int error_code = s.getInt("error_code");

            if(resultcode==200&error_code==0){
                JSONObject resultJson =s.getJSONObject("result");

                bean_today = new Bean_Today();
                JSONObject skJson = resultJson.getJSONObject("sk");
                bean_today.setTemp(skJson.getString("temp"));
                bean_today.setWind_direction(skJson.getString("wind_direction"));
                bean_today.setWind_strength(skJson.getString("wind_strength"));
                bean_today.setHumidity(skJson.getString("humidity"));
                bean_today.setTime(skJson.getString("time"));

                JSONObject todayJson = resultJson.getJSONObject("today");
                bean_today.setWeather(todayJson.getString("weather"));
                bean_today.setCity(todayJson.getString("city"));
                bean_today.setTemperature(todayJson.getString("temperature"));
                bean_today.setDate_y(todayJson.getString("date_y"));
                bean_today.setUv_index(todayJson.getString("uv_index"));
                bean_today.setExercise_index(todayJson.getString("exercise_index"));
                bean_today.setDressing_advice(todayJson.getString("dressing_advice"));

                bean_today.setFa(todayJson.getJSONObject("weather_id").getString("fa"));
                bean_today.setFb(todayJson.getJSONObject("weather_id").getString("fb"));

                JSONArray futureJsonArray = resultJson.getJSONArray("future");
                futureList = new ArrayList<>();
                for (int i = 0; i < futureJsonArray.length(); i++) {
                    JSONObject futureJson = futureJsonArray.getJSONObject(i);
                    Bean_Future bean_future = new Bean_Future();

                    bean_future.setWeather(futureJson.getString("weather"));
                    bean_future.setDate(futureJson.getString("date"));
                    bean_future.setTemperature(futureJson.getString("temperature"));
                    bean_future.setWeek(futureJson.getString("week"));
                    bean_future.setWind(futureJson.getString("wind"));
                    bean_future.setFa(futureJson.getJSONObject("weather_id").getString("fa"));
                    bean_future.setFb(futureJson.getJSONObject("weather_id").getString("fb"));

                    futureList.add(bean_future);

                }
                bean_today.setFutureList(futureList);
            } else {
                Toast.makeText(mContext, "Json Error", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void insertData(Bean_Today bean_today){
        helper.deleteTable();
        ContentValues ct = new ContentValues();
        ct.put("weather",bean_today.getWeather());
        ct.put("city",bean_today.getCity());
        ct.put("temperature",bean_today.getTemperature());
        ct.put("date_y",bean_today.getDate_y());
        ct.put("uv_index",bean_today.getUv_index());
        ct.put("exercise_index",bean_today.getExercise_index());
        ct.put("dressing_advice",bean_today.getDressing_advice());
        ct.put("temp",Integer.valueOf(bean_today.getTemp()));
        ct.put("wind_direction",bean_today.getWind_direction());
        ct.put("wind_strength",bean_today.getWind_strength());
        ct.put("humidity",bean_today.getHumidity());
        ct.put("time",bean_today.getTime());
        ct.put("fa",bean_today.getFa());
        ct.put("fb",bean_today.getFb());
        sqldb.insert("today", null, ct);// 插入数据

        for (int i = 0; i < futureList.size(); i++) {
            ContentValues ct_1 = new ContentValues();
            ct_1.put("temperature",futureList.get(i).getTemperature());
            ct_1.put("weather",futureList.get(i).getWeather());
            ct_1.put("wind",futureList.get(i).getWind());
            ct_1.put("week",futureList.get(i).getWeek());
            ct_1.put("date",futureList.get(i).getDate());
            ct_1.put("fa",futureList.get(i).getFa());
            ct_1.put("fb",futureList.get(i).getFb());
            sqldb.insert("future", null, ct_1);// 插入数据
        }
        Log.i("daily_log:---->","数据存储完毕！");
        ssl.onSuccess();//回调开始，通知fragment数据加载完毕，可以更新UI了。
    }

    private StorageStateListener ssl;
    public void setStorageStateListener(StorageStateListener ssl){
        this.ssl = ssl;
    }
    public interface StorageStateListener{
        void onSuccess();
    }
}
