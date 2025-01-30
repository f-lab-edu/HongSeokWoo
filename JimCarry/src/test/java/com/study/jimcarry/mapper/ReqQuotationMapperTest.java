//package com.study.jimcarry.mapper;
//
//import com.study.jimcarry.domain.ReqQuotationEntity;
//import com.study.jimcarry.type.QuotationStatus;
//import org.junit.jupiter.api.Test;
//import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@MybatisTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //Spring Boot 에선 기본적으로 테스트 시 임베디드 데이터 베이스를 사용(H2, HSQ: Derby ...) 해당 어노테이션은 실제 데이터 베이스를 쓰겠다는 의미
//public class ReqQuotationMapperTest {
//
//    @Autowired
//    private ReqQuotationMapper reqQuotationMapper;
//
//    @Test
//    void testInsertReqQuotation() {
//        ReqQuotationEntity entity = ReqQuotationEntity.builder()
//                .quotationReqNo("ReqQuo_test")
//                .quotationDt(LocalDateTime.now())
//                .custId("CUST001")
//                .pickupAddr("123 Start Street")
//                .deliveryAddr("456 End Avenue")
//                .moveDt(new Date())
//                .quotationAmount(BigDecimal.valueOf(1500.75))
//                .buildingType("Apartment")
//                .roomStructure("2Room")
//                .houseSize(20)
//                .hasElevator(true)
//                .boxCount(10)
//                .cid(1)
//                .status("Pending")
//                .version(1)
//                .build();
//
//        int result = reqQuotationMapper.insertReqQuotation(entity);
//
//        assertThat(result).isEqualTo(1);
//    }
//
//    @Test
//    void testSelectAllReqQuotations() {
//        List<ReqQuotationEntity> quotations = reqQuotationMapper.selectAllReqQuotations();
//        assertThat(quotations).isNotEmpty();
//    }
//
//    @Test
//    void testSelectReqQuotationByUser() {
//        ReqQuotationEntity entity = reqQuotationMapper.selectReqQuotationByUser("3");
//        assertThat(entity).isNotNull();
//        assertThat(entity.getCustId()).isEqualTo("3");
//    }
//
//    @Test
//    void testUpdateReqQuotation() {
//        ReqQuotationEntity entity = ReqQuotationEntity.builder()
//                .quotationReqNo("30473496-ef9f-443e-986e-70f0f20dc076")
//                .pickupAddr("Test_Pickup_Addr")
//                .deliveryAddr("Test_Delivery_Addr")
//                .moveDt(new Date())
//                .quotationAmount(BigDecimal.valueOf(1500.75))
//                .buildingType("Apartment")
//                .roomStructure("2Room")
//                .status(QuotationStatus.CONFIRMED.getCode())
//                .version(37)
//                .build();
//
//        int result = reqQuotationMapper.updateReqQuotation(entity);
//        assertThat(result).isEqualTo(1);
//    }
//
//    @Test
//    void testUpdateReqQuotationStatus() {
//        String reqQuotationId = "30473496-ef9f-443e-986e-70f0f20dc076";
//        String newStatus = QuotationStatus.CONFIRMED.getCode();
//
//        int result = reqQuotationMapper.updateReqQuotationStatus(reqQuotationId, newStatus);
//        assertThat(result).isEqualTo(1);
//
//        ReqQuotationEntity entity = reqQuotationMapper.selectReqQuotation(reqQuotationId);
//        assertThat(entity.getStatus()).isEqualTo(newStatus);
//    }
//
//    @Test
//    void testDeleteReqQuotation() {
//        String reqQuotationId = "30473496-ef9f-443e-986e-70f0f20dc076";
//
//        int result = reqQuotationMapper.deleteReqQuotation(reqQuotationId);
//        assertThat(result).isEqualTo(1);
//
//        ReqQuotationEntity entity = reqQuotationMapper.selectReqQuotation(reqQuotationId);
//        assertThat(entity).isNull();
//    }
//}
