package com.example.albertlee.weathertalk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.example.albertlee.weathertalk.utils.LocationService;

/**
 * Created by AlbertLee on 2016/9/10 18:45
 */
public class SplashActivity extends Activity {

    private String city=null;
    private LocationService locationService;
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 1:
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    intent.putExtra("city",city);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        locationService = new LocationService(this);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.registerListener(mListener);
        locationService.start();// 定位SDK
        // start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request
    }

    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                city = location.getCity();
                Log.i("city ",city);
                if(city!=null){
                    handler.sendEmptyMessage(1);
                } else {
                    Toast.makeText(SplashActivity.this, "定位失败！", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onStop() {
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop();
        super.onStop();
    }
}
