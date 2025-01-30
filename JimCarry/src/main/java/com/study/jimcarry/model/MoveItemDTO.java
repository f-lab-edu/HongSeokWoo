package com.study.jimcarry.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // null 제외
public class MoveItemDTO {

	// 견적요청_ID
	@Schema(name = "quotationReqNo", description = "견적요청 번호")
	private String quotationReqNo;

	//고객 아이디
	@Schema(name="furnitureId", description="가구 ID") //Swagger/OpenAPI 문서를 자동 생성
	private Integer furnitureId;

	// 출발지 주소
	@Schema(name="optionValId", description="옵션 명 ID") //Swagger/OpenAPI 문서를 자동 생성
	private Integer optionValId;

	// 출발지 주소
	@Schema(name="quantity", description="가구 수량") //Swagger/OpenAPI 문서를 자동 생성
	private Integer quantity;

	@Schema(name="cid", description="생성자") //Swagger/OpenAPI 문서를 자동 생성
	private String cid;
}
