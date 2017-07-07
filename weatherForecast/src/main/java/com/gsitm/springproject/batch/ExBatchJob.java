package com.gsitm.springproject.batch;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gsitm.springproject.service.LocationParseService;
import com.gsitm.springproject.service.TemperatureService;
import com.gsitm.springproject.service.WeatherDetailService;
import com.gsitm.springproject.service.WeatherImageService;
import com.gsitm.springproject.service.WeatherService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ExBatchJob {
	
	@Resource
	private WeatherService weatherService;
	
	@Resource
	private TemperatureService temperatureService;
	
	@Resource
	private WeatherDetailService weatherDetailService;
	
	@Resource
	private LocationParseService locationParseService;
	
	@Resource
	private WeatherImageService weatherImageService;
	
	private static final Logger logger = LoggerFactory.getLogger(ExBatchJob.class);
	public void doBatch() throws Exception {
		logger.debug("배치시작한다");
		
		weatherService.parsingToJsonData();                           /*날씨 정보 파싱*/
		temperatureService.parsingToJsonData();                       /*기온 정보 파싱*/
		weatherDetailService.parsingToJsonData();                     /*날씨 상세 정보 파싱*/
		locationParseService.parsingToJsonData();                     /*서울 구별로 대기 정보 파싱*/
		weatherImageService.truncate();                               /*위성 사진 정보 날림*/
        String[] typeValue = {"ir1","vis","wv","swir","com","eir"};   /*위성 사진 정보 파싱*/
        for(int i=0;i<typeValue.length;i++){
    		weatherImageService.parsingToJsonData(typeValue[i],i);

        }
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ExBatchJob proc = new ExBatchJob();
		proc.doBatch();
	}
}
