package com.gsitm.springproject.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.gsitm.springproject.dao.MaxMinDAO;
import com.gsitm.springproject.vo2.MaxMinVo;

/**
 * 
* @program     : MaxMinService.java
* @since       : 2017. 7. 5.
* @author      : 손성준
* @version     : 1.0
* @description : Type에 따라서 온도의 Max, Min 값 가져오기
*
 */
@Service(value = "MaxMinService")
public class MaxMinService {
	
	@Resource(name = "MaxMinDAO")
	private MaxMinDAO maxMinDAO;
	
	/**
	 * 
	* @name        : readMaxMin
	* @since       : 2017. 7. 5.
	* @author      : 손성준
	* @param Type
	* @return List<MaxMinVo>
	* @throws Exception
	* @description : Type에 따라서 온도의 Max, Min 값 가져오기
	 */
	public List<MaxMinVo> readMaxMin(String Type) throws Exception{
		List<MaxMinVo> resultList = maxMinDAO.readType(Type);
		return resultList;
	}	

}