//package com.study.jimcarry.service;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//import java.math.BigDecimal;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
//import com.study.jimcarry.domain.ReqQuotationEntity;
//import com.study.jimcarry.mapper.MoveItemMapper;
//import com.study.jimcarry.mapper.ReqQuotationMapper;
//import com.study.jimcarry.type.QuotationStatus;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.study.jimcarry.model.MoveItemDTO;
//import com.study.jimcarry.model.ReqQuotationDTO;
//import com.study.jimcarry.model.UpdateReqQuotationDTO;
//
//import lombok.extern.slf4j.Slf4j;
//
////대상이되는 Service만 테스트, 단위테스트로 변경(특정레이어), Mock객체 생성
////@SpringBootTest(classes= {JimCarryApplication.class}) 단위테스트에선 필요하지 않음.
////@SpringBootTest는 애플리케이션 전체 컨텍스트를 로드 하기 떄문에 주로 통합 테스트에서 쓰인다.
////@Transactional
////@Rollback(false)
//@Slf4j
//public class ReqQuotationTest {
//
//	@Mock
//	private ReqQuotationMapper reqQuotationMapper;
//
//	@Mock
//	private MoveItemMapper moveItemMapper;
//
//	@InjectMocks
//	ReqQuotationService reqQuotationService;
//
//	@BeforeEach
//	void setUp() {
//		MockitoAnnotations.openMocks(this); // @Mock 객체 초기화 및 주입
//	}
//
//	@Test
//	@DisplayName("견적 작성 테스트")
//	void insertQuotationReqTest() throws Exception{
//
////		String strDate = "2024-11-02 00:00:00";
////		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////		Date date = sdf.parse(strDate);
//		final Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2024-11-02 00:00:00");
//
//		// given: 서비스에 전달할 DTO 객체와 그에 필요한 값만 준비
//		// ReqQuotationDTO 객체 준비
//		ReqQuotationDTO dto = ReqQuotationDTO.builder()
//				.custId("3")
//				.pickupAddr("Seoul, Korea")
//				.deliveryAddr("Busan, Korea")
//				.moveDt(date)
//				.buildingType("Apartment")
//				.roomStructure("2 rooms")
//				.houseSize(24)
//				.hasElevator(true)
//				.boxCount(30)
//				.quotationAmount(BigDecimal.valueOf(1000000.00))
//				.status(QuotationStatus.DRAFT.getCode())
//				.version(1)
//				.build();
//
//		// MoveItemDTO 리스트 준비
//		List<MoveItemDTO> dtoList = new ArrayList<>();
//		dtoList.add(MoveItemDTO.builder().furnitureId(1).optionValId(1).quantity(1).build());
//		dtoList.add(MoveItemDTO.builder().furnitureId(2).optionValId(2).quantity(1).build());
//		dtoList.add(MoveItemDTO.builder().furnitureId(2).optionValId(5).quantity(1).build());
//		dtoList.add(MoveItemDTO.builder().furnitureId(2).optionValId(9).quantity(1).build());
//
//		// ReqQuotationEntity로 변환
//		ReqQuotationEntity entity = ReqQuotationEntity.builder()
//				.custId(dto.getCustId())
//				.pickupAddr(dto.getPickupAddr())
//				.deliveryAddr(dto.getDeliveryAddr())
//				.moveDt(dto.getMoveDt())
//				.buildingType(dto.getBuildingType())
//				.roomStructure(dto.getRoomStructure())
//				.houseSize(dto.getHouseSize())
//				.hasElevator(dto.isHasElevator())
//				.boxCount(dto.getBoxCount())
//				.quotationAmount(dto.getQuotationAmount())
//				.status(QuotationStatus.DRAFT.getCode())  // 기본 상태 설정
//				.version(1)
//				.build();
//
//		// when: 서비스 메서드 호출
//		when(reqQuotationMapper.insertReqQuotation(entity)).thenReturn(1);;  // Mock insert 동작
//
//		reqQuotationService.saveReqQuotation(dto, dtoList);  // 서비스 호출
//
//		// then: getReqQuotationByUser() 메서드가 예상대로 동작하는지 mock 설정
//		when(reqQuotationMapper.selectReqQuotationByUser(dto.getCustId())).thenReturn(entity);
//
//		// 결과 검증
//		ReqQuotationDTO reqDto = reqQuotationService.getReqQuotationByUser(dto.getCustId());
//
//		// assertThat
//		assertThat(dto).isEqualTo(reqDto);
//	}
//
//
//	@Test
//	@DisplayName("사용자별 견적 조회")
//	void selectQuotationByUserTest() throws ParseException {
//		//given
//		String custId = "1";
//
//		ReqQuotationEntity mockEntity = ReqQuotationEntity.builder()
//				.quotationReqNo("QR123")
//				.custId(custId)
//				.pickupAddr("서울")
//				.deliveryAddr("부산")
//				.moveDt(new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-01"))
//				.buildingType("아파트")
//				.roomStructure("3룸")
//				.houseSize(80)
//				.hasElevator(true)
//				.boxCount(10)
//				.quotationAmount(BigDecimal.valueOf(500000))
//				.status(QuotationStatus.DRAFT.getCode())
//				.version(1)
//				.build();
//
//		//when
//		when(reqQuotationMapper.selectReqQuotationByUser(custId)).thenReturn(mockEntity);
//
//		// Then
//		ReqQuotationDTO dto = reqQuotationService.getReqQuotationByUser(custId);
//
//
//		// assertThat를 사용한 검증
//		assertThat(dto).isEqualTo(mockEntity);
//
//
//	}
//
//	@Test
//	@DisplayName("견적서 전체 조회")
//	void selectQuotaionListAllTest() {
//		// given: Prepare mock data for the test
//		ReqQuotationDTO dto1 = ReqQuotationDTO.builder()
//				.quotationReqNo("1")
//				.custId("3")
//				.pickupAddr("Seoul")
//				.deliveryAddr("Busan")
//				.moveDt(new Date())
//				.quotationAmount(BigDecimal.valueOf(1000000.00))
//				.build();
//
//		ReqQuotationDTO dto2 = ReqQuotationDTO.builder()
//				.quotationReqNo("2")
//				.custId("4")
//				.pickupAddr("Incheon")
//				.deliveryAddr("Daegu")
//				.moveDt(new Date())
//				.quotationAmount(BigDecimal.valueOf(2000000.00))
//				.build();
//
//		// Create a mock list
//		List<ReqQuotationDTO> mockList = Arrays.asList(dto1, dto2);
//
//		// when: Define the behavior of the mocked service method
//		when(reqQuotationService.getReqQuotationList()).thenReturn(mockList);
//
//		// when: Call the method under test
//		List<ReqQuotationDTO> list = reqQuotationService.getReqQuotationList();
//
//		// then: Assert the values using assertEquals
//		assertThat(list).isEqualTo(mockList);
//	}
//
//	@Test
//	@DisplayName("견적 상태 변경")
//	void updateQuotaionStatusTest() {
//		//given
//		String reqQuotationId = "30473496-ef9f-443e-986e-70f0f20dc076";
//		String status = "1";
//
//		//when
//		reqQuotationService.updateReqQuotationStatus(reqQuotationId, status);
//
//		//then
//		ReqQuotationDTO dto = reqQuotationService.getReqQuotationByUser("3");
//
//	}
//
//	@Test
//	@DisplayName("견적 수정")
//	void modifyQuotationTest() throws Exception {
//
//		// Test data preparation
////	    String strDate = "2024-11-05 00:00:00";
////	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////	    Date date = sdf.parse(strDate);
//		final Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2024-11-02 00:00:00");
//		// given
//		UpdateReqQuotationDTO updateDto = UpdateReqQuotationDTO.builder()
//				.pickupAddr("YangPeyng, Korea")
//				.deliveryAddr("Tokyo, Japan")
//				.moveDt(date)
//				.buildingType("단독주택")
//				.roomStructure("원룸")
//				.houseSize(18)
//				.hasElevator(false)
//				.boxCount(10)
//				.quotationAmount(BigDecimal.valueOf(1000.00))
//				.build();
//
//		String quotationId = "30473496-ef9f-443e-986e-70f0f20dc076";
//
//		// when
//		reqQuotationService.modifyReqQuotation(updateDto, quotationId);
//
//		// then
//		ReqQuotationDTO dto = reqQuotationService.getReqQuotationByUser("3"); // Assuming user ID is "3"
//		log.debug("변경 된 값 -> {}", dto);
//
//	}
//
//	@Test
//	@DisplayName("견적 수정 멀티스레드 테스트 스레드풀 5개, 요청 10개")
//	void modifyQuotationMultiTest() throws Exception {
//		// Test data preparation
////	    String strDate = "2024-11-05 00:00:00";
////	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////	    Date date = sdf.parse(strDate);
//		final Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2024-11-02 00:00:00");
//
//		// Update DTO preparation
//		UpdateReqQuotationDTO updateDto = UpdateReqQuotationDTO.builder()
//				.pickupAddr("YangPeyng, Korea")
//				.deliveryAddr("Tokyo, Japan")
//				.moveDt(date)
//				.buildingType("단독주택")
//				.roomStructure("원룸")
//				.houseSize(18)
//				.hasElevator(false)
//				.boxCount(10)
//				.quotationAmount(BigDecimal.valueOf(1000.00))
//				.build();
//
//		String quotationId = "30473496-ef9f-443e-986e-70f0f20dc076";
//
//		// ExecutorService를 사용하여 멀티스레드 환경에서 수정 요청을 처리
//		ExecutorService executorService = Executors.newFixedThreadPool(5); // 5개의 스레드
//		List<Future<Void>> futures = new ArrayList<>();
//
//		for (int i = 0; i < 10; i++) { // 10개의 수정 요청
//			final int attempt = i;
//			futures.add(executorService.submit(() -> {
//				try {
//					reqQuotationService.modifyReqQuotation(updateDto, quotationId);
//					log.debug("수정 성공: 요청 번호 {}", attempt);
//				} catch (Exception e) {
//					log.error("수정 실패: 요청 번호 {} - {}", attempt, e.getMessage());
//				}
//				return null;
//			}));
//		}
//
//		// 모든 작업이 완료될 때까지 대기
//		for (Future<Void> future : futures) {
//			try {
//				future.get(); // 예외를 체크하기 위해 get() 호출
//			} catch (ExecutionException e) {
//				log.error("스레드 실행 중 예외 발생: {}", e.getCause().getMessage());
//			}
//		}
//
//		executorService.shutdown(); // 스레드 풀 종료
//	}
//
//	@Test
//	@DisplayName("견적 삭제")
//	void deleteQuotaionTest() {
//		//given
//		String quotaionId = "30473496-ef9f-443e-986e-70f0f20dc076";
//
//		//when
//		reqQuotationService.deleteReqQuotation(quotaionId);
//
//		//then
//
//	}
//}
