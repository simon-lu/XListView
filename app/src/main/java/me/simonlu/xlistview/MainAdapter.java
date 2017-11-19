package me.simonlu.xlistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MainAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<News.NewsListBean> datas;

    public MainAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(ArrayList<News.NewsListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas != null ? datas.size(): 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        MyViewHolder myViewHolder = null;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
            myViewHolder = new MyViewHolder(view);
            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        // 设置数据
        myViewHolder.textView.setText(datas.get(i).getTitle());
        // 获取图片
//        Drawable drawable = loadImageFromNetwork(datas.get(i).getImageUrl());
//        myViewHolder.imageView.setImageDrawable(drawable);
        try {
            byte[] imagedata = ImageServce.getNetImage(datas.get(i).getImageUrl());
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagedata,0, imagedata.length);

            myViewHolder.imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            // Toast.makeText(GetImageActivity.this, R.string.error, 1).show(); // 显示错误信息
            Log.e(TAG, e.toString()); // 生成异常日志
        }
        return view;
    }


    class MyViewHolder {

        private TextView textView;
        private ImageView imageView;

        MyViewHolder(View itemView) {
            textView = (TextView) itemView.findViewById(R.id.list_item_textview);
            imageView = (ImageView) itemView.findViewById(R.id.list_item_imageview);
        }
    }
}
