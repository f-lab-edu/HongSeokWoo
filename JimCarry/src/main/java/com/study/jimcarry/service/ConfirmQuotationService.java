package com.study.jimcarry.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.study.jimcarry.domain.ConfirmQuotationEntity;
import com.study.jimcarry.domain.ReqQuotationEntity;
import com.study.jimcarry.exception.CustomException;
import com.study.jimcarry.exception.ErrorCode;
import com.study.jimcarry.mapper.ConfirmQuotationMapper;
import com.study.jimcarry.mapper.ReqQuotationMapper;
import com.study.jimcarry.model.ConfirmQuotationDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor //Lombok에서 제공하는 어노테이션으로, final이나 @NonNull이 붙은 필드에 대해 생성자를 자동으로 생성해주는 역할
public class ConfirmQuotationService {
	
    private final ConfirmQuotationMapper confirmQuotationMapper;
    private final ReqQuotationMapper reqQuotationMapper;
    
	/**
	 * 견적확정 정보 저장 
	 * @param confirmQuotationEntity
	 * @return
	 */
	public int saveConfirmQuotation(ConfirmQuotationDTO confirmQuotation) {
		//견적 테이블의 status 값을 1로 update
		reqQuotationMapper.updateReqQuotationStatus(confirmQuotation.getQuotationReqNo(), "1");
		return confirmQuotationMapper.insertConfirmQuotation(ConfirmQuotationEntity.builder()
				.quotationReqNo(confirmQuotation.getQuotationReqNo())
				.confirmDt(confirmQuotation.getConfirmDt())
				.custId(confirmQuotation.getCustId())
				.driverId(confirmQuotation.getDriverId())
				.cid(0)
				.build());
	}

//	/**
//	 * 견적확정 정보 수정
//	 * @param confirmQuotationEntity
//	 * @return
//	 */
//	@Deprecated
//	public int modifyConfrimQuotation(ConfirmQuotationDTO confirmQuotation) {
//		
//		ConfirmQuotationEntity confirmQuotationEntity = ConfirmQuotationEntity.builder()
//				.quotationReqNo(confirmQuotation.getQuotationReqNo())
//				.confirmDt(confirmQuotation.getConfirmDt())
//				.custId(confirmQuotation.getCustId())
//				.driverId(confirmQuotation.getDriverId())
//				.build();
//		
//		ConfirmQuotationEntity findConfirmQuotation = confirmQuotationMapper.selectConfrimQuotation(confirmQuotationEntity.getQuotationReqNo());
//		if(findConfirmQuotation == null) {
//			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
//		}
//		return confirmQuotationMapper.updateConfirmQuotation(confirmQuotationEntity);
//	}
	
	/**
	 * 견적 확정정보 삭제(철회)
	 * @param reqQuotationId
	 * @return
	 */
	public int deleteConfirmQuotation(String quotationId) {
		Optional<ReqQuotationEntity> optionalEntity = Optional.ofNullable(reqQuotationMapper.selectReqQuotation(quotationId));
		ReqQuotationEntity entity = optionalEntity.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
		
	    if ("2".equals(entity.getStatus())) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "이미 채택 된 견적 입니다.");
        }
		
	    reqQuotationMapper.updateReqQuotationStatus(quotationId, "0");
		return confirmQuotationMapper.deleteConfirmQuotation(quotationId);
	}
	
	/**
	 * 기사님별 견적확정 정보 조회
	 * @return
	 */
	public List<ConfirmQuotationDTO> getConfirmQuotationListByDriver(String driverId) {
		List<ConfirmQuotationEntity> list = confirmQuotationMapper.selectConfirmQuotationByDriver(driverId);
		List<ConfirmQuotationDTO> quotationList = new ArrayList<>();
		for(ConfirmQuotationEntity entity : list) {
			quotationList.add(ConfirmQuotationDTO.builder()
					.quotationReqNo(entity.getQuotationReqNo())
					.confirmDt(entity.getConfirmDt())
					.custId(entity.getCustId())
					.driverId(entity.getDriverId())
					.build());
		}
		return quotationList;
	}
	
	/**
	 * 사용자별 견적확정 정보 조회
	 * @param customerId
	 * @return
	 */
	public ConfirmQuotationDTO getConfirmQuotationByUser(String customerId) {
//		ConfirmQuotationEntity entity = confirmQuotationMapper.selectConfirmQuotationByUser(customerId);
//		if(entity == null) {
//			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
//		}
		
		Optional<ConfirmQuotationEntity> optionalEntity = Optional.ofNullable(confirmQuotationMapper.selectConfirmQuotationByUser(customerId));
		ConfirmQuotationEntity entity = optionalEntity.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
		
		return ConfirmQuotationDTO.builder()
				.quotationReqNo(entity.getQuotationReqNo())
				.confirmDt(entity.getConfirmDt())
				.custId(entity.getCustId())
				.driverId(entity.getDriverId())
				.build();
	}
	
}
