package com.gsitm.springproject.service;

import java.io.StringReader;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.gsitm.springproject.dao.WeatherDetailDao;
import com.gsitm.springproject.vo.WeatherDetailVo;

/**
 * 
* @program     : WeatherService.java
* @since       : 2017. 7. 3.
* @author      : 손성준
* @version     : 1.0
* @description : 중기육상정보 조회,삭제,삽입.
*
 */
@Service(value = "WeatherDetailService")
public class WeatherDetailService {
	private static final String SERVICE_KEY="ASq1%2F36lbrKJna9AoGZ41NUpqnDzzG72elOjAfoxthlGoGiGMLELyRLvsUCHMYwYp3Ihtb4NMW%2FOkznG97W3lA%3D%3D&";
	private static final Logger logger = LoggerFactory.getLogger(WeatherDetailService.class);
	
	@Resource(name = "WeatherDetailDao")
	private WeatherDetailDao weatherDetailDao;

	/**
	 * 
	* @name        : readList
	* @since       : 2017. 7. 3.
	* @author      : 손성준
	* @param 
	* @return WeatherDetailVo
	* @throws Exception
	* @description : 현재 DB에 저장되어있은 Weather정보를 가져옴
	 */
	public WeatherDetailVo readWeatherDetail() throws Exception{

		WeatherDetailVo resultList = weatherDetailDao.getWeatherDeatil(1);
		return resultList;
	}	
	/**
	 * 
	* @name        : parsingToJsonData
	* @since       : 2017. 7. 3.
	* @author      : 손성준
	* @throws Exception
	* @description : URL을 통해 값을 가져와서 Weather 정보를 DB에 저장하기 위한 함수
	 */
	public void parsingToJsonData() throws Exception{
        RestTemplate restTp = new RestTemplate(); // xml을 받아오기위해 api에 요청을 하는 놈
        restTp.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        
        String time=parseTime();
        URI uri = UriComponentsBuilder.fromUriString(URLDecoder.decode(
        "http://newsky2.kma.go.kr/service/MiddleFrcstInfoService/getMiddleForecast?"+
        "ServiceKey="+SERVICE_KEY+
        "stnId=109&"+
        "tmFc="+time+"&"+
        "numOfRows=6&"+
        "pageNo=1","UTF-8")).build().encode().toUri();
        logger.debug("uri={}",uri);
//        logger.info("URI="+uri.toString());http://biz.epost.go.kr/openapi/test_view_new_post_xml.jsp????
        //Get방식으로 할때 GetForObject를 함
        String resStr = restTp.getForObject(uri, String.class);  
        parseXmlStr(resStr);
	}
	
	/**
	 * 
	* @name        : parseXmlStr
	* @since       : 2017. 7. 3.
	* @author      : 손성준
	* @param xmlStr
	* @description : xml파싱을 해서 item 갯수마다 insert 하기
	 */
	public void parseXmlStr(String xmlStr){
        StringReader sr = new StringReader(xmlStr);
        InputSource is = new InputSource(sr);
        NodeList result = null;
        try {
        	//xml스트링을 다 받음
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            //List
            //노드로 하나씩 따옴insertBuilding
            Node node = doc.getDocumentElement();	//Node node = doc.getElementsByTagName("List");
            //body/items/item
            result=node.getLastChild().getFirstChild().getChildNodes();
            
            for (int i=0;i<result.getLength();i++) {
            	//item
                Node tmpNode = result.item(i);

                if (tmpNode.getNodeName().equals("item")) {

                    NodeList nodeList = tmpNode.getChildNodes();
                    weatherDetailDao.truncate();
                    WeatherDetailVo weatherDetailVo = new WeatherDetailVo();
                    weatherDetailVo.setId(1);
                    weatherDetailVo.setDetail(nodeList.item(0).getTextContent());
                	weatherDetailDao.insertWeatherDetail(weatherDetailVo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
	/**
	 * 
	* @name        : parseTime
	* @since       : 2017. 7. 4.
	* @author      : 손성준
	* @return      : String
	* @description : 파싱되는 데이터가 0600, 1800 이 둘이 있는데 현재시간에 따라서 0600, 1800 으로 나눠줌
	 */
	public String parseTime(){
		
		String resultTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		LocalDateTime a = LocalDateTime.now();
		LocalDateTime b = LocalDateTime.now().withHour(6).withMinute(0);
		//201707041144 > 2017040600
		if(a.isAfter(b)){
			resultTime+="0600";
		}else{
		//201707041144 > 2017040600
			resultTime+="1800";
		}
		return resultTime;
	}
}