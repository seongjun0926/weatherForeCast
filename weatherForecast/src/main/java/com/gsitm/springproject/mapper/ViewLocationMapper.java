package com.gsitm.springproject.mapper;


import java.util.List;

import com.gsitm.springproject.vo2.ViewLocationVo;

/**
 * 
* @program     : ViewLocationMapper.java
* @since       : 2017. 7. 4.
* @author      : 손성준
* @version     : 1.0
* @description : 지도에 뿌려줄 Vo 정보
*
 */
public interface ViewLocationMapper {
	List<ViewLocationVo> readViewLocation();
	ViewLocationVo readViewLocationType(String type);
}
