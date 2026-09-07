package com.danielesteban.music_app.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


import com.danielesteban.music_app.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "album")
public class AlbumEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long albumId;
	private String name;
	private String autor;
	private Double price;
	@JsonIgnoreProperties(value = "albums", allowSetters = true)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tittle")
	private RecordCompanyEntity recordCompany;
	@JsonIgnoreProperties(value = "album", allowSetters = true)
	@OneToMany(mappedBy = "album", cascade = CascadeType.ALL,  orphanRemoval = true)
	private Set<TrackEntity> tracks;
	
	public AlbumEntity() { 
		this.tracks = new HashSet<>();
	}

	public AlbumEntity(Long albumId, String name, String autor, Double price, RecordCompanyEntity recordCompany,
			Set<TrackEntity> tracks) {
		super();
		this.albumId = albumId;
		this.name = name;
		this.autor = autor;
		this.price = price;
		this.recordCompany = recordCompany;
		this.tracks = tracks;
	}



	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public RecordCompanyEntity getRecordCompany() {
		return recordCompany;
	}

	public void setRecordCompany(RecordCompanyEntity recordCompany) {
		this.recordCompany = recordCompany;
	}
	
	public Set<TrackEntity> getTracks() {
		return tracks;
	}

	public void setTracks(Set<TrackEntity> tracks) {
		this.tracks.clear();
		tracks.forEach(this::addTrack);
	}
	
	public void addTrack(TrackEntity track) {
		this.tracks.add(track);
		track.setAlbum(this);
	}

	public void removeTrack(TrackEntity track) {
		this.tracks.remove(track);
		track.setAlbum(this);
	}

	@Override
	public String toString() {
		return JsonUtil.toStringJson(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((albumId == null) ? 0 : albumId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlbumEntity other = (AlbumEntity) obj;
		if (albumId == null) {
			if (other.albumId != null)
				return false;
		} else if (!albumId.equals(other.albumId))
			return false;
		return true;
	}

	private static final long serialVersionUID = -8277318435297709390L;

}
