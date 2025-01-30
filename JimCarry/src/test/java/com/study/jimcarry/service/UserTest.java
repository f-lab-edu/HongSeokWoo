//package com.study.jimcarry.service;
//
//import com.study.jimcarry.JimCarryApplication;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.study.jimcarry.domain.RoleEntity;
//import com.study.jimcarry.model.UserInfoDTO;
//import com.study.jimcarry.type.UserType;
//
//import lombok.extern.slf4j.Slf4j;
//
//@SpringBootTest(classes = { JimCarryApplication.class })
//@Transactional
//@Rollback(false)
//@Slf4j
//public class UserTest {
//
//   private final UserService authService; // 필드 정의
//
//   @Autowired
//   public UserTest(UserService authService) { // 생성자 주입
//       this.authService = authService;
//   }
//
//
//	@Test
//	@DisplayName("권한 생성 테스트")
//	void saveRoleTest() {
//
//	    // given
//        RoleEntity role = RoleEntity.builder() // Builder 패턴 사용
//                .roleName(UserType.DRIVER.getCode())        // 권한 이름 설정
//                .description(UserType.DRIVER.getName()) // 권한 설명 설정
//                .build();                     // 객체 생성
//
//        // when
//        authService.saveRoleInfo(role); // 권한 정보를 저장하는 메서드 호출
//
//        // then
//        log.debug("Role created: {}", role);
//
//	}
//
//	@Test
//	@DisplayName("사용자 생성 테스트")
//	void saveUserTest() {
//
//	    // given
//		UserInfoDTO user = UserInfoDTO.builder()
////						.userName("USER_1")
////						.password("12345")
////						.phoneNumber("010-1111-2222")
////						.email("user1@naver.com")
////						.userType(UserType.USER.getCode())
////						.build();
////
//						.userId("DRIVER")
//						.userName("DRIVER_NAME")
//						.password("12345")
//						.phoneNumber("010-2222-3333")
//						.email("driver1@naver.com")
//						.userType(UserType.DRIVER.getCode())
//						.build();
//
////						.userName("ADMIN")
////						.password("12345")
////						.phoneNumber("010-4444-5555")
////						.email("admin@naver.com")
////						.userType(UserType.ADMIN.getCode())
////						.build();
//
//        // when
//        authService.saveUserInfo(user);
//
//        // then
//        log.debug("user created: {}", user.getUserName());
//
//	}
//}
