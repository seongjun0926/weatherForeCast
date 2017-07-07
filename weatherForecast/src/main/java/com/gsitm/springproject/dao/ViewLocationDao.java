package com.gsitm.springproject.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gsitm.springproject.mapper.ViewLocationMapper;
import com.gsitm.springproject.vo2.ViewLocationVo;

/**
 * 
* @program     : ViewLocationDao.java
* @since       : 2017. 7. 5.
* @author      : 손성준
* @version     : 1.0
* @description : 지도에 뿌려줄 Vo 정보 Dao
*
 */
@Repository(value = "ViewLocationDao")
public class ViewLocationDao{
	private static final Logger logger = LoggerFactory.getLogger(ViewLocationDao.class);

	@Autowired
	@Qualifier("sqlSession2")
	protected SqlSession sqlSession2;
	
	/**
	 * 
	* @name        : readViewLocation
	* @since       : 2017. 7. 5.
	* @author      : 손성준
	* @return List<ViewLocationVo>
	* @description : 지도에 뿌려줄 Vo정보 리스트 가져옴
	 */
	public List<ViewLocationVo> readViewLocation() {
		List<ViewLocationVo> list = null;
		try{			
			ViewLocationMapper  viewLocationMapper = sqlSession2.getMapper(ViewLocationMapper.class);
			list = viewLocationMapper.readViewLocation();
		}
		catch(Exception e){
			logger.debug(" [ERROR] "+e);
		}		
		return list;
	}	
	
	/**
	 * 
	* @name        : readViewLocationType
	* @since       : 2017. 7. 6.
	* @author      : 손성준
	* @param type
	* @return ViewLocationVo
	* @description : 해당 구만 가져오기
	 */
	public ViewLocationVo readViewLocationType(String type) {
		ViewLocationVo result = null;
		try{			
			ViewLocationMapper  viewLocationMapper = sqlSession2.getMapper(ViewLocationMapper.class);
			result = viewLocationMapper.readViewLocationType(type);
		}
		catch(Exception e){
			logger.debug(" [ERROR] "+e);
		}		
		return result;
	}	
	
}
