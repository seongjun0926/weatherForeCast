package com.gsitm.springproject.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gsitm.springproject.service.AmPmService;
import com.gsitm.springproject.service.LocationParseService;
import com.gsitm.springproject.service.MaxMinService;
import com.gsitm.springproject.service.TemperatureService;
import com.gsitm.springproject.service.ViewLocationService;
import com.gsitm.springproject.service.WeatherDetailService;
import com.gsitm.springproject.service.WeatherImageService;
import com.gsitm.springproject.service.WeatherService;
import com.gsitm.springproject.vo.TemperatureVo;
import com.gsitm.springproject.vo.WeatherDetailVo;
import com.gsitm.springproject.vo.WeatherImageVo;
import com.gsitm.springproject.vo.WeatherVo;
import com.gsitm.springproject.vo2.ViewLocationVo;

@Controller
public class ServiceController {

	@Resource
	private WeatherService weatherService;
	
	@Resource
	private TemperatureService temperatureService;
	
	@Resource
	private WeatherDetailService weatherDetailService;
	
	@Resource
	private AmPmService amPmService;
	
	@Resource
	private MaxMinService maxMinService;
	
	@Resource
	private LocationParseService locationParseService;
	
	@Resource
	private WeatherImageService weatherImageService;
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);
	
	/**
	 * 
	* @name        : weather
	* @since       : 2017. 7. 3.
	* @author      : 손성준
	* @param request
	* @param response
	* @param param
	* @param model
	* @return String
	* @throws Exception
	* @description : /index.do 로 들어오게 되면 중기육상조회, 중기 기온조회, 기상전망조회 데이터들을 DB에 저장
	 */
	@RequestMapping(value="/index.do", method = RequestMethod.GET)
	public String weather(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> param, Model model) throws Exception {
		
		
		model.addAttribute("poligonList",locationParseService.parseJsonParse("https://raw.githubusercontent.com/ssm-lim/bPolygon/master/bPolygon/highmap/json/11.json"));
		model.addAttribute("weatherList", weatherService.readList());                             /*날씨 정보 가져옴*/
		model.addAttribute("temperatureList", temperatureService.readList());                     /*기온 정보 가져옴*/
		model.addAttribute("weatherDetail", weatherDetailService.readWeatherDetail());            /*날씨 상세 정보 가져옴*/
		
		model.addAttribute("am", amPmService.readMaxMin("AM"));                                   /*통계치를 낼 오전 날씨정보*/
		model.addAttribute("pm", amPmService.readMaxMin("PM"));                                   /*통계치를 낼 오후 날씨정보*/
		model.addAttribute("max", maxMinService.readMaxMin("MAX"));                               /*통계치를 낼 최고 기온정보*/
		model.addAttribute("min", maxMinService.readMaxMin("MIN"));                               /*통계치를 낼 최저 기온정보*/
		model.addAttribute("weatherImageListType0", weatherImageService.readWeatherImage(0));     /*화면에 뿌려줄 사진 정보*/
		model.addAttribute("weatherImageListType1", weatherImageService.readWeatherImage(1));     /*화면에 뿌려줄 사진 정보*/
		model.addAttribute("weatherImageListType2", weatherImageService.readWeatherImage(2));     /*화면에 뿌려줄 사진 정보*/
		model.addAttribute("weatherImageListType3", weatherImageService.readWeatherImage(3));     /*화면에 뿌려줄 사진 정보*/
		model.addAttribute("weatherImageListType4", weatherImageService.readWeatherImage(4));     /*화면에 뿌려줄 사진 정보*/
		model.addAttribute("weatherImageListType5", weatherImageService.readWeatherImage(5));     /*화면에 뿌려줄 사진 정보*/
	
		return "index"; 
	}
	
	/**
	 * 
	* @name        : login
	* @since       : 2017. 7. 3.
	* @author      : 손성준
	* @param request
	* @param response
	* @param param
	* @param model
	* @return String
	* @throws Exception
	* @description : login Service
	 */
	@RequestMapping(value="/login.do", method = RequestMethod.GET)
	public String login() throws Exception {
		
		return "login"; 
	}
}
