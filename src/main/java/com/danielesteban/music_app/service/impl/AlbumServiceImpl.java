package com.danielesteban.music_app.service.impl;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielesteban.music_app.dto.AlbumDTO;
import com.danielesteban.music_app.dto.TrackDTO;
import com.danielesteban.music_app.entity.AlbumEntity;
import com.danielesteban.music_app.entity.RecordCompanyEntity;
import com.danielesteban.music_app.entity.TrackEntity;
import com.danielesteban.music_app.repository.AlbumRepository;
import com.danielesteban.music_app.repository.RecordCompanyRepository;
import com.danielesteban.music_app.repository.TrackRepository;
import com.danielesteban.music_app.service.IAlbumService;
import com.danielesteban.music_app.util.JsonUtil;

@Service
@Transactional
public class AlbumServiceImpl implements IAlbumService {
	
	private static final Logger log = LoggerFactory.getLogger(AlbumServiceImpl.class);

	private final AlbumRepository albumRepository;
	private final TrackRepository trackRepository;
	private final RecordCompanyRepository recordCompanyRepository;

	@Autowired
	public AlbumServiceImpl(
			AlbumRepository albumRpository,
			TrackRepository trackRepository,
			RecordCompanyRepository recordCompanyRepository
			) {
		this.albumRepository = albumRpository;
		this.trackRepository = trackRepository;
		this.recordCompanyRepository = recordCompanyRepository;
	}

	@Override
	public Set<AlbumDTO> getAll() {
		Set<AlbumEntity> response = new HashSet<>();
		this.albumRepository.findAll().forEach(response::add);
		if(response.isEmpty()) {
			throw new NoSuchElementException("No data");
		}
		return response.stream()
				.map(a -> (AlbumDTO) JsonUtil.bodyMapper(a, AlbumDTO.class))
				.collect(Collectors.toSet());
	}

	@Override
	public AlbumDTO save(AlbumDTO album) {
		AlbumEntity toSave = (AlbumEntity) JsonUtil.bodyMapper(album, AlbumEntity.class);
		if(toSave.getRecordCompany() != null) {
			Optional<RecordCompanyEntity> response = this.recordCompanyRepository.findById(toSave.getRecordCompany().getTittle());
			if(response.isPresent()) {
				toSave.setRecordCompany(response.get());
			}
		}
		log.info("save {}", toSave.toString());
		return (AlbumDTO) JsonUtil.bodyMapper(this.albumRepository.save(toSave), AlbumDTO.class);
	}

	@Override
	public AlbumDTO findById(Long id) {
		return (AlbumDTO) JsonUtil.bodyMapper(this.albumRepository.findById(id).orElseThrow(), AlbumDTO.class);
	}

	@Override
	public void delete(Long id) {
		if(this.albumRepository.findById(id).isEmpty()) {
			throw new NoSuchElementException("The id dont exist");
		}
		this.albumRepository.deleteById(id);
	}

	@Override
	public AlbumDTO update(AlbumDTO album, Long id) {
		if(this.albumRepository.findById(id).isEmpty()) {
			throw new NoSuchElementException("The id dont exist");
		}
		AlbumEntity toUpdate = this.albumRepository.findById(id).get();
		toUpdate.setAutor(album.getAutor());
		toUpdate.setName(album.getName());
		toUpdate.setPrice(album.getPrice());
		return (AlbumDTO) JsonUtil.bodyMapper(this.albumRepository.save(toUpdate), AlbumDTO.class);
	}

	@Override
	public Set<AlbumDTO> findBetweenPrice(Double min, Double max) {
		Set<AlbumEntity> response = this.albumRepository.findByPriceBetween(min, max);
		if (response.size() == 0) {
			throw new NoSuchElementException("Not records");
		}
		return response.stream()
				.map(a -> (AlbumDTO) JsonUtil.bodyMapper(a, AlbumDTO.class))
				.collect(Collectors.toSet());
	}

	@Override
	public AlbumDTO addTrack(TrackDTO track, Long id) {
		if(!this.albumRepository.findById(id).isPresent()) {
			throw new NoSuchElementException("The id dont exist");
		}
		AlbumEntity toUpdate = this.albumRepository.findById(id).get();
		toUpdate.addTrack((TrackEntity) JsonUtil.bodyMapper(track, TrackEntity.class));
		return (AlbumDTO) JsonUtil.bodyMapper(this.albumRepository.save(toUpdate), AlbumDTO.class);
	}

	@Override
	public AlbumDTO removeTrack(TrackDTO track, Long id) {
		AlbumEntity toUpdate = this.albumRepository.findById(id).get();

		if(!this.trackRepository.existsById(track.getTrackId())) {
			throw new NoSuchElementException("The track dont exist");
		}
		toUpdate.removeTrack((TrackEntity) JsonUtil.bodyMapper(track, TrackEntity.class));
		this.albumRepository.save(toUpdate);
		return (AlbumDTO) JsonUtil.bodyMapper(this.albumRepository.save(toUpdate), AlbumDTO.class);
	}

}
