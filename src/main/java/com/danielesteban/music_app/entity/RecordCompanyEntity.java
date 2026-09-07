package com.danielesteban.music_app.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.danielesteban.music_app.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "record_company")
public class RecordCompanyEntity  implements Serializable {
	@Id
	private String tittle;
	@JsonIgnoreProperties(value = "recordCompany", allowSetters = true)
	@OneToMany(mappedBy = "recordCompany")
	private Set<AlbumEntity> albums;
	
	public RecordCompanyEntity() {
		this.albums = new HashSet<>();
	}

	public RecordCompanyEntity(String tittle) {
		super();
		this.tittle = tittle;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public Set<AlbumEntity> getAlbums() {
		return albums;
	}
	
	public void setAlbums(Set<AlbumEntity> albums) {
		this.albums.clear();
		albums.forEach(this::addAlbum);
	}

	public void addAlbum(AlbumEntity album) {
		this.albums = new HashSet<>();
		this.albums.add(album);
	}
	
	@Override
	public String toString() {
		return JsonUtil.toStringJson(this);
	}

	private static final long serialVersionUID = -8941398747644265559L;

}
