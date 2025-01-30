package com.study.jimcarry.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.study.jimcarry.type.QuotationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

//@Data
/**
 * @Data는 Lombok에서 @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor를 한 번에 적용하는 어노테이션
 * 하지만 @Builder를 사용할 때 @Data와 같이 사용할 경우, @Builder가 자동으로 만들어주는 생성자와 충돌할 수 있습니다.
 * 모든 필드에 대한 setter가 생성되기 때문에 빌더 패턴의 사용이 필요 없어 보이는 문제가 있을 수 있습니다.
 */
@Getter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL) // null 제외
@EqualsAndHashCode
public class ReqQuotationDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	// 견적요청_ID
	@Schema(name = "quotationReqNo", description = "견적요청 번호")
	private String quotationReqNo;

	// 견적요청일시
	@Schema(name = "quotationDt", description = "견적요청일시")
	private LocalDateTime quotationDt;

	// 고객 아이디
	@Schema(name = "custId", description = "고객 아이디")
	private String custId;

	// 출발지 주소
	@Schema(name = "pickupAddr", description = "출발지 주소")
	private String pickupAddr;

	// 목적지 주소
	@Schema(name = "deliveryAddr", description = "목적지 주소")
	private String deliveryAddr;

	// 이사 예정일자
	@Schema(name = "moveDt", description = "이사 예정일자")
	private Date moveDt;

	// 건물 종류 (빌라/연립, 오피스텔, 주택, 아파트, 상가/사무실)
	@Schema(name = "buildingType", description = "건물 종류 (빌라/연립, 오피스텔, 주택, 아파트, 상가/사무실)")
	private String buildingType;

	// 방 구조 (원룸, 1.5룸, 2룸, 3룸, 4룸, 5룸 이상)
	@Schema(name = "roomStructure", description = "방 구조 (원룸, 1.5룸, 2룸, 3룸, 4룸, 5룸 이상)")
	private String roomStructure;

	// 집 평수
	@Schema(name = "houseSize", description = "집 평수")
	private int houseSize;

	// 엘리베이터 여부
	@Schema(name = "hasElevator", description = "엘리베이터 여부")
	private boolean hasElevator;

	// 짐 박스 갯수
	@Schema(name = "boxCount", description = "짐 박스 갯수")
	private int boxCount;

	// 견적요청 금액
	@Schema(name = "quotationAmount", description = "견적요청 금액")
	private BigDecimal quotationAmount;

	// 이사 짐 정보 리스트
//	@Schema(name = "moveItemList", description = "이사 짐 정보 리스트")
//	private List<MoveItemDTO> moveItemList;

	@Schema(name="cid", description="생성자") //Swagger/OpenAPI 문서를 자동 생성
	private String cid;

	//견적상태 -> 0: 견적요청 / 1: 견적확정 / 2: 견적채택
	@Schema(name = "status", description = "견적상태")
	private String status = QuotationStatus.DRAFT.getCode();

	@Schema(name = "version", description = "버전")
	private int version;
}
