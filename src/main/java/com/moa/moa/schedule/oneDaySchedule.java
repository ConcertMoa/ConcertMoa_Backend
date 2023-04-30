package com.moa.moa.schedule;

import com.moa.moa.dto.concertDTO;
import lombok.extern.java.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
@Log
public class oneDaySchedule {
    static private String secretKey = "f0809716ad6b4f6c8ee33c97714b343a";

    //스케줄 생성 초(0~59) 분(0~59) 시(0~23) 일(1~31) 월(1~12) 요일(0~7)
    @Scheduled(cron = "0 0 12 * * *")
    public void oneDaySearch(){
        log.info("scheduled");
        //오늘 날짜 가져오기
        LocalDate now = LocalDate.now();
        //형식제작하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMdd");
        //올해 년도 가져오기
        int year = now.getYear();
        //오늘날짜 형식변환
        String stdate = now.format(formatter);
        //올해 말로 설정
        String eddate = year+"1231";
        //page설정 1부터 시작
        int cpage =1;
        //한번에 10개 가져오기
        String rows = "10";
        //가져오는 메소드
        getDayList(stdate,eddate,cpage,rows);

    }

    public List<concertDTO> getDayList(String stdate,String eddate,int cpage ,String rows){
        List<concertDTO> list = new ArrayList<>();

        //반복여부 json 값이 없을 경우 멈추게 설정
        int cycle = 1;

        //반복 시작
        while(cycle==1) {

            String urlStr = "http://www.kopis.or.kr/openApi/restful/pblprfr?service="
                    +secretKey+"&stdate="+stdate+"&eddate="+eddate+"&rows="+rows+"&cpage="+cpage;

            try {
                URL url = new URL(urlStr);
                BufferedReader bf;
                String line = "";
                String result = "";

                //공연정보 받아오기
                bf = new BufferedReader(new InputStreamReader(url.openStream()));

                //버퍼에 있는 정보를 하나의 문자열로 변환
                while ((line = bf.readLine()) != null) {
                    result = result.concat(line);
                }

                //Json parser를 만들어 만들어진 문자열 데이터를 객체화
                JSONParser parser = new JSONParser();

                //XML로 온 데이터를 JSON으로 변환하기
                JSONObject obj = org.json.XML.toJSONObject(result);
                log.info("value check :: "+obj.toString());

                //json 값 확인하기
                if (!obj.isEmpty()) {
                    //JSON안의 요소 찾기
                    JSONObject dbs = obj.getJSONObject("dbs");
                    JSONArray db = dbs.getJSONArray("db");

                    //JSON 배열에서 각각의 내용 가져오기
                    for (int i = 0; i < db.length(); i++) {
                        JSONObject one = db.getJSONObject(i);

                        concertDTO cdto = new concertDTO();
                        //dto에 각각의 정보 넣기
                        cdto.setFcltynm((String) one.get("fcltynm"));
                        cdto.setPrfpdfrom((String) one.get("prfpdfrom"));
                        cdto.setOpenrun((String) one.get("openrun"));
                        cdto.setPrfpdto((String) one.get("prfpdto"));
                        cdto.setMt20id((String) one.get("mt20id"));
                        cdto.setGenrenm((String) one.get("genrenm"));
                        cdto.setPoster((String) one.get("poster"));
                        cdto.setPrfstate((String) one.get("prfstate"));

                        //이제 여기서 db값 비교 및 값넣는 로직 추가하기
                    }
                } else {
                    //만약 값이 비어있나면 반복문 멈추게 설정
                    cycle = 0;
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            cpage++;

        }
        return list;


    }

}
