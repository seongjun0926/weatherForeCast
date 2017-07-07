package com.gsitm.springproject.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.gsitm.springproject.dao.AmPmDAO;
import com.gsitm.springproject.vo2.AmPmVo;

/**
 * 
* @program     : AmPmService.java
* @since       : 2017. 7. 5.
* @author      : 손성준
* @version     : 1.0
* @description : Am, Pm 의 값 가져오기
*
 */
@Service(value = "AmPmService")
public class AmPmService {
	
	@Resource(name = "AmPmDAO")
	private AmPmDAO amPmDAO;
	
	/**
	 * 
	* @name        : readMaxMin
	* @since       : 2017. 7. 5.
	* @author      : 손성준
	* @param Type
	* @return List<AmPmVo>
	* @throws Exception
	* @description : Type에 따라서 Am,Pm의 값들 가져오기
	 */
	public List<AmPmVo> readMaxMin(String Type) throws Exception{
		List<AmPmVo> resultList = amPmDAO.readType(Type);
		return resultList;
	}	

}