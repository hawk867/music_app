package com.danielesteban.music_app.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielesteban.music_app.entity.RecordCompanyEntity;
import com.danielesteban.music_app.repository.RecordCompanyRepository;
import com.danielesteban.music_app.service.IRecordCompanyService;

@Service
@Transactional
public class RecordCompanyImpl implements IRecordCompanyService {
	
	
	private static final Logger log = LoggerFactory.getLogger(RecordCompanyImpl.class);

	private final RecordCompanyRepository repository;

	public RecordCompanyImpl(RecordCompanyRepository repository) {
		this.repository = repository;
	}

	@Override
	public Set<RecordCompanyEntity> getAll() {
		Set<RecordCompanyEntity> response = new HashSet<>();
		this.repository.findAll().forEach(response::add);
		return response;
	}

	@Override
	public RecordCompanyEntity save(RecordCompanyEntity entity) {
		log.info("save {}", entity.toString());
		return this.repository.save(entity);
	}

	@Override
	public RecordCompanyEntity findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RecordCompanyEntity update(RecordCompanyEntity entity, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
