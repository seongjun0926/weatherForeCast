package com.gsitm.springproject.vo2;

import lombok.Data;

/**
 * 
* @program     : MaxMinVo.java
* @since       : 2017. 7. 4.
* @author      : 손성준
* @version     : 1.0
* @description : 하이버네이트로 크리테리아로 해야하는데 할줄 몰라서 mapping하기 위해 쓰는 VO
*
 */
@Data
public class MaxMinVo {
	private String temperatureType;
	private String temperatureCount;
}
