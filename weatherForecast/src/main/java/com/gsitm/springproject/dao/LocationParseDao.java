package com.gsitm.springproject.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gsitm.springproject.vo.LocationParseVo;

/**
 * 
 * @program : LocationParseDao.java
 * @since : 2017. 7. 3.
 * @author : 손성준
 * @version : 1.0
 * @description : 지도에 뿌려줄 정보 Parse
 *
 */
@Repository(value = "LocationParseDao")
public class LocationParseDao {
	private static final Logger logger = LoggerFactory.getLogger(LocationParseDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * @name : insertLocationParse
	 * @since : 2017. 7. 3.
	 * @author : 손성준
	 * @param weatherVO
	 * @description : LocationParse 값 DB에 저장
	 */
	@SuppressWarnings("unchecked")
	public void insertLocationParse(LocationParseVo locationParseVo) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.save(locationParseVo);
		session.getTransaction().commit();
		session.close();
	}
	/**
	 * 
	 * @name : truncate
	 * @since : 2017. 7. 3.
	 * @author : 손성준
	 * @param 
	 * @description : 삽입하기 전에 그 전에 있던 행들을 삭제
	 */
	@SuppressWarnings("unchecked")
	public void truncate() {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.createQuery("DELETE FROM LocationParseVo").executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
}
