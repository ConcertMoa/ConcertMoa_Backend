package com.moa.moa.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Getter@Setter
public class concertDTO {
    String prfpdfrom; //공연시작일
    String fcltynm; //공연시설명
    String openrun; //오픈런
    String prfpdto; //공연종료일
    String mt20id; //공연 ID
    String genrenm; //공연장르명
    String poster; //공연 포스터 경로
    String prfstate; //공연상태

}
