package com.moa.moa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class detailDTO {
    String mt20id; // 공연상세정보
    String mt10id; //공연시설 ID
    String prfnm; //공연명
    String prfpdfrom; //공연시작일
    String prfpdto; //공연종료일
    String fcltynm; //공연시설명
    String prfcast; //공연출연진
    String prfcrew; //공연제작진
    String prfruntime; //공연 런타임
    String prfage; //공연 관람 연령
    String entrpsnm; //제작사
    String pcseguidance; //티켓가격
    String poster; //포스터
    String sty; //줄거리
    String genrenm; //공연장르
    String prfstate; //공연상태
    String openrun; //오픈런
    String styurl; //소개이미지
    String dtguidance; //공연시간

}
