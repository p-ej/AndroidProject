package com.test.testfragement;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.test.testfragement.DTO.MapsDTO;
import com.test.testfragement.DTO.MyItem;
import com.test.testfragement.MarkerImage.MarkerClusterRenderer;
import com.test.testfragement.Task.TestTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapFragment2 extends Fragment implements OnMapReadyCallback {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private ClusterManager<MyItem> clusterManager;
    private MapView mapView = null;
    private Context ct;
    private Button btn1,btn2,btn3;
    private float col = 0.3f;

    GoogleMap gMap;
    MarkerOptions markerOptions;



//    private ActivityMainBinding binding; // 데이터 바인딩

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        binding = DataBindingUtil.setContentView(getActivity(),R.layout.activity_main);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map,container,false);
        ct = container.getContext();

        mapView = view.findViewById(R.id.map);
        mapView.getMapAsync(this);

        this.btnSetting(view,ct); // 버튼 객체 초기화, 리스너 설정
        return view;
    }

    // 버튼 객체 초기화
    public void btnSetting(View view,Context ct){

        btn1 = view.findViewById(R.id.btn1); // 병원
        btn2 = view.findViewById(R.id.btn2); // 체육시설
        btn3 = view.findViewById(R.id.btn3); // 약국

        btn1.setAlpha(col);
        btn2.setAlpha(col);
        btn3.setAlpha(col);



        // 버튼 하나에 대한 이벤트를 생성하기 위해 리스너 코드 구조가 이렇게 되어 있음.
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                btn1.setAlpha(1f);
                btn2.setAlpha(col);
                btn3.setAlpha(col);
                gMap.clear();
//                Toast.makeText(ct,"병원",Toast.LENGTH_SHORT).show();

                TestTask testTask = new TestTask(handler); // 핸들러 객체 생성
                Map<String, String> params = new HashMap<>();
                params.put("kind","병원");
                Log.d("params data ",params.toString());
                testTask.execute(params);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                btn2.setAlpha(1f);
                btn1.setAlpha(col);
                btn3.setAlpha(col);
                gMap.clear();
//                Toast.makeText(ct,"체육시설",Toast.LENGTH_SHORT).show();

                TestTask testTask = new TestTask(handler); // 핸들러 객체 생성
                Map<String, String> params = new HashMap<>();
                params.put("kind","체육시설");

                testTask.execute(params);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                btn3.setAlpha(1f);
                btn1.setAlpha(col);
                btn2.setAlpha(col);
                gMap.clear();
//                Toast.makeText(ct,"약국",Toast.LENGTH_SHORT).show();

                TestTask testTask = new TestTask(handler); // 핸들러 객체 생성
                Map<String, String> params = new HashMap<>();
                params.put("kind","약국");

                testTask.execute(params);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    //액티비티가 처음 생성될 때 실행되는 함수

        if(mapView != null)
        {
            mapView.onCreate(savedInstanceState);
        }
    }

    // 맵 호출
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;
        LatLng SEOUL = new LatLng(37.56, 126.97);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15)); // 나중에 현재위치 표시할것 .

        // 현재위치 GPS 버튼 표시와 퍼미션(권한) 체크
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
            Log.d("위치 Lan : ", Manifest.permission.ACCESS_FINE_LOCATION);
        } else {
            checkLocationPermissionWithRationale();
        }
    }

    // 위치 체크 퍼미션
    private void checkLocationPermissionWithRationale() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("위치정보")
                        .setMessage("이 앱을 사용하기 위해서는 위치정보에 접근이 필요합니다. 위치정보 접근을 허용하여 주세요.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        }).create().show();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }


    // 리퀘스트 퍼미션 (퍼미션에 대한 권한 요청)
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        gMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(getActivity(), "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }


    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {

            switch (msg.what) {

                case -1:
                    Toast.makeText(getActivity(), "검색된 데이터가 존재하지 않음.", Toast.LENGTH_SHORT).show();
                    break;
                case 0:
                    ArrayList<MapsDTO> mapslist = (ArrayList<MapsDTO>) msg.obj;
//                    binding.tvTest2.setText(mapslist.get(0).toString());
                    Log.d("list in data : ", String.valueOf(mapslist));
//                    markerCreate(mapslist.get(0).getKind(), mapslist);

                    setUpClusterer(mapslist); // 클러스터링 적용
                    break;

            }

        }
    };

    // 구글맵 마커 클러스터링 처리
    private void setUpClusterer(ArrayList<MapsDTO> dataArray){
        Bitmap bitmap =getBitmapFromVectorDrawable(getActivity(),R.drawable.ic_mapsmarker);
        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromBitmap(bitmap);

        clusterManager = new ClusterManager<MyItem>(getActivity(), gMap);
        clusterManager.setRenderer(new MarkerClusterRenderer(getActivity(),gMap,clusterManager)); // 마커 이미지 적용

        gMap.setOnCameraIdleListener(clusterManager);
        gMap.setOnMarkerClickListener(clusterManager);

        addItems(dataArray, descriptor);

        // 클러스터 클릭 시 카메라 확대 후 전환
        clusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MyItem>() {
            @Override
            public boolean onClusterClick(Cluster<MyItem> cluster) {
                LatLng latLng = new LatLng(cluster.getPosition().latitude, cluster.getPosition().longitude);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
                gMap.moveCamera(cameraUpdate);
                return false;
            }
        });

        clusterManager.setOnClusterItemInfoWindowClickListener(new ClusterManager.OnClusterItemInfoWindowClickListener<MyItem>() {
            @Override
            public void onClusterItemInfoWindowClick(MyItem item) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("정보");
                builder.setMessage("이름 : "+item.getTitle() +
                        "\n주소 : " + "냥냥이 주소" +
                        "\n전화번호 : " + item.getPhone()
                );

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setGravity(Gravity.BOTTOM);
                alertDialog.show();
            }
        });
    }

    private void addItems(ArrayList<MapsDTO> dataArray, BitmapDescriptor des){
        for(int i=0;i<dataArray.size();i++){
            MyItem offsetItem = new MyItem(dataArray.get(i).getLat(),dataArray.get(i).getLng(), dataArray.get(i).getName(),dataArray.get(i).getPhone(), des);
            clusterManager.addItem(offsetItem);
        }
    }

    // 벡터 -> 비트맵 변환
    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable =  AppCompatResources.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

}