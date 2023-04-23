package com.moa.moa.service;

import com.moa.moa.dto.concertDTO;
import com.moa.moa.dto.detailDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


import static org.apache.logging.log4j.message.MapMessage.MapFormat.XML;

@Service
public class concertServiceImpl implements concertService{

    static private String secretKey = "f0809716ad6b4f6c8ee33c97714b343a";
    @Override
    public String dateInfo(String stdate, String edate, String row,String cpage) {

        String urlStr = "http://www.kopis.or.kr/openApi/restful/pblprfr?service="
                    +secretKey+"&stdate="+stdate+"&eddate="+edate+"&rows="+row+"&cpage="+cpage;

        try {
            URL url = new URL(urlStr);
            BufferedReader bf;
            String line ="";
            String result = "";

            //공연정보 받아오기
            bf = new BufferedReader(new InputStreamReader(url.openStream()));

            //버퍼에 있는 정보를 하나의 문자열로 변환
            while((line=bf.readLine())!= null){
                result = result.concat(line);
            }

            //Json parser를 만들어 만들어진 문자열 데이터를 객체화
            JSONParser parser = new JSONParser();

            //XML로 온 데이터를 JSON으로 변환하기
            JSONObject obj = org.json.XML.toJSONObject(result);
            System.out.println(obj.toString());

            //JSON안의 요소 찾기
            JSONObject dbs = obj.getJSONObject("dbs");
            JSONArray db = dbs.getJSONArray("db");

            //JSON 배열에서 각각의 내용 가져오기
            for(int i=0 ; i<db.length();i++){
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
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public detailDTO detailInfo(String concertid) {

        detailDTO ddto = new detailDTO();

        String urlStr = "http://www.kopis.or.kr/openApi/restful/pblprfr/"+concertid+"?service="
                +secretKey;

        try {
            URL url = new URL(urlStr);
            BufferedReader bf;
            String line ="";
            String result = "";

            //공연정보 받아오기
            bf = new BufferedReader(new InputStreamReader(url.openStream()));

            //버퍼에 있는 정보를 하나의 문자열로 변환
            while((line=bf.readLine())!= null){
                result = result.concat(line);
            }

            //Json parser를 만들어 만들어진 문자열 데이터를 객체화
            JSONParser parser = new JSONParser();

            //XML로 온 데이터를 JSON으로 변환하기
            JSONObject obj = org.json.XML.toJSONObject(result);
            System.out.println(obj.toString());

            //JSON안의 요소 찾기
            JSONObject dbs = obj.getJSONObject("dbs");
            JSONObject db = dbs.getJSONObject("db");

            //dto에 각각의 정보 넣기
            ddto.setMt20id((String) db.get("mt20id"));
            ddto.setMt10id((String) db.get("mt10id"));
            ddto.setPrfnm((String) db.get("prfnm"));
            ddto.setPrfpdfrom((String) db.get("prfpdfrom"));
            ddto.setPrfpdto((String) db.get("prfpdto"));
            ddto.setFcltynm((String) db.get("fcltynm"));
            ddto.setPrfcast((String) db.get("prfcast"));
            ddto.setPrfcrew((String) db.get("prfcrew"));
            ddto.setPrfruntime((String) db.get("prfruntime"));
            ddto.setPrfage((String) db.get("prfage"));
            ddto.setEntrpsnm((String) db.get("entrpsnm"));
            ddto.setPcseguidance((String) db.get("pcseguidance"));
            ddto.setPoster((String) db.get("poster"));
            ddto.setSty((String) db.get("sty"));
            ddto.setGenrenm((String) db.get("genrenm"));
            ddto.setPrfstate((String) db.get("prfstate"));
            ddto.setOpenrun((String) db.get("openrun"));
            ddto.setDtguidance((String) db.get("dtguidance"));

            JSONObject img = db.getJSONObject("styurls");
            System.out.println(img.toString());

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return ddto;
    }
}
