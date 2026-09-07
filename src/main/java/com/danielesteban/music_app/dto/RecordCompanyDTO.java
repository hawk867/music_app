package com.danielesteban.music_app.dto;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Pattern;

public class RecordCompanyDTO implements Serializable{

	@Pattern(regexp = ".*records.*", message = "The record have to contains records word")
	private String tittle;
	@JsonIgnoreProperties(value = "recordCompany", allowSetters = true)
	private Set<AlbumDTO> albums;
	
	public RecordCompanyDTO() {}
	
	public RecordCompanyDTO(String tittle) {
		this.tittle = tittle;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public Set<AlbumDTO> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<AlbumDTO> albums) {
		this.albums = albums;
	}

	private static final long serialVersionUID = -830289242848414364L;

}
