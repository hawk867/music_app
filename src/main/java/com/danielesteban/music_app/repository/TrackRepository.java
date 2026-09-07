package com.danielesteban.music_app.repository;

import com.danielesteban.music_app.entity.TrackEntity;
import org.springframework.data.repository.CrudRepository;

public interface TrackRepository extends CrudRepository<TrackEntity, Long>{

}
