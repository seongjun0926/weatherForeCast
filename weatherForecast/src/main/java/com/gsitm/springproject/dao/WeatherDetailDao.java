package com.gsitm.springproject.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gsitm.springproject.vo.WeatherDetailVo;

/**
 * 
 * @program : WeatherDetailDao.java
 * @since : 2017. 7. 3.
 * @author : 손성준
 * @version : 1.0
 * @description : 기상전망조회,삭제,삽입. 삽입시 delete를 사용하여 날리고 나서 삽입해야함. API에서도 현재 이후
 *              과거의 날씨를 보여주지 않으며, 예보이기 때문에 그 전 데이터는 필요없다고 판단
 *
 */
@Repository(value = "WeatherDetailDao")
public class WeatherDetailDao {
	private static final Logger logger = LoggerFactory.getLogger(WeatherDetailDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * @name : insertWeatherDetail
	 * @since : 2017. 7. 3.
	 * @author : 손성준
	 * @param weatherDetailVo
	 * @description : weatherDeatil 값 DB에 저장
	 */
	@SuppressWarnings("unchecked")
	public void insertWeatherDetail(WeatherDetailVo weatherDetailVo) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.save(weatherDetailVo);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * 
	* @name        : getWeatherDeatil
	* @since       : 2017. 7. 4.
	* @author      : 손성준
	* @param       :index
	* @return      :WeatherDetailVo
	* @description :선택된 WeatherDetailVo 값 가져오기
	 */
	public WeatherDetailVo getWeatherDeatil(int index) {
		Session session = this.sessionFactory.openSession();
		WeatherDetailVo weatherDetailVo = (WeatherDetailVo) session.getNamedQuery("getWeatherDetail").setParameter("id", index).uniqueResult();
		session.close();
		return weatherDetailVo;
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
		session.createQuery("DELETE FROM WeatherDetailVo").executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
}
