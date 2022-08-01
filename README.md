# MarkTipsView
地图标识标签、文字标识标签、文字箭头标识。  
# 预览  
竖屏效果  
![效果](./ic_preview.png)
# 资源
|名字|资源| 
|-|-|
|AAR|[mark_tips_view.aar](https://github.com/RelinRan/MarkTipsView/blob/master/mark_tips_view_2022.8.1.1)|
|GitHub |[MarkTipsView](https://github.com/RelinRan/MarkTipsView)|
|Gitee|[MarkTipsView](https://gitee.com/relin/MarkTipsView)|
# Maven
1.build.grade | setting.grade
```
repositories {
	...
	maven { url 'https://jitpack.io' }
}
```
2./app/build.grade
```
dependencies {
	implementation 'com.github.RelinRan:MarkTipsView:2022.8.1.1'
}
```
# xml
~~~
<androidx.widget.MarkTipsView
    android:id="@+id/mark_tips"
    android:layout_width="200dp"
    android:layout_height="wrap_content"
    android:layout_margin="30dp"
    android:paddingHorizontal="8dp"
    android:paddingTop="5dp"
    android:text="重庆渝北区义学路政府\n106.631155,29.718087"
    android:gravity="center"
    android:textColor="@android:color/white"
    android:textSize="14sp"
    app:markRadius="5dp"
    app:markSolid="@android:color/holo_purple"
    app:triangleSide="10dp" />
~~~
# attrs.xml
~~~
<!--标识三角形边长-->
<attr name="triangleSide" format="dimension" />
<!--标识三角形高度-->
<attr name="markRadius" format="dimension" />
<!--标识填充颜色-->
<attr name="markSolid" format="color" />
~~~
# 使用
~~~
MarkTipsView mark_tips = findViewById(R.id.mark_tips);
//背景颜色
mark_tips.setMarkSolid(Color.parseColor("#AA66CC"));
//三角标识边长
mark_tips.setTriangleSide(30);
//标识圆角大小
mark_tips.setMarkRadius(20);
~~~
