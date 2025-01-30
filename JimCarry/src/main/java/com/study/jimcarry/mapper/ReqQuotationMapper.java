package com.study.jimcarry.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.jimcarry.domain.ReqQuotationEntity;

@Mapper
public interface ReqQuotationMapper {
	
	/**
	 * 견적요청서 저장
	 * @param reqQuotationEntity
	 * @return
	 */
	int insertReqQuotation(ReqQuotationEntity reqQuotationEntity);
	
	/**
	 * 견적요청서 수정
	 * @param reqQuotationEntity
	 * @return
	 */
	int updateReqQuotation(ReqQuotationEntity reqQuotationEntity);
	
	/**
	 * 견적요청서 삭제
	 * @param reqQuotationId
	 * @return
	 */
	int deleteReqQuotation(String reqQuotationId);
	
	/**
	 * 견적 요청서 전체 조회
	 * @return
	 */
	List<ReqQuotationEntity> selectAllReqQuotations();
	
	/**
	 * 사용자별 견적요청서 조회
	 * @param customerId
	 * @return
	 */
	ReqQuotationEntity selectReqQuotationByUser(String customerId);

	/**
	 * 견적상태 갱신
	 * @param reqQuotationId
	 * @param status
	 * @return
	 */
	   int updateReqQuotationStatus(@Param("quotationReqNo") String reqQuotationId, @Param("status") String status);

	/**
	 * 견적요청서 조회
	 * @param reqQuotationId
	 * @return
	 */
	ReqQuotationEntity selectReqQuotation(String reqQuotationId);
}
