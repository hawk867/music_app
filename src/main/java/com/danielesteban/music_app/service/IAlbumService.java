package com.danielesteban.music_app.service;

import java.util.Set;

import com.danielesteban.music_app.dto.AlbumDTO;
import com.danielesteban.music_app.dto.TrackDTO;
import com.danielesteban.music_app.service.common.SimpleService;

public interface IAlbumService extends SimpleService<AlbumDTO, Long> {
	
  AlbumDTO addTrack(TrackDTO track, Long id);

  AlbumDTO removeTrack(TrackDTO track, Long id);

  Set<AlbumDTO> findBetweenPrice(Double min, Double max);

}
