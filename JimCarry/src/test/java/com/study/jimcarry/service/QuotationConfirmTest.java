//package com.study.jimcarry.service;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import com.study.jimcarry.JimCarryApplication;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.study.jimcarry.api.ConfirmQuotationRequest;
//import com.study.jimcarry.model.ConfirmQuotationDTO;
//import com.study.jimcarry.model.ReqQuotationDTO;
//
//import lombok.extern.slf4j.Slf4j;
//
//@SpringBootTest(classes= {JimCarryApplication.class})
////@Transactional
////@Rollback(false)
//@Slf4j
//public class QuotationConfirmTest {
//
//	@Autowired
//	ConfirmQuotationService confirmQuotationService;
//
//	@Autowired
//	ReqQuotationService reqQuotationService;
//
//	@Test
//	@DisplayName("견적확정 테스트")
//	void insertQuotationReqTest() throws Exception {
//
//		String strDate = "    2024-11-02 00:00:00";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = sdf.parse(strDate);
//
//		//given
//		ConfirmQuotationRequest req = new ConfirmQuotationRequest();
//		req.setQuotationReqNo("30473496-ef9f-443e-986e-70f0f20dc076");
//		req.setConfirmDt(date);
//		req.setCustId("3");
//		req.setDriverId("2");
//
//		ConfirmQuotationDTO dto = ConfirmQuotationDTO.builder()
//			.quotationReqNo(req.getQuotationReqNo())
//			.confirmDt(req.getConfirmDt())
//			.custId(req.getCustId())
//			.driverId(req.getDriverId())
//			.build();
//
//		//when
//		confirmQuotationService.saveConfirmQuotation(dto);
//
//		//then
//		ConfirmQuotationDTO cDto = confirmQuotationService.getConfirmQuotationByUser(dto.getCustId());
//		log.debug("확정 된 견적 => {}", cDto);
//
//		ReqQuotationDTO rDto = reqQuotationService.getReqQuotationByUser(dto.getCustId());
//		log.debug("확정 된 견적상태 => {}", rDto.getStatus());
//	}
//
//	@Test
//	@DisplayName("확정 견적 사용자별 조회")
//	void selectComfirmByUserTest() {
//		//given
//		String custId = "3";
//
//		//when
//		ConfirmQuotationDTO cDto = confirmQuotationService.getConfirmQuotationByUser(custId);
//
//		//then
//		log.debug("확정 된 견적 => {}", cDto);
//	}
//
//	@Test
//	@DisplayName("확정 견적 기사님별 조회")
//	void selectComfirmByDriverTest() {
//		//given
//		String driverId = "2";
//
//		//when
//		List<ConfirmQuotationDTO> list = confirmQuotationService.getConfirmQuotationListByDriver(driverId);
//
//		//then
//		list.stream().forEach(c -> log.debug("기사님별 확정 견적 => {}", c));
//
//	}
//
//	@Test
//	@DisplayName("견적 철회")
//	void deleteConfirmQuotationTest() {
//		//given
//		String quotationId = "30473496-ef9f-443e-986e-70f0f20dc076";
//
//		//then
//		confirmQuotationService.deleteConfirmQuotation(quotationId);
//
//		//when
//	}
//}
