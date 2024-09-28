package com.study.jimcarry.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.jimcarry.domain.ConfirmQuotationEntity;
import com.study.jimcarry.exception.CustomException;
import com.study.jimcarry.exception.ErrorCode;
import com.study.jimcarry.mapper.ConfirmQuotationMapper;
import com.study.jimcarry.model.ConfirmQuotation;
import com.study.jimcarry.service.ConfirmQuotationService;

@Service
public class ConfirmQuotationServiceImpl implements ConfirmQuotationService {

	@Autowired
	ConfirmQuotationMapper confirmQuotationMapper;
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public int saveConfirmQuotation(ConfirmQuotationEntity confirmQuotationEntity) {
		return confirmQuotationMapper.insertConfirmQuotation(confirmQuotationEntity);
	}

	@Override
	public int modifyConfrimQuotation(ConfirmQuotationEntity confirmQuotationEntity) {
		ConfirmQuotationEntity findConfirmQuotation = confirmQuotationMapper.selectConfrimQuotation(confirmQuotationEntity.getReqQuotationId());
		if(findConfirmQuotation == null) {
			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
		}
		return confirmQuotationMapper.updateConfirmQuotation(confirmQuotationEntity);
	}

	@Override
	public int deleteConfirmQuotation(String reqQuotationId) {
		return confirmQuotationMapper.deleteConfirmQuotation(reqQuotationId);
	}

	@Override
	public List<ConfirmQuotation> getConfirmQuotationListByDriver(String driverId) {
		List<ConfirmQuotationEntity> findList = confirmQuotationMapper.selectConfirmQuotationByDriver(driverId);
		List<ConfirmQuotation> confirmQuotationList = new ArrayList<>();
		for(ConfirmQuotationEntity entity : findList) {
			ConfirmQuotation confirmQuotation = modelMapper.map(entity, ConfirmQuotation.class);
			confirmQuotationList.add(confirmQuotation);
		}
		return confirmQuotationList;
	}

	@Override
	public ConfirmQuotation getConfirmQuotationByUser(String customerId) {
		ConfirmQuotationEntity entity = confirmQuotationMapper.selectConfirmQuotationByUser(customerId);
		if(entity == null) {
			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
		}
		ConfirmQuotation confirmQuotation = modelMapper.map(entity, ConfirmQuotation.class);
		return confirmQuotation;
	}
	
}
