package com.test.hometest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
// 메인 클래스
    // 바텀 네비게이션
    BottomNavigationView bottomNavigationView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_view);

        // 처음 화면
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, new fragmentHome()).commit();

        // 바텀 네비뷰 안의 아이템 설정
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ttTest:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Fragment1()).commit();
                        break;
                    // 이미지 슬라이드를 적용할 홈 프레그먼트
                    case R.id.ttHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new fragmentHome()).commit();
                        break;
                    case R.id.ttTest2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Fragment2()).commit();
                        break;
                }
                return true;
            }
        });

    }

}