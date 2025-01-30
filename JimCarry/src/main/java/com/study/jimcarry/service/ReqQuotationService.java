package com.study.jimcarry.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.uuid.Generators;
import java.util.UUID;

import com.study.jimcarry.type.QuotationStatus;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.jimcarry.domain.MoveItemEntity;
import com.study.jimcarry.domain.ReqQuotationEntity;
import com.study.jimcarry.exception.CustomException;
import com.study.jimcarry.exception.ErrorCode;
import com.study.jimcarry.mapper.MoveItemMapper;
import com.study.jimcarry.mapper.ReqQuotationMapper;
import com.study.jimcarry.model.MoveItemDTO;
import com.study.jimcarry.model.ReqQuotationDTO;
import com.study.jimcarry.model.UpdateReqQuotationDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReqQuotationService {

	private final ReqQuotationMapper reqQuotationMapper;

	private final MoveItemMapper moveItemMapper;

	private static final int MAX_RETRIES = 3; // 최대 재시도 횟수

	/**
	 * 견적요청서 작성
	 * @param reqQuotation
	 * @param moveItemList
	 * @return
	 */
	@Transactional
	public int saveReqQuotation(ReqQuotationDTO reqQuotation, List<MoveItemDTO> moveItemList) {

		// UUID Version1 생성
		// 타임 스탬프 기반으로 생성 되며 시간 순서대로 정렬이 됨.
		String uuidVer1 = Generators.timeBasedGenerator().generate().toString();
		log.debug("uuid ver1 -> {}", uuidVer1);

		// UUID version4 생성
		// version4는 정렬되지 않은 UUID로 PK로 사용할 경우
		// 삽입, 조회 성능에 영향을 미침
		String uuidVer4 = UUID.randomUUID().toString();
		log.debug("uuid ver4 -> {}", uuidVer4);

		for (MoveItemDTO dto : moveItemList) {

			moveItemMapper.insertMoveItem(
					MoveItemEntity.builder()
							.quotationReqNo(uuidVer1)
							.furnitureId(dto.getFurnitureId())
							.optionValId(dto.getOptionValId())
							.quantity(dto.getQuantity())
							.cid(0)
							.build());
		}

		// 레코드 저장
		return reqQuotationMapper.insertReqQuotation(
				ReqQuotationEntity.builder().quotationReqNo(uuidVer1).custId(reqQuotation.getCustId())
						.pickupAddr(reqQuotation.getPickupAddr()).deliveryAddr(reqQuotation.getDeliveryAddr())
						.moveDt(reqQuotation.getMoveDt()).buildingType(reqQuotation.getBuildingType())
						.roomStructure(reqQuotation.getRoomStructure()).houseSize(reqQuotation.getHouseSize())
						.hasElevator(reqQuotation.isHasElevator()).boxCount(reqQuotation.getBoxCount())
						.quotationAmount(reqQuotation.getQuotationAmount()).cid(0).build());
	}

	/**
	 * 견적요청서 수정
	 * @param updateReqQuotation
	 * @param quotationId
	 * @return
	 */
	@Transactional
	public int modifyReqQuotation(UpdateReqQuotationDTO updateReqQuotation, String quotationId) {
		for (int attempt = 0; attempt < MAX_RETRIES; attempt++) {
			// 견적 정보를 조회
			/**
			 * AS-IS
			 */
//			Optional<ReqQuotationEntity> optionalEntity = Optional
//					.ofNullable(reqQuotationMapper.selectReqQuotation(quotationId));
//			ReqQuotationEntity entity = optionalEntity.orElseThrow(
//					() -> new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
			/**
			 * TO-BE
			 */
			ReqQuotationEntity entity = Optional.ofNullable(reqQuotationMapper.selectReqQuotation(quotationId))
					.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));


			// 견적 상태가 "0"이 아닐 경우 예외를 발생
			QuotationStatus.validDraftStatus(entity.getStatus());
//			if (!QuotationStatus.DRAFT.getCode().equals(entity.getStatus())) {
//				throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "이미 확정되거나 채택 된 견적 입니다.");
//			}
			log.debug("version => {}", entity.getVersion());

			// 견적 업데이트
			int resultRow = reqQuotationMapper.updateReqQuotation(ReqQuotationEntity.builder()
					.quotationReqNo(quotationId)
					.pickupAddr(updateReqQuotation.getPickupAddr())
					.deliveryAddr(updateReqQuotation.getDeliveryAddr())
					.moveDt(updateReqQuotation.getMoveDt())
					.buildingType(updateReqQuotation.getBuildingType())
					.roomStructure(updateReqQuotation.getRoomStructure())
					.houseSize(updateReqQuotation.getHouseSize())
					.hasElevator(updateReqQuotation.isHasElevator())
					.boxCount(updateReqQuotation.getBoxCount())
					.quotationAmount(updateReqQuotation.getQuotationAmount())
					.cid(0) // 실제 cid 값으로 대체해야 함
					.version(entity.getVersion())
					.build());

			// 업데이트 성공 시
			if (resultRow == 1) {
				return resultRow; // 성공적으로 업데이트 되었을 때
			}
			// 재시도를 위한 로깅
			log.warn("Optimistic locking failure, attempt: {}", attempt + 1);
		}

		// 모든 시도 후 업데이트 실패 시 예외를 발생
		throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "수정 실패: 다른 사용자가 먼저 변경하였습니다.");
	}

	/**
	 * 견적요청서 삭제
	 *
	 * @param quotationId
	 * @return
	 */
	public int deleteReqQuotation(String quotationId) {

		ReqQuotationEntity entity = Optional.ofNullable(reqQuotationMapper.selectReqQuotation(quotationId))
				.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));

		// 견적 상태가 "0"이 아닐 경우 예외를 발생
		QuotationStatus.validDraftStatus(entity.getStatus());

		return reqQuotationMapper.deleteReqQuotation(quotationId);
	}

	/**
	 * 견적요청서 전체 조회
	 *
	 * @return
	 */
	@Cacheable(value="ReqQuotationListCache")
	public List<ReqQuotationDTO> getReqQuotationList() {
		List<ReqQuotationEntity> reqQuotations = reqQuotationMapper.selectAllReqQuotations();
		List<ReqQuotationDTO> reqQuotationList = new ArrayList<>();
		for (ReqQuotationEntity entity : reqQuotations) {
			reqQuotationList.add(ReqQuotationDTO.builder()
					.quotationReqNo(entity.getQuotationReqNo())
					.custId(entity.getCustId())
					.pickupAddr(entity.getPickupAddr())
					.deliveryAddr(entity.getDeliveryAddr())
					.moveDt(entity.getMoveDt())
					.buildingType(entity.getBuildingType())
					.roomStructure(entity.getRoomStructure())
					.houseSize(entity.getHouseSize())
					.hasElevator(entity.isHasElevator())
					.boxCount(entity.getBoxCount())
					.quotationAmount(entity.getQuotationAmount())
					.status(entity.getStatus())
					.build());
		}
		return reqQuotationList;
	}

	/**
	 * 사용자별 견적요청서 조회
	 *
	 * @param customerId
	 * @return
	 */
	@Cacheable(value="ReqQuotationByUser")
	public ReqQuotationDTO getReqQuotationByUser(String customerId) {
		ReqQuotationEntity entity = Optional.ofNullable(reqQuotationMapper.selectReqQuotationByUser(customerId))
				.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));

		return ReqQuotationDTO.builder()
				.quotationReqNo(entity.getQuotationReqNo())
				.quotationDt(entity.getQuotationDt())
				.custId(entity.getCustId())
				.pickupAddr(entity.getPickupAddr())
				.deliveryAddr(entity.getDeliveryAddr())
				.moveDt(entity.getMoveDt())
				.buildingType(entity.getBuildingType())
				.roomStructure(entity.getRoomStructure())
				.houseSize(entity.getHouseSize())
				.hasElevator(entity.isHasElevator())
				.boxCount(entity.getBoxCount())
				.quotationAmount(entity.getQuotationAmount())
				.status(entity.getStatus())
				.version(entity.getVersion())
				.build();
	}

	/**
	 * 견적상태 갱신
	 *
	 * @param reqQuotationId
	 * @param status
	 * @return
	 */
	@Cacheable(value="UpdateReqQuotationStatus")
	public int updateReqQuotationStatus(String reqQuotationId, String status) {

		Optional<ReqQuotationEntity> optionalEntity = Optional
				.ofNullable(reqQuotationMapper.selectReqQuotation(reqQuotationId));
		optionalEntity.orElseThrow(
				() -> new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));

		return reqQuotationMapper.updateReqQuotationStatus(reqQuotationId, status);
	}

}
