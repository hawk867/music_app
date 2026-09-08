package com.danielesteban.music_app.service;

import com.danielesteban.music_app.DataDummy;
import com.danielesteban.music_app.entity.AlbumEntity;
import com.danielesteban.music_app.repository.AlbumRepository;
import com.danielesteban.music_app.repository.RecordCompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumServiceTest extends ServiceSpec {

    @Autowired
    private IAlbumService albumService;
    @MockBean
    private AlbumRepository albumRepositoryMock;
    @MockBean private RecordCompanyRepository recordCompanyRepositoryMock;

    private static final Long DEFAULT_ALBUM_ID = 1L;
    private static final Long INVALID_ALBUM_ID = 0L;

    @BeforeEach
    void setUp() {
        when(albumRepositoryMock.findById(DEFAULT_ALBUM_ID))
                .thenReturn(Optional.of(DataDummy.ALBUM));
        when(albumRepositoryMock.findById(INVALID_ALBUM_ID))
                .thenReturn(Optional.empty());
    }

    @AfterEach
    void resetMocks() {
        reset(albumRepositoryMock);
    }

    @Test
    void findByIdTest() {
        var result = albumService.findById(DEFAULT_ALBUM_ID);
        verify(albumRepositoryMock, times(1)).findById(eq(DEFAULT_ALBUM_ID));
        assertEquals(DataDummy.ALBUM_DTO, result);

        assertThrows(NoSuchElementException.class,
                () -> {
                    albumService.findById(INVALID_ALBUM_ID);
                    verify(albumRepositoryMock, times(1)).findById(eq(INVALID_ALBUM_ID));
                }
        );
    }

    @Test
    void getAllTest() {
        when(albumRepositoryMock.findAll()).thenReturn(Collections.emptyList());

        assertThrows(NoSuchElementException.class,
                () -> {
                    albumService.getAll();
                    verify(albumRepositoryMock, times(1)).findAll();
                }
        );

        when(albumRepositoryMock.findAll()).thenReturn(List.of(DataDummy.ALBUM));

        var result = albumService.getAll();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

        verify(albumRepositoryMock, times(2)).findAll();
    }

    @Test
    void saveTest() {
        when(recordCompanyRepositoryMock.findById(anyString()))
                .thenReturn(Optional.of(DataDummy.RECORD_COMPANY));
        when(albumRepositoryMock.save(any(AlbumEntity.class)))
                .thenReturn(DataDummy.ALBUM);

        var result = albumService.save(DataDummy.ALBUM_DTO);

        assertEquals(DataDummy.ALBUM_DTO, result);
        verify(recordCompanyRepositoryMock, times(1)).findById(anyString());
        verify(albumRepositoryMock, times(1)).save(any(AlbumEntity.class));
    }

    @Test
    void deleteTest() {
        albumService.delete(DEFAULT_ALBUM_ID);
        verify(albumRepositoryMock, times(1)).deleteById(eq(DEFAULT_ALBUM_ID));

        assertThrows(NoSuchElementException.class,
                () -> {
                    albumService.delete(INVALID_ALBUM_ID);
                    verify(albumRepositoryMock, times(1)).deleteById(eq(DEFAULT_ALBUM_ID));
                });
    }

    @Test
    void updateTest() {
        when(recordCompanyRepositoryMock.findById(anyString()))
                .thenReturn(Optional.of(DataDummy.RECORD_COMPANY));
        when(albumRepositoryMock.save(any(AlbumEntity.class)))
                .thenReturn(DataDummy.ALBUM);

        var result = albumService.update(DataDummy.ALBUM_DTO, DEFAULT_ALBUM_ID);
        assertEquals(DataDummy.ALBUM_DTO, result);
        verify(albumRepositoryMock, times(1)).save(any(AlbumEntity.class));
    }

    @Test
    void findBetweenPriceTest() {
        when(albumRepositoryMock.findByPriceBetween(anyDouble(), anyDouble()))
                .thenReturn(Set.of(DataDummy.ALBUM));

        var result = albumService.findBetweenPrice(10.0, 100.0);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void addTrackTest() {
        assertThrows(NoSuchElementException.class,
                () -> {
                    albumService.addTrack(DataDummy.TRACK_1_DTO, INVALID_ALBUM_ID);
                        verify(albumRepositoryMock, times(1)).findById(eq(INVALID_ALBUM_ID));
                });
    }
}
