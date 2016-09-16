package com.example.albertlee.weathertalk.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.albertlee.weathertalk.R;
import com.example.albertlee.weathertalk.dao.Dao_Today;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by AlbertLee on 2016/9/7 20:53
 */
public class SimleRecyclerCardAdapter extends RecyclerView.Adapter<SimleRecyclerCardAdapter.SimpleViewHolder> {

    private Context mContext;
//    private ArrayList<Bean_Future> mList = new ArrayList<>();
    private LayoutInflater mInflater;
    Dao_Today mToday;

    public SimleRecyclerCardAdapter(Context mContex){

        this.mContext = mContex;
//        this.mList = mList;
        mInflater = LayoutInflater.from(mContex);

    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_weather,parent,false);
        SimpleViewHolder simpleViewHolder = new SimpleViewHolder(view);
//        simpleViewHolder.setIsRecyclable(true);

        return simpleViewHolder;
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        mToday = new Dao_Today(mContext);
        int resid = mContext.getResources().getIdentifier("d"+mToday.query("future","fa",position+1),"drawable",mContext.getPackageName());
        if(resid!=0){
            holder.iv_week_icon.setImageResource(resid);
        }

        SimpleDateFormat sdf_date = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf_date.parse(mToday.query("future","date",position+1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(date);
        holder.tv_week_day.setText(mToday.query("future","week",position+1));
        holder.tv_week_weather.setText(mToday.query("future","weather",position+1));
        holder.tv_week_temp.setText(mToday.query("future","temperature",position+1));
//        holder.tv_week_date.setText(mToday.query("future","date",position+1));
        holder.tv_week_date.setText(str);

    }


    @Override
    public int getItemCount() {
        return 7;
    }

    private OnItemActionListener mOnItemActionListener;
    /**********定义点击事件**********/
    public   interface OnItemActionListener
    {
        public   void onItemClickListener(View v,int pos);
        public   boolean onItemLongClickListener(View v,int pos);
    }
    public void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.mOnItemActionListener = onItemActionListener;
    }

    class SimpleViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_week_day;
        private TextView tv_week_weather;
        private TextView tv_week_temp;
        private TextView tv_week_date;
        private ImageView iv_week_icon;


        public SimpleViewHolder(View itemView) {
            super(itemView);
            tv_week_day = (TextView) itemView.findViewById(R.id.week_day);
            tv_week_weather = (TextView) itemView.findViewById(R.id.week_weather);
            tv_week_temp = (TextView) itemView.findViewById(R.id.week_temp);
            tv_week_date = (TextView) itemView.findViewById(R.id.week_date);
            iv_week_icon = (ImageView) itemView.findViewById(R.id.week_icon);
        }
    }
}
