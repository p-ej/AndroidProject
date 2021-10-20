
package com.test.hometest;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;

import java.util.ArrayList;
import java.util.Arrays;

import me.relex.circleindicator.CircleIndicator;

// home
public class fragmentHome extends Fragment {
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

    // 파이어베이스 연동 및 데이터 가져올 필드
    DatabaseReference mReference;
    DatabaseReference mReference2;
    DatabaseReference mReference3;

    TextView tv;
    TextView tv2;

    // 디비에서 가져온 값 테스트 데이터
    ArrayList<String> testdbArr = new ArrayList<>();
    ArrayList<String> fooddbArr = new ArrayList<>();
    ArrayList<Integer> recordArr = new ArrayList<>();

    // 초마다 랜덤 텍스트 뷰를 위한 핸들러
    private Handler handler;
    private Runnable handlerTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        assert container != null;
        ct = container.getContext();

        View view = inflater.inflate(R.layout.fragment_home,container,false);
        mViewPager = view.findViewById(R.id.pager);


        mViewPagerAdapter = new pageAdapter(getActivity(), images);
        mViewPager.setAdapter(mViewPagerAdapter);

        indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);

        tv = view.findViewById(R.id.textview); // 사용자의 질병이 제대로 표시되는지 테스트
        tv2 = view.findViewById(R.id.textview2); // 사용자의 등록된 질병들
        // 뷰페이저 개수 표시


        // 파이어베이스 등록된 질병에 따른 음식 표시
//        String userId = "-Mar75hefcmh8Re5KrLM";
        String userId2 = "-Mar75eqKmCQbxCHPT8t"; // 테스트 user id값
        String userId3 = "-MayF-Yu-4OJOKpv8_G8";
        mReference2 = FirebaseDatabase.getInstance().getReference("user").child(userId2).child("sick");
        // child("-MafTQUBwwp1cMqpHZqR") => child(userId)
        mReference2.addValueEventListener(new ValueEventListener() { // 값에 대한 이벤트 객체 생성
            int i = 0;
            String str = "";
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data : snapshot.getChildren()){
                    testdbArr.add((String)data.getValue());
                    str += testdbArr.get(i) + " ";
                    i++;
                }
                tv2.setText(str);
                foodData(testdbArr);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("TAG:", "Failed to read value",error.toException());
            }
        });

        // 그래프
        BarChart mBarChart = view.findViewById(R.id.barchart);

//        mBarChart.addBar(new BarModel(1.1f, 0xFF873F56)); // 뻘겅
//
//        mBarChart.addBar(new BarModel(4.f,  0xFF1BA4E6)); // 연한 퍼렁

        String[] arrTest = new String[]{"아침전","아침후","점심전","점심후","저녁전","저녁후","자기전"}; // 그래프 표시

        mReference3 = FirebaseDatabase.getInstance().getReference("user").child(userId3).child("bloodsugar");
        mReference3.orderByKey().addChildEventListener(new ChildEventListener() {
            int i = 0;
            @Override
            public void onChildAdded(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                //String.valueOf(previousChildName) //
                if(Integer.parseInt(snapshot.getValue().toString())>120 && 200 > Integer.parseInt(snapshot.getValue().toString())){
                    mBarChart.addBar(new BarModel(arrTest[i++],Integer.parseInt(snapshot.getValue().toString()),0xFF1BA4E6));
                }
                else{
                    mBarChart.addBar(new BarModel(arrTest[i++],Integer.parseInt(snapshot.getValue().toString()),0xFF873F56));
                }
//                0xFF873F56 뻘겅
//                0xFF1BA4E6 퍼렁
                mBarChart.startAnimation();
                recordArr.add(Integer.parseInt(snapshot.getValue().toString()));
                System.out.println(previousChildName);
            }

            @Override
            public void onChildChanged(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }


            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Log.d("recordArr ", Arrays.toString(mBarChart.getData().toArray()));

        // 마지막 뷰 리턴
        return view;
    }


    // 등록된 질병에 따른 음식 데이터들 출력
    public void foodData(ArrayList testdbArr){
        // testdbArr : [고혈압, 당뇨]
        mReference = FirebaseDatabase.getInstance().getReference("fooddata");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("fooddbArr test ", String.valueOf(testdbArr.size()));
                for(int i=0;i<testdbArr.size();i++){
                    Log.d("fooddata arr test ", testdbArr.get(i).toString());
                    for(DataSnapshot data : snapshot.child(testdbArr.get(i).toString()).getChildren()){
//                        fooddbArr.add(data.getValue().toString());
                        Log.d("고혈압 스냅샷",String.valueOf(data.getValue()));
                        fooddbArr.add(String.valueOf(data.getValue()));
                    }
                }
                Log.d("fooddbArr test ", fooddbArr.toString());
                // fooddbArr = [고혈압과 당뇨의 음식 데이터들이 들어감.]
                StartTimer(fooddbArr);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("TAG:", "Failed to read value",error.toException());
            }
        });
    }

    // 랜덤 텍스트 출력하기
    public void StartTimer(ArrayList fooddbArr){
        handler = new Handler();
        handlerTask = new Runnable() {
            @Override
            public void run() {
                int num = (int)(Math.random() * fooddbArr.size());
                tv.setText(fooddbArr.get(num).toString());
                handler.postDelayed(handlerTask,1000);
            }
        };
        handlerTask.run();
    }



}