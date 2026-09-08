package com.danielesteban.music_app.service;

import com.danielesteban.music_app.DataDummy;
import com.danielesteban.music_app.entity.TrackEntity;
import com.danielesteban.music_app.repository.TrackRepository;
import com.danielesteban.music_app.service.impl.TrackServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TrackServiceTest extends ServiceSpec {

    @InjectMocks
    private TrackServiceImpl trackService;
    @Mock
    private TrackRepository trackRepositoryMock;

    private static final Long INVALID_ID = 2L;
    private static final Long VALID_ID = 1L;

    @BeforeEach
    void setMocks() {
        when(trackRepositoryMock.findById(eq(VALID_ID)))
                .thenReturn(Optional.of(DataDummy.TRACK_1));

        when(trackRepositoryMock.findById(eq(INVALID_ID)))
                .thenReturn(Optional.empty());
    }

    @Test
    @DisplayName("Should return track when id is valid")
    void findByIdTest() {
        assertThrows(NoSuchElementException.class, () -> trackService.findById(INVALID_ID));
        var result = trackService.findById(VALID_ID);
        assertEquals(DataDummy.TRACK_1, result);
        assertThrows(NoSuchElementException.class, () -> trackService.findById(INVALID_ID));
    }

    @Test
    void getAllTest() {
        var expected = Set.of(DataDummy.TRACK_4, DataDummy.TRACK_2);
        when(trackRepositoryMock.findAll())
                .thenReturn(expected);
        var result = trackService.getAll();
        assertEquals(expected, result);
    }

    @Test
    void saveTest() {
        trackService.save(DataDummy.TRACK_3);
        verify(trackRepositoryMock, times(1)).save(any(TrackEntity.class));
    }

    @Test
    void deleteTest() {
        trackService.delete(VALID_ID);
        verify(trackRepositoryMock, times(1)).deleteById(eq(VALID_ID));
    }

    @Test
    void updateTest() {
        when(trackRepositoryMock.existsById(VALID_ID))
                .thenReturn(true);
        when(trackRepositoryMock.existsById(INVALID_ID))
                .thenReturn(false);
        when(trackRepositoryMock.save(any(TrackEntity.class)))
                .thenReturn(DataDummy.TRACK_2);

        var result = trackService.update(DataDummy.TRACK_1, VALID_ID);

        assertEquals(DataDummy.TRACK_2, result);
        assertThrows(NoSuchElementException.class,
                () -> trackService.update(DataDummy.TRACK_1, INVALID_ID));

        verify(trackRepositoryMock, times(2)).existsById(anyLong());
    }
}
