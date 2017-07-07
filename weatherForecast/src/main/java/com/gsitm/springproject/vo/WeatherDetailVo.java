package com.gsitm.springproject.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
@NamedQueries({
	@NamedQuery(
			name="getWeatherDetail",
			query="from WeatherDetailVo where id = :id"
			)
})
/**
 * 
* @program     : WeatherDetailVo.java
* @since       : 2017. 7. 5.
* @author      : 손성준
* @version     : 1.0
* @description : 기상 정보 Vo
*
 */
@Data
@Entity
@Table(name="WeatherDetail")
public class WeatherDetailVo implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3473700982945838536L;
/*	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WeatherDetail_SEQ")
	@SequenceGenerator(name="WeatherDetail_SEQ", sequenceName="WeatherDetail_SEQ")*/
	@Id
	@Column(name="id")
	private int id;
	@Column(name="DETAIL")
	private String detail;
}
