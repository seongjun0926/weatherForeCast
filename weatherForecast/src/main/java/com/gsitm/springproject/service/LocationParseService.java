package com.gsitm.springproject.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
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

import com.gsitm.springproject.dao.LocationParseDao;
import com.gsitm.springproject.dao.ViewLocationDao;
import com.gsitm.springproject.vo.LntLngVo;
import com.gsitm.springproject.vo.LocationParseVo;
import com.gsitm.springproject.vo.PoligonLocationVo;
import com.gsitm.springproject.vo2.ViewLocationVo;

/**
 * 
 * @program : LocationParseService.java
 * @since : 2017. 7. 3.
 * @author : 손성준
 * @version : 1.0
 * @description : 서울 구 별로 대기정보 가져오기.
 *
 */
@Service(value = "LocationParseService")
public class LocationParseService {
	private static final String SERVICE_KEY = "6b4749655873656f3130336c46787862";
	private static final Logger logger = LoggerFactory.getLogger(LocationParseService.class);

	@Resource(name = "LocationParseDao")
	private LocationParseDao locationParseDao;
	
	@Resource(name = "ViewLocationDao")
	private ViewLocationDao viewLocationDao;
	
	
	/**
	 * 
	 * @name : parsingToJsonData
	 * @since : 2017. 7. 3.
	 * @author : 손성준
	 * @throws Exception
	 * @description : URL을 통해 값을 가져와서 LocationParseVo 정보를 DB에 저장하기 위한 함수
	 */
	public void parsingToJsonData() throws Exception {
		RestTemplate restTp = new RestTemplate(); // xml을 받아오기위해 api에 요청을 하는 놈
		restTp.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

		URI uri = UriComponentsBuilder.fromUriString(URLDecoder.decode(
				"http://openapi.seoul.go.kr:8088/" + SERVICE_KEY + "/xml/ListAirQualityByDistrictService/1/100/",
				"UTF-8")).build().encode().toUri();
		logger.debug("uri={}", uri);
		// logger.info("URI="+uri.toString());http://biz.epost.go.kr/openapi/test_view_new_post_xml.jsp????
		// Get방식으로 할때 GetForObject를 함
		String resStr = restTp.getForObject(uri, String.class);
		parseXmlStr(resStr);
	}

	/**
	 * 
	 * @name : parseXmlStr
	 * @since : 2017. 7. 3.
	 * @author : 손성준
	 * @param xmlStr
	 * @description : xml파싱을 해서 item 갯수마다 insert 하기
	 */
	public void parseXmlStr(String xmlStr) {
		StringReader sr = new StringReader(xmlStr);
		InputSource is = new InputSource(sr);
		NodeList result = null;
		try {
			// xml스트링을 다 받음
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			// List
			// 노드로 하나씩 따옴insertBuilding
			Node node = doc.getDocumentElement(); // Node node =
													// doc.getElementsByTagName("List");
			// body/items/item
			result = node.getChildNodes();
			locationParseDao.truncate();
			for (int i = 0; i < result.getLength(); i++) {
				// item
				Node tmpNode = result.item(i);
				if (tmpNode.getNodeName().equals("row")) {

					NodeList nodeList = tmpNode.getChildNodes();
					LocationParseVo locationParseVo = new LocationParseVo();

					locationParseVo.setMSRDATE(nodeList.item(1).getTextContent());
					locationParseVo.setMSRADMCODE(nodeList.item(3).getTextContent());
					locationParseVo.setMSRSTENAME(nodeList.item(5).getTextContent());
					locationParseVo.setMAXINDEX(nodeList.item(7).getTextContent());
					locationParseVo.setGRADE(nodeList.item(9).getTextContent());
					locationParseVo.setPOLLUTANT(nodeList.item(11).getTextContent());
					locationParseVo.setNITROGEN(nodeList.item(13).getTextContent());
					locationParseVo.setOZONE(nodeList.item(15).getTextContent());
					locationParseVo.setCARBON(nodeList.item(17).getTextContent());
					locationParseVo.setSULFUROUS(nodeList.item(19).getTextContent());
					locationParseVo.setPM10(nodeList.item(21).getTextContent());
					locationParseVo.setPM25(nodeList.item(23).getTextContent());

					locationParseDao.insertLocationParse(locationParseVo);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	* @name        : parseJsonParse
	* @since       : 2017. 7. 6.
	* @author      : 손성준
	* @param urlStr
	* @return List<PoligonLocationVo>
	* @throws UnsupportedEncodingException
	* @throws IOException
	* @description : poligon을 그려줄 정보 파싱하기
	 */
	public List<PoligonLocationVo> parseJsonParse(String urlStr) throws UnsupportedEncodingException, IOException {
		URL url = new URL(urlStr);

		InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");

		JSONObject object = (JSONObject) JSONValue.parse(isr);


		JSONArray features = (JSONArray) object.get("features");
		
		List<PoligonLocationVo> result = new ArrayList<PoligonLocationVo>();
		for (int i = 0; i < features.size(); i++) {
			PoligonLocationVo poligonLocationVo = new PoligonLocationVo();
			
			JSONObject Firstname = (JSONObject) features.get(i);// 1,2,3,4,5,6,7
			JSONObject Secondname = (JSONObject) Firstname.get("properties");
			String name = Secondname.get("name").toString();
			JSONObject geometry = (JSONObject) Firstname.get("geometry");
			JSONArray coordinates = (JSONArray) geometry.get("coordinates");
			JSONArray coordinatesSecond = (JSONArray) coordinates.get(0);
			
			List<LntLngVo> llVoList = new ArrayList<>();
			
			for(int j=0;j<coordinatesSecond.size();j++){
				LntLngVo llVo= new LntLngVo();
				JSONArray coordinatesThird = (JSONArray)coordinatesSecond.get(j);
				llVo.setLnt(Float.parseFloat(coordinatesThird.get(0).toString()));
				llVo.setLng(Float.parseFloat(coordinatesThird.get(1).toString()));
				llVoList.add(llVo);
			}
			logger.debug("Secondname={}",Secondname);
			poligonLocationVo.setLlVo(llVoList);
			poligonLocationVo.setName(name);
			poligonLocationVo.setVlVo(viewLocationDao.readViewLocationType(name));
			result.add(poligonLocationVo);
		}
		return result;
	}
}