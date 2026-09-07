package com.danielesteban.music_app.controller;

import com.danielesteban.music_app.entity.TrackEntity;
import com.danielesteban.music_app.service.ITrackService;
import com.danielesteban.music_app.util.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping(path = "v1/track")
public class TrackController {

	private final ITrackService service;

	public TrackController(ITrackService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<Set<TrackEntity>> getAll(){
		return ResponseEntity.ok().body(this.service.getAll());
	}
	
	@PostMapping
	public ResponseEntity<TrackEntity> save(@RequestBody TrackEntity track) {
		return ResponseEntity.ok().body(this.service.save(track));
	}

	@PutMapping("{id}")
	public  ResponseEntity<?> update(@RequestBody TrackEntity entity, @PathVariable Long id) {

		try {
			return ResponseEntity.ok().body(this.service.update(entity, id));
		} catch (NoSuchElementException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ResponseModel(LocalDateTime.now(), null, e.getMessage()));
		}
	}
	
}
