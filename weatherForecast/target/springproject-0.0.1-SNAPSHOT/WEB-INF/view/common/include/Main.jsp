<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>GS ITM 설문 솔루션</title>

<script>

	$(document).ready(function($) {
		UIUtil.setEllipsis($('.group2 .news ul li .title a'), 36);
		UIUtil.setEllipsis($('.group2 .faq ul li .title a '), 42);

	});
	
	function fn_board_url(seq,menu_code){
		var form = document.getElementById("MainModel");
		var retUrl = document.getElementById("retUrl");
		var m_code = document.getElementById("m_code");
		var g_code = document.getElementById("g_code");
		var board_seq = document.getElementById("board_seq");
		var gubun = document.getElementById("gubun");
		document.getElementById("pageIndex").value="1";
		
		$.each(menuArray,function(idx, obj){
			//alert(idx + " : " + obj);
			
			if(obj.m_code == menu_code){
				board_seq.value = seq;
				if(obj.auth_code=='1'){	
					alert("사용권한이 없습니다.");
					return ;
				}
				if(obj.m_code=="22"){
					m_code.value=obj.m_code;
					g_code.value=obj.g_code;
					gubun.value="NOTICE";
					retUrl.value = "/support/Notice_View";
					form.action = "/support/Notice_Read.do";
					form.submit();
				}else if(obj.m_code=="23"){
					m_code.value=obj.m_code;
					g_code.value=obj.g_code;
					gubun.value="FAQ";
					retUrl.value = "/support/Faq_View";
					form.action = "/support/Faq_Read.do";
					
				}
				form.submit();
				
			}
		});
	}	
	
</script>
</head>
<body>

	<form:form  commandName="MainModel" method="post"  onsubmit="return false;" >

		<form:hidden path="board_seq" />
		<form:hidden path="retUrl" />
		<!-- 페이징 -->
		<form:hidden path="pageIndex"/>
		<form:hidden path="gubun" />
		<form:hidden path="m_code" />
		<form:hidden path="g_code" />
		
		<!-- 		<a href="j_spring_security_logout.do">로그아웃</a><br /> -->
		<div class="group1">
			<div class="login_warp">
				<div class="inner">
					<div class="title">
						<img src="/images/main/app_title.gif" alt="나의 업무내역" />
					</div>
					<div class="logout">
					    <a href="j_spring_security_logout.do"><img src="/images/main/btn_logout.png" alt="로그아웃"/></a>
					</div>
					<div class="app_box">
						<div class="app">
							<div class="member">
								${MainModel.session_user_name}(${MainModel.session_auth_name})님
							</div>
							<div class="sumlist">
								<c:choose>
									<c:when test="${LeftCntData.left_gb == '1'}">
										<dl>
											<dt>작성중인 설문  :  </dt>
											<dd>
												<a href="javascript:fn_url('21')"><em>${LeftCntData.left_cnt1}</em></a> 건
											</dd>
										</dl>
										<dl>
											<dt>작성완료된 설문  :  </dt>
											<dd>
												<a href="javascript:fn_url('21')"><em>${LeftCntData.left_cnt2}</em></a> 건
											</dd>
										</dl>
										<dl>
											<dt>진행중인 설문  :  </dt>
											<dd>
												<a href="javascript:fn_url('21')"><em>${LeftCntData.left_cnt3}</em></a> 건
											</dd>
										</dl>
										<dl>
											<dt>종료된 설문  :  </dt>
											<dd>
												<a href="javascript:fn_url('21')"><em>${LeftCntData.left_cnt4}</em></a> 건
											</dd>
										</dl>								
									</c:when>
									<c:when test="${LeftCntData.left_gb == '2'}">
										<dl>
											<dt>작성중인 설문  :  </dt>
											<dd>
												<a href="javascript:fn_url('21')"><em>${LeftCntData.left_cnt1}</em></a> 건
											</dd>
										</dl>
										<dl>
											<dt>작성완료된 설문  :  </dt>
											<dd>
												<a href="javascript:fn_url('21')"><em>${LeftCntData.left_cnt2}</em></a> 건
											</dd>
										</dl>
										<dl>
											<dt>진행중인 설문  :  </dt>
											<dd>
												<a href="javascript:fn_url('21')"><em>${LeftCntData.left_cnt3}</em></a> 건
											</dd>
										</dl>								
										<dl>
											<dt>종료된 설문  :  </dt>
											<dd>
												<a href="javascript:fn_url('21')"><em>${LeftCntData.left_cnt4}</em></a> 건
											</dd>
										</dl>
									</c:when>
								</c:choose>	
							</div>
						</div>
					</div>
				</div>
			</div>

</div>

		</div>

		<div class="group2">
			<div class="news">
				<h3>
					<img src="/images/main/new_title.gif" alt="news" />
				</h3>
				<div class="more">
					<a href="javascript:fn_url('22')"><img src="/images/main/more.gif"
						alt="더보기" /></a>
				</div>
				<ul>
					<c:choose>
						<c:when test="${not empty Notice}">
							<c:forEach var="list" items="${Notice}"
								varStatus="status">
								<li><span class="title">
									<a href="javascript:fn_board_url('${list.board_seq }','22')">${list.title}</a></span>
									<span class="date">${list.reg_dt }</span>
								</li>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<li><span class="nodata">데이터가 없습니다.</span></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<div class="faq">
				<h3>
					<img src="/images/main/faq_title.gif" alt="자주하는 질문" />
				</h3>
				<div class="more">
					<a href="javascript:fn_url('23')"><img src="/images/main/more.gif"
						alt="더보기" /></a>
				</div>
				<ul>
					<c:choose>
						<c:when test="${not empty Faq}">
							<c:forEach var="list" items="${Faq}"
								varStatus="status">
								<li><span class="title">
									<a href="javascript:fn_board_url('${list.board_seq }','23')">${list.title}</a></span>
								</li>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<li><span class="nodata">데이터가 없습니다.</span></li>
						</c:otherwise>
					</c:choose>			
				</ul>
			</div>
