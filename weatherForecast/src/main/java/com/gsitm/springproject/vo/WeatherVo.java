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
			name="getWeather",
			query="from WeatherVo where id = :id"
			)
})
/**
 * 
* @program     : WeatherVo.java
* @since       : 2017. 7. 3.
* @author      : 손성준
* @version     : 1.0
* @description : 중기육상정보 
* id는 weather_seq에 따라서 자동 증가,
* am은 오전
* pm은 오후
 */
@Data
@Entity
@Table(name="WEATHER")
public class WeatherVo implements Serializable {


	private static final long serialVersionUID = 6900596784014468051L;
	
	/*@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WEATHER_SEQ")
	@SequenceGenerator(name="WEATHER_SEQ", sequenceName="WEATHER_SEQ")*/
	@Id
	@Column(name="ID")
	private int id;
	@Column(name="AM")
	private String am;
	@Column(name="PM")
	private String pm;
}
