package com.joysuch.listviewstyle;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

import static android.widget.AbsListView.TRANSCRIPT_MODE_DISABLED;

/**
 * 装饰的ListView
 * Created by admin2017 on 2017/9/22.
 */

public class DecorativeListView extends LinearLayout implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    private Context context;
    private OnDecorativeListViewItemClickListener decorativeListViewItemClickListener;
    private ListView mListView;
    private ImageView upImage;
    private ImageView downImage;
    private Drawable downDrawable;
    private Drawable upDrawable;
    private Drawable downNoramlDrawable;
    private Drawable upNormalDrawable;
    private String[] data ;
    private int mItemWidth;
    private int mItemHeight;
    private float mFontSize;
    private int mMaxItemSize;
    private int listViewHeight;
    private int currentSelectPosition;
    private MyAdapter myAdapter;

    public DecorativeListView(Context context) {
        this(context,null);
    }

    public DecorativeListView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DecorativeListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context);
    }

    private void init(Context context) {
        //设置DecorativeListView子view的排列方向
        setOrientation(VERTICAL);
        //居中对齐
        setGravity(Gravity.CENTER);
        mItemWidth = 100;
        mItemHeight = 100;
        mFontSize = 18.0F;
        mMaxItemSize = 5;
        mListView = new ListView(context);
        upImage = new ImageView(context);
        downImage = new ImageView(context);

        try {
            downNoramlDrawable = Utils.getDrawable(context, "allow_down_normal.png");
            upNormalDrawable = Utils.getDrawable(context, "allow_up_normal.png");
            downDrawable = Utils.getDrawable(context, "allow_down_scroll.png");
            upDrawable = Utils.getDrawable(context, "allow_up_scroll.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mListView.setVerticalScrollBarEnabled(false);
        mListView.setBackgroundColor(Color.parseColor("#FDFDDF"));
        mListView.setOnItemClickListener(this);
        mListView.setOnScrollListener(this);
    }

    /**
     * 设置listview的显示条目的个数
     * @param num
     */
    public void setListViewItem(int num){
        mMaxItemSize = num;
    }

    /**
     * 设置数据和选中的条目
     * @param data
     * @param selectID
     */
    public void setListViewData(String[] data ,int selectID){
        if(data == null){
            return ;
        }
        this.data = data;
        LayoutParams upImageParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        LayoutParams downImageParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);

        if(data.length > mMaxItemSize){
            listViewHeight = mMaxItemSize * mItemHeight ;
        }else {
            listViewHeight = data.length * mItemHeight ;
        }
        LayoutParams listViewParams = new LayoutParams(mItemWidth,listViewHeight);
        addView(upImage,upImageParams);
        addView(mListView,listViewParams);
        addView(downImage,downImageParams);
        myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);
        mListView.setSelection(selectID);
        currentSelectPosition = selectID ;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        currentSelectPosition = position;
        decorativeListViewItemClickListener.DecorativeListViewItemClick(view,position);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        int firstVisiblePosition = mListView.getFirstVisiblePosition();
        int lastVisiblePosition = mListView.getLastVisiblePosition();
        //在listView的头和尾item显示时，使知识图片变为白色
        if(firstVisiblePosition == 0){
            if(upNormalDrawable != null&& upNormalDrawable != upImage.getDrawable()){
                upImage.setImageDrawable(upNormalDrawable);//白
            }
        }else if(upDrawable != null&& upDrawable != upImage.getDrawable()){
            upImage.setImageDrawable(upDrawable);//蓝
        }
        if(data == null){
            return;
        }
        if(lastVisiblePosition == data.length-1){
            if(downNoramlDrawable != null && downNoramlDrawable != downImage.getDrawable()){
                downImage.setImageDrawable(downNoramlDrawable);//白
            }
        }else if(downDrawable != null && downDrawable != downImage.getDrawable()){
            downImage.setImageDrawable(downDrawable); //蓝
        }
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int position) {
            return data[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ItemHolder itemHolder;
            if(convertView == null){
                itemHolder = new ItemHolder(context);
                convertView = itemHolder.textView;
                convertView.setTag(itemHolder);
            }else {
                itemHolder = (ItemHolder) convertView.getTag();
            }
            itemHolder.textView.setText(data[position]);
            if(position == currentSelectPosition){
                itemHolder.textView.setTextColor(Color.BLUE);
            }else {
                itemHolder.textView.setTextColor(Color.GRAY);
            }
            return itemHolder.textView;
        }
    }

    private class ItemHolder{
        TextView textView ;
        public ItemHolder(Context context){
            textView = new TextView(context);
            textView.setTextSize(mFontSize);
            textView.setGravity(Gravity.CENTER);
            LayoutParams params = new LayoutParams(mItemWidth,mItemHeight);
            textView.setLayoutParams(params);
        }
    }

    /**
     * 条目点击监听
     * @param decorativeListViewItemClickListener
     */
    public  void setOnDecorativeListViewItemClickListener(
            OnDecorativeListViewItemClickListener decorativeListViewItemClickListener){
        this.decorativeListViewItemClickListener = decorativeListViewItemClickListener ;
    }

    public interface OnDecorativeListViewItemClickListener{
        void DecorativeListViewItemClick(View view ,int position);
    }
}