<!-- 
<div class="banner">
	<a href="http://203.245.65.116" target="_blank"></a>
</div>
 -->
		</div>
		<div class="group3">
			<div class="process_suvery">
				<h3>
					<img src="/images/main/title_process_survey.gif" alt="진행중인 설문" />
				</h3>
				<div class="more">
					<a href="javascript:fn_url4Condition('64', '3')"><img src="/images/main/more.gif" alt="더보기" /></a>
				</div>
						
				<table class="table_style" cellspacing="0" cellpadding="0" border="0" summary="진행중인 설문">
					<caption>진행중인 설문</caption>
					<colgroup>
						<col width="30px" />
						<col width="*" />
						<col width="80px" />
						<col width="80px" />
						<col width="80px" />
					</colgroup>
					<thead>
						<tr>
							<th>No.</th>
							<th>설문명</th>
							<th>응답자 수</th>
							<th>대상자 수</th>
							<th>응답률</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
	               			<c:when test="${not empty SummaryModel}">
	               				<c:set var="tmpCount" value= "0" />
	               				<c:forEach var="list" items="${SummaryModel}" varStatus="status">
			               			<c:if test="${list.survey_status eq 3 && tmpCount < 5}">
			               				<c:set var="tmpCount" value= "${tmpCount + 1}" />
										<tr>
											<td><c:out value="${tmpCount}" /></td>
											<td class="title">
												<%-- <a href="javascript:fn_read('view', <c:out value="${list.survey_seq}" />)"> --%>
												<c:out value="${fn:substring(list.survey_nm, 0, 34)}"/>
												<c:out value="${fn:length(list.survey_nm) > 34 ? '...' : ''}"/>
												<!-- </a> -->
											</td>
											<td>${list.answer_cnt} 명</td>
											<td>${list.target_cnt} 명</td>
											<td>
												<c:if test="${list.target_cnt eq '0'}">
													${list.answer_cnt} 명
												</c:if>
												<c:if test="${list.target_cnt ne '0'}">
													${list.percent} %
												</c:if>
											</td>
										</tr>
									</c:if>
									<c:if test="${status.last}">
										<c:if test="${tmpCount eq 0}">
											<tr><td colspan="5" style="text-align: center;">해당 설문 데이터가 없습니다.</td></tr>
											<c:remove var="tmpCount"/>
										</c:if>
									</c:if>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr><td colspan="5" style="text-align: center;">해당 설문 데이터가 없습니다.</td></tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
	
			<div class="complete_suvery">
				<h3>
					<img src="/images/main/title_complete_survey.gif" alt="완료된 설문" />
				</h3>
				<div class="more">
					<a href="javascript:fn_url4Condition('64', '4')"><img src="/images/main/more.gif" alt="더보기" /></a>
				</div>
		
				<table class="table_style" cellspacing="0" cellpadding="0" border="0" summary="완료된 설문">
					<caption>완료된 설문</caption>
					<colgroup>
						<col width="30px" />
						<col width="*" />
						<col width="80px" />
						<col width="80px" />
						<col width="80px" />
					</colgroup>
					<thead>
						<tr>
							<th>No.</th>
							<th>설문명</th>
							<th>응답자 수</th>
							<th>대상자 수</th>
							<th>응답률</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
	               			<c:when test="${not empty SummaryModel}">
	               				<c:set var="tmpCount" value= "0" />
	               				<c:forEach var="list" items="${SummaryModel}" varStatus="status">
			               			<c:if test="${list.survey_status eq 4 && tmpCount < 5}">
			               				<c:set var="tmpCount" value= "${tmpCount + 1}" />
										<tr>
											<td><c:out value="${tmpCount}" /></td>
											<td class="title">
												<%-- <a href="javascript:fn_read('view', <c:out value="${list.survey_seq}" />)"> --%>												
												<c:out value="${fn:substring(list.survey_nm, 0, 34)}"/>
												<c:out value="${fn:length(list.survey_nm) > 34 ? '...' : ''}"/>
												<!-- </a> -->
											</td>
											<td>${list.answer_cnt} 명</td>
											<td>${list.target_cnt} 명</td>
											<td>
												<c:if test="${list.target_cnt eq '0'}">
													${list.answer_cnt} 명
												</c:if>
												<c:if test="${list.target_cnt ne '0'}">
													${list.percent} %
												</c:if>
											</td>
										</tr>
									</c:if>
									<c:if test="${status.last}">
										<c:if test="${tmpCount eq 0}">
											<tr><td colspan="5" style="text-align: center;">해당 설문 데이터가 없습니다.</td></tr>
											<c:remove var="tmpCount"/>
										</c:if>
									</c:if>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr><td colspan="5" style="text-align: center;">해당 설문 데이터가 없습니다.</td></tr>
							</c:otherwise>
						</c:choose>						
					</tbody>
				</table>
			</div>
		</div>	
		
	</form:form>

</body>
</html>