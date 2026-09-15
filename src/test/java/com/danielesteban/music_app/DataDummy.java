package com.danielesteban.music_app;

import com.danielesteban.music_app.dto.AlbumDTO;
import com.danielesteban.music_app.dto.RecordCompanyDTO;
import com.danielesteban.music_app.dto.TrackDTO;
import com.danielesteban.music_app.entity.AlbumEntity;
import com.danielesteban.music_app.entity.RecordCompanyEntity;
import com.danielesteban.music_app.entity.TrackEntity;

import java.util.Arrays;
import java.util.HashSet;

public class DataDummy {

    public static final RecordCompanyEntity RECORD_COMPANY;
    public static final TrackEntity TRACK_1;
    public static final TrackEntity TRACK_2;
    public static final TrackEntity TRACK_3;
    public static final TrackEntity TRACK_4;
    public static final TrackEntity TRACK_5;
    public static final AlbumEntity ALBUM;
    public static final RecordCompanyDTO RECORD_COMPANY_DTO;
    public static final TrackDTO TRACK_1_DTO;
    public static final TrackDTO TRACK_2_DTO;
    public static final TrackDTO TRACK_3_DTO;
    public static final TrackDTO TRACK_4_DTO;
    public static final TrackDTO TRACK_5_DTO;
    public static final AlbumDTO ALBUM_DTO;
    public static final AlbumDTO ALBUM_DTO_INVALID;

    static {
        TRACK_1 = new TrackEntity(1L, "test-1", "lycris-1");
        TRACK_2 = new TrackEntity(2L, "test-2", "lycris-2");
        TRACK_3 = new TrackEntity(3L, "test-3", "lycris-3");
        TRACK_4 = new TrackEntity(4L, "test-4", "lycris-4");
        TRACK_5 = new TrackEntity(5L, "test-5", "lycris-5");

        RECORD_COMPANY = new RecordCompanyEntity("test-comany-records");
        ALBUM = new AlbumEntity(
                1L,
                "Album-test",
                "Actor-test",
                20.20,
                RECORD_COMPANY,
                new HashSet<>(Arrays.asList(TRACK_1, TRACK_2, TRACK_3, TRACK_4)));

        TRACK_1_DTO = new TrackDTO(1L, "test-1", "lycris-1");
        TRACK_2_DTO = new TrackDTO(2L, "test-2", "lycris-2");
        TRACK_3_DTO = new TrackDTO(3L, "test-3", "lycris-3");
        TRACK_4_DTO = new TrackDTO(4L, "test-4", "lycris-4");
        TRACK_5_DTO = new TrackDTO(5L, "test-5", "lycris-5");

        RECORD_COMPANY_DTO = new RecordCompanyDTO("test-comany-records");
        ALBUM_DTO = new AlbumDTO(
                1L,
                "Album-test",
                "Actor-test",
                20.20,
                RECORD_COMPANY_DTO,
                new HashSet<>(Arrays.asList(TRACK_1_DTO, TRACK_2_DTO, TRACK_3_DTO, TRACK_4_DTO)));

        ALBUM_DTO_INVALID = new AlbumDTO(
                1L,
                "album-test",
                "actor-test",
                20.20,
                RECORD_COMPANY_DTO,
                new HashSet<>(Arrays.asList(TRACK_1_DTO, TRACK_2_DTO, TRACK_3_DTO, TRACK_4_DTO)));
    }
}
