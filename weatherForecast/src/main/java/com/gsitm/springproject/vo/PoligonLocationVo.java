package com.gsitm.springproject.vo;

import java.util.List;

import com.gsitm.springproject.vo2.ViewLocationVo;

import lombok.Data;

/**
 * 
* @program     : PoligonLocationVo.java
* @since       : 2017. 7. 6.
* @author      : 손성준
* @version     : 1.0
* @description : poligon 정보를 담기위한 변수
*
 */
@Data
public class PoligonLocationVo {

	private String name;
	
	private ViewLocationVo vlVo;
	
	private List<LntLngVo> llVo;

	@Override
	public String toString() {
		return "PoligonLocationVo [name=" + name + ", vlVo=" + vlVo + ", llVo=" + llVo + "]";
	}
}
