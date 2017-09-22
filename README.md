# DecorativeListView

[![](https://jitpack.io/v/GoldenStrawberry/DecorativeListView.svg)](https://jitpack.io/#GoldenStrawberry/DecorativeListView)

仿地图上楼层切换的控件 ---- DecorativeListView . 同步在CSDN http://blog.csdn.net/hnkwei1213/article/details/78068225

**控件可以实现的功能：**

 1. 设置ListView显示的条目个数。
 2. 设置ListView的数据和选中的条目。
 3. 获得条目点击的回调。

废话不多说，先来张效果图：

![这里写图片描述](http://img.blog.csdn.net/20170923003411693?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvaG5rd2VpMTIxMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**原理：**

*这个控件由两张图片和一个ListView组成，我们将他们添加到一个线性布局中即可实现。*

自定义View就是继承View或者其子类，重写方法。听了无数遍了，就不赘述了。

**1. 初始化时，设置子view的排列方向，对齐方向。**

**2.设置数据方法中，对各个view进行参数的设置，添加进容器中。**

**3.自定义的接口的调用。**

**4.以及ListView滑动时，图片状态的改变**。


## To get a Git project into your build:

### Step 1. Add the JitPack repository to your build file 
   Add it in your root build.gradle at the end of repositories:
   
   	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
### Step 2. Add the dependency

	dependencies {
	        compile 'com.github.GoldenStrawberry:DecorativeListView:V1.0'
	}
	
	
	
**Example：**

xml:
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#888"
    android:gravity="center"
    tools:context="com.joysuch.mylistview.MainActivity">
    <com.joysuch.listviewstyle.DecorativeListView
        android:id="@+id/dlistview"
        android:layout_alignParentLeft="true"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

</RelativeLayout>
```
code：

```
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
```

欢迎STAR!
