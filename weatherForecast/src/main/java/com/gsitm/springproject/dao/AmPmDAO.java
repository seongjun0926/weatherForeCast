package com.gsitm.springproject.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gsitm.springproject.mapper.AmPmMapper;
import com.gsitm.springproject.vo2.AmPmVo;

/**
 * 
* @program     : AmPmDAO.java
* @since       : 2017. 7. 5.
* @author      : 손성준
* @version     : 1.0
* @description : Am ,Pm 정보 가져오기
*
 */
@Repository(value = "AmPmDAO")
public class AmPmDAO{
	private static final Logger logger = LoggerFactory.getLogger(AmPmDAO.class);

	@Autowired
	@Qualifier("sqlSession2")
	protected SqlSession sqlSession2;
	
	/**
	 * 
	* @name        : readType
	* @since       : 2017. 7. 5.
	* @author      : 손성준
	* @param Type
	* @return List<AmPmVo>
	* @description : type에 따라서 Am,Pm정보 읽어옴
	 */
	public List<AmPmVo> readType(String Type) {
		List<AmPmVo> list = null;
		try{			
			AmPmMapper amPmMapper = sqlSession2.getMapper(AmPmMapper.class);
			list = amPmMapper.readAmPm(Type);
		}
		catch(Exception e){
			logger.debug(" [ERROR] "+e);
		}		
		return list;
	}	
}
