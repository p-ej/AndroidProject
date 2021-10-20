package com.test.hometest;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

// home
public class fragmentHomeMemo extends Fragment {
    // 이미지 슬라이드를 위한 필드
    // creating object of ViewPager
    ViewPager mViewPager;

    Context ct;

    // images array
    int[] images = {R.drawable.test1, R.drawable.home, R.drawable.test3};

    // Creating Object of ViewPagerAdapter
    pageAdapter mViewPagerAdapter;

    // 써클
    CircleIndicator indicator;

    // 파이어베이스 연동 및 데이터 가져올 필드
    DatabaseReference mReference;
    DatabaseReference mReference2;
    Users user;
//    ArrayList<Users> infoList = new ArrayList<>();

    TextView tv;

    // 테스트 데이터
    String[] textArr1 = {"사과","배","딸기"}; // 고혈압
    String[] textArr2 = {"소금","김치","까나리"}; // 당뇨
    String[] textArr3 = {"찌개","생선","???"}; // 저혈압

    // 디비에서 가져온 값 테스트 데이터
    ArrayList<String> testdbArr = new ArrayList<>();
    ArrayList<String> fooddbArr = new ArrayList<>();

    private Handler handler;
    private Runnable handlerTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ct = container.getContext();
        // Inflate the layout for this fragment
        // Initializing the ViewPager Object
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        mViewPager = (ViewPager)view.findViewById(R.id.pager);

        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new pageAdapter(ct, images);

        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);
        tv = (TextView) view.findViewById(R.id.textview); // 사용자의 질병이 제대로 표시되는지 테스트
        indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);
        String userId = "-Mar75hefcmh8Re5KrLM";
//        mReference = FirebaseDatabase.getInstance().getReference();
        mReference2 = FirebaseDatabase.getInstance().getReference("user").child(userId).child("sick");
        // child("-MafTQUBwwp1cMqpHZqR") => child(userId)
        // 유저의 질병 가져오기 ( sick 구분 )
//        mReference.child("user").child(userId).child("sick").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(Task<DataSnapshot> task) {
//                if(!task.isSuccessful()){
//                    Log.e("firebase","Error getting data",task.getException());
//                }else{
//                    Log.d("firebase success ",String.valueOf(task.getResult().getValue()));
//
////                    tv.setText(String.valueOf(task.getResult().getValue()));
////                    user = new Users(userId,String.valueOf(task.getResult().getValue()));
//                    // 등록된 질병으로 값 띄우기 (우선 1개 > 여러개 )
////                    StartTimer(user.sick); // 배열에 저장된 음식 텍스트로 띄우기
//                }
//            }
//        });

        mReference2.addValueEventListener(new ValueEventListener() { // 값에 대한 이벤트 객체 생성
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot data : snapshot.getChildren()){
                    testdbArr.add((String)data.getValue());
                }
                foodData(testdbArr);

//                Object str = snapshot.child("user").child(userId).child("sick").getValue(Object.class);
//                Object str2 = snapshot.child("fooddata").child(str.toString()).getValue(Object.class);
//                Log.d("firebase success ",str.toString());
//                Log.d("firebase success ",str2.toString());

//                for (DataSnapshot data : snapshot.getChildren()) {
//                        testdbArr = new String[]{(String) data.child("sick").getValue()};
//                    Log.d("firebase success ",String.valueOf(data.getValue()));
//                    testdbArr.add(String.valueOf(data.getValue()));
//                }
//                Log.d("firebase fooddata ",String.valueOf(testdbArr.get(1)));
//                foodData(testdbArr);
            }
            @Override
            public void onCancelled(DatabaseError error) {
//                Log.w("TAG:", "Failed to read value",error.toException());
            }
        });
        // 해당 유저 질병에 관련된 데이터 가져오기 (질병을 여러개 가지고 있을 경우 )

        return view;
    }

//    public void randomData(String sick){
//        tv.setText(sick); // 데이터 출력 텍스트 테스트
//    }

    public void foodData(ArrayList testdbArr){
        // testdbArr : [고혈압, 당뇨]
        mReference = FirebaseDatabase.getInstance().getReference("fooddata");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
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
//                Object str = snapshot.child(testdbArr.get(0).toString()).getValue(Object.class);
//                Object str2 = snapshot.child(testdbArr.get(1).toString()).getValue(Object.class);
//                Log.d("test read ",str.toString());
//                Log.d("test read ",str2.toString());
//                for(DataSnapshot data : snapshot.getChildren()){
//
//                    Log.d("foodData function data",String.valueOf(data.child("저혈압").getValue()));
//                    Log.d("foodData function data",String.valueOf(data.child("고혈압").getValue()));
//                    Log.d("foodData function data",String.valueOf(data.child("당뇨").getValue()));
//                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    public void StartTimer(String sick){
        handler = new Handler();
        handlerTask = new Runnable() {
            @Override
            public void run() {
                int textran1 = (int)(Math.random() * textArr1.length);
                int textran2 = (int)(Math.random() * textArr2.length);
                int textran3 = (int)(Math.random() * textArr3.length);

                if(sick.equals("고혈압")){
                    tv.setText(textArr1[textran1]);
                }
                else if(sick.equals("당뇨")){
                    tv.setText(textArr2[textran2]);
                }
                else if(sick.equals("저혈압")){
                    tv.setText(textArr3[textran3]);
                }
                handler.postDelayed(handlerTask,1000);
            }
        };
        handlerTask.run();
    }

}