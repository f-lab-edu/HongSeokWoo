package com.study.jimcarry.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 견적요청 테이블
 */
@Getter
@Builder
@ToString
public class ReqQuotationEntity {
	
	// 견적요청 번호
	private String quotationReqNo;

	// 견적요청일시
	private LocalDateTime quotationDt;

	// 고객 아이디
	private String custId;

	// 출발지 주소
	private String pickupAddr;

	// 목적지 주소
	private String deliveryAddr;

	// 이사 예정일자
	private Date moveDt;
	
	// 건물 종류 (빌라/연립, 오피스텔, 주택, 아파트, 상가/사무실)
	private String buildingType;

	// 방 구조 (원룸, 1.5룸, 2룸, 3룸, 4룸, 5룸 이상)
	private String roomStructure;

	// 집 평수
	private int houseSize;

	// 엘리베이터 여부
	private boolean hasElevator;

	// 짐 박스 갯수
	private int boxCount;

	// 견적요청 금액
	private BigDecimal quotationAmount;
	
	//생성자
	private Integer cid;
	
	//견적 상태
	private String status;
	
	//Update 버전
	private Integer version;

}
