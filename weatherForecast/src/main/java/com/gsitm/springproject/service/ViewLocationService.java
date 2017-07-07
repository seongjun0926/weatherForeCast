package com.gsitm.springproject.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.gsitm.springproject.dao.ViewLocationDao;
import com.gsitm.springproject.vo2.ViewLocationVo;

/**
 * 
* @program     : ViewLocationService.java
* @since       : 2017. 7. 5.
* @author      : 손성준
* @version     : 1.0
* @description : 지도에 뿌려줄 Vo 정보 가져오기
*
 */
@Service(value = "ViewLocationService")
public class ViewLocationService {
	
	@Resource(name = "ViewLocationDao")
	private ViewLocationDao viewLocationDao;
	
	/**
	 * 
	* @name        : readViewLocation
	* @since       : 2017. 7. 5.
	* @author      : 손성준
	* @return List<ViewLocationVo>
	* @throws Exception
	* @description : 뷰 정보 가져오기
	 */
	public List<ViewLocationVo> readViewLocation() throws Exception{
		List<ViewLocationVo> resultList = viewLocationDao.readViewLocation();
		return resultList;
	}	

}