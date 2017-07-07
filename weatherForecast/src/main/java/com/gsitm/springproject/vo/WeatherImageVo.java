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
			name="getWeatherImage",
			query="from WeatherImageVo where type = :type"
			)
})
/**
 * 
* @program     : WeatherImageVo.java
* @since       : 2017. 7. 3.
* @author      : 손성준
* @version     : 1.0
* @description : 위성사진
 */
@Data
@Entity
@Table(name="WeatherImage")
public class WeatherImageVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 360133785470101009L;
	/*@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WEATHER_SEQ")
	@SequenceGenerator(name="WEATHER_SEQ", sequenceName="WEATHER_SEQ")*/
	@Id
	@Column(name="ID")
	private int id;
	@Column(name="ImgLocation")
	private String imgLocation;
	@Column(name="TYPE")
	private int type;
}
