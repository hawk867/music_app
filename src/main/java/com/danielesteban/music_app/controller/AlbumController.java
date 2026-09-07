package com.danielesteban.music_app.controller;

import com.danielesteban.music_app.dto.AlbumDTO;
import com.danielesteban.music_app.dto.TrackDTO;
import com.danielesteban.music_app.service.IAlbumService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping(path = "v1/album")
public class AlbumController {

	private final IAlbumService service;

	public AlbumController(IAlbumService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<Set<AlbumDTO>> getAll() {
		return ResponseEntity.ok().body(this.service.getAll());
	}
	
	@PostMapping
	public ResponseEntity<AlbumDTO> save(@Valid @RequestBody AlbumDTO album) {
		return ResponseEntity.created(URI.create("v1/album/" + this.service.save(album).getAlbumId())).build();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<AlbumDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(this.service.findById(id));
	}
	
	@PutMapping("{id}")
	public  ResponseEntity<AlbumDTO> update(@RequestBody AlbumDTO album, @PathVariable Long id) {
		return ResponseEntity.ok().body(this.service.update(album, id));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<AlbumDTO> delete(@PathVariable Long id) {
		this.service.delete(id);
		return ResponseEntity.notFound().build();
	}
	
	@PatchMapping("add/{id}")
	public  ResponseEntity<AlbumDTO> addTrack(@RequestBody TrackDTO track, @PathVariable Long id) {
		return ResponseEntity.ok().body(this.service.addTrack(track, id));
	}
	
	@PatchMapping("remove/{id}")
	public  ResponseEntity<AlbumDTO> removeTrack(@RequestBody TrackDTO track, @PathVariable Long id) {
		return ResponseEntity.ok().body(this.service.removeTrack(track, id));
	}
	
	@GetMapping("between")
	public ResponseEntity<Set<AlbumDTO>> fondBetweenPrice(@RequestParam Double min, @RequestParam Double max) {
			return ResponseEntity.ok().body(this.service.findBetweenPrice(min, max));
	
	}
	
}
