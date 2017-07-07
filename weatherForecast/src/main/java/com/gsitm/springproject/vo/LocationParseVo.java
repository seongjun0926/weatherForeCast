package com.gsitm.springproject.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;



/**
 * 
* @program     : LocationParseVo.java
* @since       : 2017. 7. 3.
* @author      : 손성준
* @version     : 1.0
* @description : 위치정보와 대기 정보를 담기 위한 Vo
 */
@Data
@Entity
@Table(name="LOCATIONPARSE")
public class LocationParseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5325604351045391372L;

	@Id
	@Column(name="MSRDATE")
	private String MSRDATE;

	@Column(name="MSRADMCODE")
	private String MSRADMCODE;
	
	@Column(name="MSRSTENAME")
	private String MSRSTENAME;
	
	@Column(name="MAXINDEX")
	private String MAXINDEX;
	
	@Column(name="GRADE")
	private String GRADE;
	
	@Column(name="POLLUTANT")
	private String POLLUTANT;
	
	@Column(name="NITROGEN")
	private String NITROGEN;
	
	@Column(name="OZONE")
	private String OZONE;
	
	@Column(name="CARBON")
	private String CARBON;
	
	@Column(name="SULFUROUS")
	private String SULFUROUS;
	
	@Column(name="PM10")
	private String PM10;
	
	@Column(name="PM25")
	private String PM25;
}
