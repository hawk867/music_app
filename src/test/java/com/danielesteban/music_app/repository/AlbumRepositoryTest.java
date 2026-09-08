package com.danielesteban.music_app.repository;

import com.danielesteban.music_app.DataDummy;
import com.danielesteban.music_app.entity.AlbumEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlbumRepositoryTest extends RepositorySpec {

    @Autowired
    private AlbumRepository albumRepository;
    private static final Long ALBUM_ID = 100L;
    private static final Long INVALID_ALBUM_ID = 999L;

    @Test
    void findByIdTest() {
        var result = albumRepository.findById(ALBUM_ID);
        assertTrue(result.isPresent());
        assertAll(() -> {
            assertEquals("fear of the dark", result.get().getName());
            assertEquals(280.50, result.get().getPrice());
        });
    }

    @Test
    void findByInvalidIdTest() {
        var result = albumRepository.findById(INVALID_ALBUM_ID);
        assertTrue(result.isEmpty());
    }

    @Test
    void findAllTest() {
        var result = (List<AlbumEntity>) albumRepository.findAll();
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    void saveTest() {
        var result = albumRepository.save(DataDummy.ALBUM);
        assertEquals(1L, result.getAlbumId());
    }

    @Test
    void findBetweenPriceTest() {
        var result = albumRepository.findByPriceBetween(270.0, 275.0);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void deleteByIdTest() {
        albumRepository.deleteById(ALBUM_ID);
        var result = albumRepository.findById(ALBUM_ID);
        assertTrue(result.isEmpty());
    }
}
