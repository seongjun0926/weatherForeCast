package com.gsitm.springproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gsitm.springproject.vo.WeatherImageVo;

/**
 * 
 * @program : WeatherImageDao.java
 * @since : 2017. 7. 3.
 * @author : 손성준
 * @version : 1.0
 * @description : 위성사진 Dao
 *
 */
@Repository(value = "WeatherImageDao")
public class WeatherImageDao {
	private static final Logger logger = LoggerFactory.getLogger(WeatherImageDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * @name : insertWeatherImage
	 * @since : 2017. 7. 3.
	 * @author : 손성준
	 * @param weatherImageVo
	 * @description : weatherImage 값 DB에 저장
	 */
	@SuppressWarnings("unchecked")
	public void insertWeatherImage(WeatherImageVo weatherImageVo) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.save(weatherImageVo);
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
		session.createQuery("DELETE FROM WeatherImageVo").executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * 
	 * @name : readWeatherImage
	 * @since : 2017. 7. 3.
	 * @author : 손성준
	 * @return : List<WeatherImageVo>
	 * @description : image 값 select
	 */
	@SuppressWarnings("unchecked")
	public List<WeatherImageVo> readWeatherImage(int type) {
		Session session = this.sessionFactory.openSession();
		List<WeatherImageVo> resultList = session.getNamedQuery("getWeatherImage").setParameter("type", type).list();
		session.close();
		return resultList;
	}
}
