package com.example.albertlee.weathertalk.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.albertlee.weathertalk.R;
import com.example.albertlee.weathertalk.dao.Dao_Today;
import com.example.albertlee.weathertalk.utils.WeatherUtils;

import java.util.Calendar;

public class Fragment_Today extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    SwipeRefreshLayout swipeRefreshLayout;
    View view;
    private TextView tv_temperature;
    private TextView tv_temp;
    private TextView tv_exercise_index;
    private TextView tv_city;
    private ImageView iv_weather;
    private TextView tv_weather;
    private TextView tv_time_now;
    private TextView tv_wind_value;
    private TextView tv_humidity;
    private TextView tv_uv_index;
    private TextView tv_dressing_advice;
    LinearLayout layout;

    private Handler handler=new Handler();
    private Dao_Today mToday;
    private WeatherUtils weatherUtils;
    private String id = "d00";
    private String path = "com.example.albertlee.weathertalk";
    private String city;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            city = getArguments().getString("city");
        }
        mToday = new Dao_Today(getContext());
        weatherUtils = new WeatherUtils(getContext());
    }

    public Fragment_Today(){

    }

    public static Fragment_Today newInstance(String city) {

        Bundle args = new Bundle();
        args.putString("city",city);
        Fragment_Today fragment = new Fragment_Today();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view!=null){
            container.removeView(view);
        }else {
            view = inflater.inflate(R.layout.fragment__today, container, false);
            initView(view);
        }
        layout = (LinearLayout) view.findViewById(R.id.weather_overview_information);
        layout.setVisibility(View.GONE);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srfl);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        onRefresh();

        return view;
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                weatherUtils.getData(city);//这一操作略微耗时间，应该设置监听回调完成状态。
                //监听数据存储的状态，避免数据未能完全存储完毕便更新UI，导致UI界面错误！
//*********************************************************************
//        weatherUtils.getData(city);
//        tvSet();
//        以上这种写法，会导致tvSet()暂时没有数据源，因为getData的数据操作还未完毕。
//*********************************************************************
                weatherUtils.setStorageStateListener(new WeatherUtils.StorageStateListener() {
                    @Override
                    public void onSuccess() {
                        tvSet();
                        swipeRefreshLayout.setRefreshing(false);
                        layout.setVisibility(View.VISIBLE);
                    }
                });

            }
        }, 2000);
    }

    public void initView(View view){
        tv_temperature = (TextView) view.findViewById(R.id.temperature);
        tv_temp = (TextView) view.findViewById(R.id.temp);
        tv_exercise_index = (TextView) view.findViewById(R.id.exercise_index);
        tv_wind_value = (TextView) view.findViewById(R.id.wind_value);
        tv_city = (TextView) view.findViewById(R.id.cityName);
        tv_time_now = (TextView) view.findViewById(R.id.time_now);
        tv_humidity = (TextView) view.findViewById(R.id.humidity);
        tv_uv_index = (TextView) view.findViewById(R.id.uv_index);
        tv_dressing_advice = (TextView) view.findViewById(R.id.dressing_advice);
        tv_weather = (TextView) view.findViewById(R.id.weather);
        iv_weather = (ImageView) view.findViewById(R.id.weather_id);
    }
    public void tvSet(){
        Calendar c = Calendar.getInstance();
        int time = c.get(Calendar.HOUR_OF_DAY);
        if (time >= 6 && time < 18) {
            id = "d"+mToday.query("today","fa",1);
        } else {
            id = "n"+mToday.query("today","fb",1);
        }

        tv_temp.setText(mToday.query("today","temp",1)+"°");
        tv_temperature.setText(mToday.query("today","temperature",1));
        tv_exercise_index.setText(mToday.query("today","exercise_index",1));
        tv_wind_value.setText(mToday.query("today","wind_direction",1)+" "+mToday.query("today","wind_strength",1));
        tv_city.setText(mToday.query("today","city",1));
//        Log.i("city_init:---->",mToday.query("today","city",1));
        tv_time_now.setText(mToday.query("today","time",1));
        tv_humidity.setText(mToday.query("today","humidity",1));
        tv_uv_index.setText(mToday.query("today","uv_index",1));
        tv_dressing_advice.setText(mToday.query("today","dressing_advice",1));
        tv_weather.setText(mToday.query("today","weather",1));
        iv_weather.setImageResource(getResources().getIdentifier(id, "drawable", path));
    }
}
