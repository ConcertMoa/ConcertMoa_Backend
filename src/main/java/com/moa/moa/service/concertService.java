package com.moa.moa.service;

import com.moa.moa.dto.detailDTO;
import org.springframework.stereotype.Service;

public interface concertService {
    String dateInfo(String stdate, String edate, String row, String cpage);

    detailDTO detailInfo(String concertid);
}
