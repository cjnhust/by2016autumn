package com.example.administrator.weathertimeline10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.weathertimeline10.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/3/003.
 */

public class WeatherAdapter extends ArrayAdapter {
    private int resourceID;//布局文件
    public WeatherAdapter(Context context, int textViewRsourceID, List<Weather> objects){//textViewRsourceID是构造器
        super(context,textViewRsourceID,objects);
        resourceID=textViewRsourceID;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){//用于缓存加载好的View
        Weather weather = (Weather) getItem(position);//获取当前项的Weather实例  注意
        View view= LayoutInflater.from(getContext()).inflate(resourceID,null);
        ImageView weatherImage=(ImageView) view.findViewById(R.id.Weather_Image);
        TextView cityName=(TextView) view.findViewById(R.id.City_View);
        TextView temptureNum=(TextView) view.findViewById(R.id.temptureView) ;
        weatherImage.setImageResource(weather.getImageID());
        cityName.setText(weather.getName());
        temptureNum.setText(weather.getTempture());
        return view;
    }
}
