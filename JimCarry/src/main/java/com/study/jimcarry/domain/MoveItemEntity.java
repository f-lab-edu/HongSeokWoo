package com.study.jimcarry.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

/**
 * 이삿 짐 정보 테이블
 */
@Getter
@Builder
public class MoveItemEntity {
	// 견적요청 번호
	private String quotationReqNo;

	//가구 아이디
	private Integer furnitureId;

	//옵션 명 아이디
	private Integer optionValId;

	//가구 수량
	private Integer quantity;

	//생성자
	private Integer cid;

}
