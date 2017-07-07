package com.gsitm.springproject.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gsitm.springproject.mapper.MaxMinMapper;
import com.gsitm.springproject.vo2.MaxMinVo;

/**
 * 
* @program     : MaxMinDAO.java
* @since       : 2017. 7. 5.
* @author      : 손성준
* @version     : 1.0
* @description : Max, Min 정보 읽어오기 위한 Dao
*
 */
@Repository(value = "MaxMinDAO")
public class MaxMinDAO{
	private static final Logger logger = LoggerFactory.getLogger(MaxMinDAO.class);

	@Autowired
	@Qualifier("sqlSession2")
	protected SqlSession sqlSession2;
	
	/**
	 * 
	* @name        : readType
	* @since       : 2017. 7. 5.
	* @author      : 손성준
	* @param Type
	* @return List<MaxMinVo>
	* @description : Max, Min 정보 가져옴
	 */
	public List<MaxMinVo> readType(String Type) {
		List<MaxMinVo> list = null;
		try{			
			MaxMinMapper maxMinMapper = sqlSession2.getMapper(MaxMinMapper.class);
			list = maxMinMapper.readMaxMin(Type);
		}
		catch(Exception e){
			logger.debug(" [ERROR] "+e);
		}		
		return list;
	}	
}
