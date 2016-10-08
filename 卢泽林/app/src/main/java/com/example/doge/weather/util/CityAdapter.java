package com.example.doge.weather.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doge.weather.R;
import com.example.doge.weather.model.*;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Doge on 2016/10/6.
 */
public class CityAdapter extends ArrayAdapter<City> {

    private int resourceId;

    private Bitmap bitmap;

    public CityAdapter(Context context, int resource, List<City> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        City city = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView!=null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.city_name = (TextView) view.findViewById(R.id.city_name);
            viewHolder.temperature = (TextView) view.findViewById(R.id.temperature);
            viewHolder.city_pcpn = (TextView) view.findViewById(R.id.city_pcpn);
            viewHolder.city_date = (TextView) view.findViewById(R.id.city_date);
            viewHolder.rain = (ImageView) view.findViewById(R.id.rain);
            viewHolder.weather = (ImageView) view.findViewById(R.id.city_weather);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
            HttpUtil.sendHttpRequest("http://files.heweather.com/cond_icon/" + city.getIcon() + ".png", new HttpCallbackListener() {
                @Override
                public void onFinish(InputStream in) {
                    bitmap = BitmapFactory.decodeStream(in);
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                }
            });
            viewHolder.weather.setImageBitmap(bitmap);
            viewHolder.rain.setImageResource(R.mipmap.rain);
            viewHolder.city_name.setText(city.getCityName());
            viewHolder.city_date.setText(city.getLocTime());
            viewHolder.city_pcpn.setText(city.getPcpn());
            viewHolder.temperature.setText(city.getTmp());

        return view;
    }

    class ViewHolder{
        TextView city_name,temperature,city_pcpn,city_date;
        ImageView rain,weather;
    }
}
