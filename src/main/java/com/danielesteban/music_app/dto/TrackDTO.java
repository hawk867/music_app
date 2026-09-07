package com.danielesteban.music_app.dto;

import java.io.Serializable;

import com.danielesteban.music_app.util.JsonUtil;

public class TrackDTO implements Serializable{

	private Long trackId;
	private String name;
	private String lycris;
	private AlbumDTO album;
	
	public TrackDTO() {}

	public TrackDTO(Long trackId, String name, String lycris) {
		this.trackId = trackId;
		this.name = name;
		this.lycris = lycris;
	}

	public Long getTrackId() {
		return trackId;
	}

	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLycris() {
		return lycris;
	}

	public void setLycris(String lycris) {
		this.lycris = lycris;
	}

	public AlbumDTO getAlbum() {
		return album;
	}

	public void setAlbum(AlbumDTO album) {
		this.album = album;
	}
	
	
	@Override
	public String toString() {
		return JsonUtil.toStringJson(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((trackId == null) ? 0 : trackId.hashCode());
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
		TrackDTO other = (TrackDTO) obj;
		if (trackId == null) {
			if (other.trackId != null)
				return false;
		} else if (!trackId.equals(other.trackId))
			return false;
		return true;
	}



	private static final long serialVersionUID = 5585894285878319988L;
	
}
