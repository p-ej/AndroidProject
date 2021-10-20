package com.test.testfragement.Task;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.test.testfragement.DTO.MapsDTO;
import com.test.testfragement.Http.HTTPClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class TestTask extends AsyncTask<Map<String, String>, String, String> {

//    final String ip = "192.168.0.5:8087/ForHeart"; // 주성
    final String ip = "192.168.35.201:9091/ForHeart"; // 의진
    private Handler handler;
    private Exception exception;

    public TestTask(Handler handler) {

        this.handler = handler;

    }

    @Override
    protected String doInBackground(Map<String, String>... maps) {
        // http 로컬 서버 연결 요청 및 값 전송 -> 이클립스
        try {

//            HTTPClient.Builder http = new HTTPClient.Builder("POST", "http://" + ip + "/AndroidTest");
            HTTPClient.Builder http = new HTTPClient.Builder("POST", "http://" + ip + "/ForHeartTest"); // 해당 로컬 주소 객체 생성

            http.addAllParameters(maps[0]); // 전달받은 값을 받음, 텍스트 객체 하나만 찾는거임. 키를 전송

            HTTPClient post = http.create(); // create() 함수
            post.request();  // post 요청

            int statusCode = post.getHttpStatusCode();

            String body = post.getBody(); // 포스트 바디? 시2발 뭐야이거

            if (body.equals("")) {
                throw new Exception();
            }

            return body; // 바디 리턴 그냥 받은 값을 리턴한다

        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        Message msg = handler.obtainMessage();

        if (exception != null) {

            msg.what = -1;
            msg.obj = exception;
//            return;

        } else {

            Gson gson = new Gson();
//            TestDTO testDTO = gson.fromJson(s, TestDTO.class); // 테스트

//            MapsDTO mapsDTO = gson.fromJson(s,MapsDTO.class); // location2 컬렉션 값 1개만 가져와보기 테스트
//            String mapsString = gson.toJson(s, MapsDTO.class); // json 포맷 형식으로 만들어주는 메서드
            Type type = new TypeToken<ArrayList<MapsDTO>>(){}.getType();
            ArrayList<MapsDTO> mapsList = gson.fromJson(s,type);

            msg.what = 0;
            msg.obj = mapsList;

        }
        handler.sendMessage(msg);
    }

}
