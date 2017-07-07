package com.gsitm.springproject.vo;

import lombok.Data;

/**
 * 
* @program     : LntLngVo.java
* @since       : 2017. 7. 6.
* @author      : 손성준
* @version     : 1.0
* @description : 파싱한 Lng, Lnt 정보 담기
*
 */
@Data
public class LntLngVo {

	private float lng;
	private float lnt;
	@Override
	public String toString() {
		return "LntLngVo [lng=" + lng + ", lnt=" + lnt + "]";
	}
}
