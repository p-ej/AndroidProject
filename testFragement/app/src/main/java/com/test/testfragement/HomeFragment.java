package com.test.testfragement;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment {

    EventDialogFragment e;
    // 이미지 슬라이드를 위한 필드
    // creating object of ViewPager
    ViewPager mViewPager;

    Context ct;

    // images array
    int[] images = {R.drawable.rule1, R.drawable.rule2, R.drawable.rule3};

    // Creating Object of ViewPagerAdapter
    pageAdapter mViewPagerAdapter;

    // 써클
    CircleIndicator indicator;

    TextView tv;
    TextView tv2;

    // 초마다 랜덤 텍스트 뷰를 위한 핸들러
    private Handler handler;
    private Runnable handlerTask;

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;
    final long PERIOD_MS = 3000;

    // random text
    // 랜덤 텍스트 DB대신 사용한 Test 값
    String testArr[] = new String[]{"고혈압1","고혈압2","고혈압3","저혈압1","저혈압2","저혈압3","당뇨1","당뇨2","당뇨3"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        randomView(view);

        tv = view.findViewById(R.id.textview); // 사용자의 질병이 제대로 표시되는지 테스트
        tv2 = view.findViewById(R.id.textview2); // 사용자의 등록된 질병들
        tv2.setText("고혈압, 저혈압, 당뇨");
        // 뷰페이저 개수 표시


        // 질병데이터 정보
        // 기록정보
        // 그래프
        BarChart mBarChart = view.findViewById(R.id.barchart);
        mBarChart.addBar(new BarModel("아침전",1.1f, 0xFF873F56)); // 뻘겅
        mBarChart.addBar(new BarModel("아침후",4.f,  0xFF1BA4E6)); // 연한 퍼렁
        mBarChart.addBar(new BarModel("점심전",3.f,  0xFF1BA4E6)); // 연한 퍼렁
        mBarChart.addBar(new BarModel("점심후",2.f,  0xFF873F56)); // 연한 퍼렁
        mBarChart.addBar(new BarModel("저녁전",7.f,  0xFF1BA4E6)); // 연한 퍼렁
        mBarChart.addBar(new BarModel("저녁후",0.5f,  0xFF873F56)); // 연한 퍼렁
        mBarChart.addBar(new BarModel("자기전",2.f,  0xFF1BA4E6)); // 연한 퍼렁
        mBarChart.startAnimation(); // 그래프 차트 시작

        // 추천 음식 정보
        foodData(); // DB에서 음식 데이터 가져오는 함수

        return view;
    }

//    public void foodData(){
//        StartTimer();
//    } // 이 함수를 빼고 바로 StartTimer()함수를 넣어도 되지 않을까 생각.

    // 랜덤 텍스트 출력하기
    public void foodData(){ // StartTimer() 함수를 foodData()로 변경
        handler = new Handler();
        handlerTask = new Runnable() {
            @Override
            public void run() {
                int num = (int)(Math.random() * testArr.length);
                tv.setText(testArr[num]);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        e = EventDialogFragment.getInstance();
                        e.show(getActivity().getSupportFragmentManager(), EventDialogFragment.TAG_EVENT_DIALOG);
                    }
                });
                handler.postDelayed(handlerTask,1000);
            }
        };
        handlerTask.run();
    }

    // 이미지 슬라이드 (뷰페이저) 자동
    public void randomView(View view){

        mViewPager = view.findViewById(R.id.pager);

        mViewPagerAdapter = new pageAdapter(getActivity(), images);
        mViewPager.setAdapter(mViewPagerAdapter);

        indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable(){

            @Override
            public void run() {
                if(currentPage == images.length){
                    currentPage = 0;
                }
                mViewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }
}