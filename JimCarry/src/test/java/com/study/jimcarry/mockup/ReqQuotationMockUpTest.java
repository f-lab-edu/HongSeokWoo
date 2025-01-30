//package com.study.jimcarry.mockup;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.study.jimcarry.api.MoveItemRequest;
//import com.study.jimcarry.api.ReqQuotationRequest;
//import com.study.jimcarry.controller.ReqQuotationController;
//import com.study.jimcarry.service.ReqQuotationService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@WebMvcTest(ReqQuotationController.class)
//@Slf4j
//public class ReqQuotationMockUpTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean // ReqQuotationService를 Mock으로 설정
//    private ReqQuotationService reqQuotationService;
//
//    @MockBean
//    private ModelMapper modelMapper; // ModelMapper를 Mock으로 설정
//
//    @Test
//    @WithMockUser(username = "USER_NAME1")
//    public void saveReqQuotationTest() throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        ReqQuotationRequest request = new ReqQuotationRequest();
//        request.setCustId("customer_123");
//        request.setPickupAddr("Seoul, Korea");
//        request.setDeliveryAddr("Busan, Korea");
//        request.setMoveDt(new Date(2024-11-02)); // set a valid move date
//        request.setQuotationAmount(BigDecimal.valueOf(1000000.00));
//        request.setBuildingType("Apartment");
//        request.setRoomStructure("2 rooms");
//        request.setHouseSize(24);
//        request.setHasElevator(true);
//        request.setBoxCount(30);
//        request.setCid(1);
//
//        //이사 짐 정보
//        List<MoveItemRequest> moveItemList = new ArrayList<>();
//        MoveItemRequest moveReq1= new MoveItemRequest();
//        moveReq1.setFurnitureId(1);
//        moveReq1.setOptionValId(1);
//        moveReq1.setQuantity(1);
//        moveItemList.add(moveReq1);
//
//        MoveItemRequest moveReq2= new MoveItemRequest();
//        moveReq2.setFurnitureId(2);
//        moveReq2.setOptionValId(2);
//        moveReq2.setQuantity(1);
//        moveItemList.add(moveReq2);
//
//        MoveItemRequest moveReq3= new MoveItemRequest();
//        moveReq3.setFurnitureId(2);
//        moveReq3.setOptionValId(5);
//        moveReq3.setQuantity(1);
//        moveItemList.add(moveReq3);
//
//        MoveItemRequest moveReq4= new MoveItemRequest();
//        moveReq4.setFurnitureId(2);
//        moveReq4.setOptionValId(9);
//        moveReq4.setQuantity(1);
//        moveItemList.add(moveReq4);
//
//        request.setMoveItemList(moveItemList);
//
//        String json = objectMapper.writeValueAsString(request);
//        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/quotation")
//                .contentType("application/json;charset=UTF-8")
//                .content(json); // CSRF 비활성화;
//
//        MvcResult mvcResult = mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(print())
//                .andReturn();
//
//        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
//
//        String body = mvcResult.getResponse().getContentAsString();
//        log.debug("response: {}", body);
//    }
//
//    @Test
//    public void getReqQuotationListTest() throws Exception {
//        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/req-quotation/list");
//
//        MvcResult mvcResult = mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(print())
//                .andReturn();
//
//        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
//
//        String body = mvcResult.getResponse().getContentAsString();
//        log.debug("response: {}", body);
//    }
//}
