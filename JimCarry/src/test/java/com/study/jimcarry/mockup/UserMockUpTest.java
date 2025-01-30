//package com.study.jimcarry.mockup;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.study.jimcarry.api.UserRequest;
//import com.study.jimcarry.controller.UserController;
//import com.study.jimcarry.service.UserService;
//import com.study.jimcarry.type.UserType;
//
//import lombok.extern.slf4j.Slf4j;
//
//@WebMvcTest(UserController.class)
//@Slf4j
//public class UserMockUpTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService authService;
//
//    @MockBean
//    private ModelMapper modelMapper; // ModelMapper를 Mock으로 설정
//
//    @Test
//    public void saveUserTest() throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        UserRequest request = new UserRequest();
//        request.setUserName("TEST_USER");
//        request.setPassword("12345");
//        request.setPhoneNumber("010-9999-8888");
//        request.setEmail("test@email.com");
//        request.setUserType(UserType.USER.getCode());
//
//        String jsonString = objectMapper.writeValueAsString(request);
//
//        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/auth/users")
//                .contentType("application/json;charset=UTF-8")
//                .content(jsonString);
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
//	@DisplayName("Login Test")
//    public void loginTest() throws Exception {
//
//        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/loginProcess")
//    			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("id", "TEST_ADMIN")
//                .param("pw", "12345");
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
