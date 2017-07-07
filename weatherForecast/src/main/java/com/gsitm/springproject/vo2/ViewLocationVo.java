package com.gsitm.springproject.vo2;

import lombok.Data;

/**
 * 
* @program     : ViewLocationVo.java
* @since       : 2017. 7. 4.
* @author      : 손성준
* @version     : 1.0
* @description : 다음 지도 API에 뿌리기 위한 Vo
*
 */
@Data
public class ViewLocationVo {
	private String msrsteName;
	private String wido;
	private String geuogdo;
	private String maxIndex;
	private String grade;
	private String pollutant;
	private String nitrogen;
	private String ozone;
	private String carbon;
	private String sulfurous;
	private String pm10;
	private String pm25;
	@Override
	public String toString() {
		return "ViewLocationVo [msrsteName=" + msrsteName + ", wido=" + wido + ", geuogdo=" + geuogdo + ", maxIndex="
				+ maxIndex + ", grade=" + grade + ", pollutant=" + pollutant + ", nitrogen=" + nitrogen + ", ozone="
				+ ozone + ", carbon=" + carbon + ", sulfurous=" + sulfurous + ", pm10=" + pm10 + ", pm25=" + pm25 + "]";
	}
}
