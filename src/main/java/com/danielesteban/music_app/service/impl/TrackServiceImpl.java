package com.danielesteban.music_app.service.impl;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielesteban.music_app.entity.TrackEntity;
import com.danielesteban.music_app.repository.TrackRepository;
import com.danielesteban.music_app.service.ITrackService;

@Service
@Transactional
public class TrackServiceImpl implements ITrackService {
	
	
	private static final Logger log = LoggerFactory.getLogger(TrackServiceImpl.class);

	private final TrackRepository repository;

	public TrackServiceImpl(TrackRepository repository) {
		this.repository = repository;
	}

	@Override
	public Set<TrackEntity> getAll() {
		Set<TrackEntity> response = new HashSet<>();
		this.repository.findAll().forEach(response::add);
		return response;
	}

	@Override
	public TrackEntity save(TrackEntity entity) {
		log.info("save {}", entity.toString());
		return this.repository.save(entity);
	}

	@Override
	public TrackEntity findById(Long id) {
		return this.repository.findById(id).get();
	}

	@Override
	public void delete(Long entity) {
		this.repository.deleteById(entity);
		
	}

	@Override
	public TrackEntity update(TrackEntity entity, Long id) {
		if(!this.repository.existsById(id)) {
			throw new NoSuchElementException("Id not found");
		}
		TrackEntity toUpdate = this.repository.findById(id).get();
		toUpdate.setLycris(entity.getLycris());
		toUpdate.setName(entity.getName());
		return this.repository.save(toUpdate);
	}

}
