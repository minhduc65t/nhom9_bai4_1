package com.example.nhom9_bai4_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private List<Video> videoList;
    private  VideoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoList = new ArrayList<>();
        viewPager2 = findViewById(R.id.viewPager2);

        videoList.add(new Video("android.resource://"+getPackageName()+ "/" + R.raw.video_one, "New Title","Love Screnn"));
        videoList.add(new Video("android.resource://"+getPackageName()+ "/" + R.raw.video_two, "New Title","Love Screnn"));
        adapter = new VideoAdapter(videoList);
        viewPager2.setAdapter(adapter);
    }

}