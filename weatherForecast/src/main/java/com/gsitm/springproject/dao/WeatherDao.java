package com.gsitm.springproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gsitm.springproject.vo.WeatherVo;

/**
 * 
 * @program : WeatherDao.java
 * @since : 2017. 7. 3.
 * @author : 손성준
 * @version : 1.0
 * @description : 중기육상정보 조회,삭제,삽입. 삽입시 truncate를 사용하여 날리고 나서 삽입해야함. API에서도 현재 이후
 *              과거의 날씨를 보여주지 않으며, 예보이기 때문에 그 전 데이터는 필요없다고 판단
 *
 */
@Repository(value = "WeatherDao")
public class WeatherDao {
	private static final Logger logger = LoggerFactory.getLogger(WeatherDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * @name : insertWether
	 * @since : 2017. 7. 3.
	 * @author : 손성준
	 * @param weatherVO
	 * @description : weather 값 DB에 저장
	 */
	@SuppressWarnings("unchecked")
	public void insertWeather(WeatherVo weatherVO) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.save(weatherVO);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * 
	* @name        : getWeather
	* @since       : 2017. 7. 4.
	* @author      : 손성준
	* @param       :index
	* @return      :WeatherVo
	* @description :선택된 WeatherVo 값 가져오기
	 */
	public WeatherVo getWeather(int index) {
		Session session = this.sessionFactory.openSession();
		WeatherVo weatherVo = (WeatherVo) session.getNamedQuery("getWeather").setParameter("id", index).uniqueResult();
		session.close();
		return weatherVo;
	}

	/**
	 * 
	 * @name : truncate
	 * @since : 2017. 7. 3.
	 * @author : 손성준
	 * @param weatherVO
	 * @description : 삽입하기 전에 그 전에 있던 행들을 삭제
	 */
	@SuppressWarnings("unchecked")
	public void truncate() {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.createQuery("DELETE FROM WeatherVo").executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * 
	 * @name : readWeather
	 * @since : 2017. 7. 3.
	 * @author : 손성준
	 * @return : List<WeatherVo>
	 * @description : weather 값 select
	 */
	@SuppressWarnings("unchecked")
	public List<WeatherVo> readWeather() {
		Session session = this.sessionFactory.openSession();
		List<WeatherVo> resultList = session.createQuery("from WeatherVo order by id").list();
		session.close();
		return resultList;
	}
}
