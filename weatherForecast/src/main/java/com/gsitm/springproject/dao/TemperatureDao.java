package com.gsitm.springproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gsitm.springproject.vo.TemperatureVo;

/**
 * 
 * @program : TemperatureDao.java
 * @since : 2017. 7. 3.
 * @author : 손성준
 * @version : 1.0
 * @description : 중기기온정보 조회,삭제,삽입. 삽입시 delete를 사용하여 날리고 나서 삽입해야함. API에서도 현재 이후
 *              과거의 날씨를 보여주지 않으며, 예보이기 때문에 그 전 데이터는 필요없다고 판단
 *
 */
@Repository(value = "TemperatureDao")
public class TemperatureDao {
	private static final Logger logger = LoggerFactory.getLogger(TemperatureDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * @name : insertTemperature
	 * @since : 2017. 7. 3.
	 * @author : 손성준
	 * @param weatherVO
	 * @description : Temperature 값 DB에 저장
	 */
	@SuppressWarnings("unchecked")
	public void insertTemperature(TemperatureVo temperatureVo) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.save(temperatureVo);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * 
	* @name        : getTemperature
	* @since       : 2017. 7. 4.
	* @author      : 손성준
	* @param       :index
	* @return      :TemperatureVo
	* @description :선택된 TemperatureVo 값 가져오기
	 */
	public TemperatureVo getTemperature(int index) {
		Session session = this.sessionFactory.openSession();
		TemperatureVo temperatureVo = (TemperatureVo) session.getNamedQuery("getTemperature").setParameter("id", index).uniqueResult();
		session.close();
		return temperatureVo;
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
		session.createQuery("DELETE FROM TemperatureVo").executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * 
	 * @name : readTemperature
	 * @since : 2017. 7. 3.
	 * @author : 손성준
	 * @return : List<WeatherVo>
	 * @description : Temperature 값 select
	 */
	@SuppressWarnings("unchecked")
	public List<TemperatureVo> readTemperature() {
		Session session = this.sessionFactory.openSession();
		List<TemperatureVo> resultList = session.createQuery("from TemperatureVo order by id").list();
		session.close();
		return resultList;
	}
}
