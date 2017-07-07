package com.gsitm.springproject.mapper;

import java.util.List;
import com.gsitm.springproject.vo2.AmPmVo;
/**
 * 
* @program     : AmPmMapper.java
* @since       : 2017. 7. 4.
* @author      : 손성준
* @version     : 1.0
* @description : Am, Pm 종류, 카운트 값 가져오기
*
 */
public interface AmPmMapper {
	List<AmPmVo> readAmPm(String Type);

}
