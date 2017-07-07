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
			name="getTemperature",
			query="from TemperatureVo where id = :id"
			)
})
/**
 * 
* @program     : TemperatureVo.java
* @since       : 2017. 7. 5.
* @author      : 손성준
* @version     : 1.0
* @description : 온도 정보 Vo
*
 */
@Data
@Entity
@Table(name="Temperature")
public class TemperatureVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4791042951542063214L;

/*	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Temperature_SEQ")
	@SequenceGenerator(name="Temperature_SEQ", sequenceName="Temperature_SEQ")*/
	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="MIN")
	private int min;
	@Column(name="MAX")
	private int max;
}
