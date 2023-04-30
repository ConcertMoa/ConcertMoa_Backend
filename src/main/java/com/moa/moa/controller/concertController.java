package com.moa.moa.controller;

import com.moa.moa.dto.detailDTO;
import com.moa.moa.service.concertService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/concert")
public class concertController {

    @Autowired
    concertService  concertservice;

    @GetMapping(value="/dateInfo")
        public String dateInfo
            (@RequestParam String stdate, @RequestParam String edate,
             @RequestParam String row,@RequestParam String cpage ){
                String result = "";

                result = concertservice.dateInfo(stdate,edate,row,cpage);

                return result;
        }
    @GetMapping(value="/detailInfo")
        public detailDTO detailInfo(@RequestParam String concertid){
        detailDTO dinfo = new detailDTO();

        dinfo = concertservice.detailInfo(concertid);

        return dinfo;
    }

}
