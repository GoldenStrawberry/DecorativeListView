package com.joysuch.mylistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.joysuch.listviewstyle.DecorativeListView;

public class MainActivity extends AppCompatActivity implements
        DecorativeListView.OnDecorativeListViewItemClickListener {

    private DecorativeListView decorativeListView;
    private String data[] ={"F1","F2","F3","F4","F5","F6","F7","F8","F9"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        decorativeListView = (DecorativeListView) findViewById(R.id.dlistview);
        decorativeListView.setListViewItem(5);
        decorativeListView.setListViewData(data,6);
        decorativeListView.setOnDecorativeListViewItemClickListener(this);
    }

    @Override
    public void DecorativeListViewItemClick(View view, int position) {
        Toast.makeText(this,"点击了"+data[position],Toast.LENGTH_SHORT).show();
    }
}
