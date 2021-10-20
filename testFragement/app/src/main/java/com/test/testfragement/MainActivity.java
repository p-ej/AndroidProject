package com.test.testfragement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.test.testfragement.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private ActivityMainBinding binding; // 데이터 바인딩

    private FragmentManager fragmentManager;
    private Fragment news, maps, homes, records, infos;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_view);
        fragmentManager = getSupportFragmentManager();
        // 처음 화면

        homes = new HomeFragment();
        fragmentManager.beginTransaction().add(R.id.frameLayout, homes).commit();
        bottomNavigationView.setSelectedItemId(R.id.thome); // 초기화면 중 바텀 셀렉트 지정

        // 바텀 네비뷰 안의 아이템 설정
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tnews:
                        if (news == null) {
                            news = new NewsFragment();
                            fragmentManager.beginTransaction().add(R.id.frameLayout, news).commit();
                        }
                        if(news!=null)fragmentManager.beginTransaction().show(news).commit();
                        if(maps!=null)fragmentManager.beginTransaction().hide(maps).commit();
                        if(homes!=null)fragmentManager.beginTransaction().hide(homes).commit();
                        if(records!=null)fragmentManager.beginTransaction().hide(records).commit();
                        if(infos!=null)fragmentManager.beginTransaction().hide(infos).commit();
                        return true;
                    // 이미지 슬라이드를 적용할 홈 프레그먼트
                    case R.id.tmap:
                        if (maps == null) {
                            maps = new MapFragment();
                            fragmentManager.beginTransaction().add(R.id.frameLayout, maps).commit();
                        }
                        if(news!=null)fragmentManager.beginTransaction().hide(news).commit();
                        if(maps!=null)fragmentManager.beginTransaction().show(maps).commit();
                        if(homes!=null)fragmentManager.beginTransaction().hide(homes).commit();
                        if(records!=null)fragmentManager.beginTransaction().hide(records).commit();
                        if(infos!=null)fragmentManager.beginTransaction().hide(infos).commit();
                        return true;
                    case R.id.thome:
                        if (homes == null) {
                            homes = new HomeFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, homes).commit();
                        }
                        if(news!=null)getSupportFragmentManager().beginTransaction().hide(news).commit();
                        if(maps!=null)getSupportFragmentManager().beginTransaction().hide(maps).commit();
                        if(homes!=null)getSupportFragmentManager().beginTransaction().show(homes).commit();
                        if(records!=null)getSupportFragmentManager().beginTransaction().hide(records).commit();
                        if(infos!=null)getSupportFragmentManager().beginTransaction().hide(infos).commit();
                        return true;
                    case R.id.trecord:
                        if (records == null) {
                            records = new RecordFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, records).commit();
                        }
                        if(news!=null)getSupportFragmentManager().beginTransaction().hide(news).commit();
                        if(maps!=null)getSupportFragmentManager().beginTransaction().hide(maps).commit();
                        if(homes!=null)getSupportFragmentManager().beginTransaction().hide(homes).commit();
                        if(records!=null)getSupportFragmentManager().beginTransaction().show(records).commit();
                        if(infos!=null)getSupportFragmentManager().beginTransaction().hide(infos).commit();
                        return true;
                    case R.id.tinfo:
                        if (infos == null) {
                            infos = new InfoFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, infos).commit();
                        }
                        if(news!=null)getSupportFragmentManager().beginTransaction().hide(news).commit();
                        if(maps!=null)getSupportFragmentManager().beginTransaction().hide(maps).commit();
                        if(homes!=null)getSupportFragmentManager().beginTransaction().hide(homes).commit();
                        if(records!=null)getSupportFragmentManager().beginTransaction().hide(records).commit();
                        if(infos!=null)getSupportFragmentManager().beginTransaction().show(infos).commit();
                        return true;
                }
                return false;
            }
        });
    }

}