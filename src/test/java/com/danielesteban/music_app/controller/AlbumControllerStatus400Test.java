package com.danielesteban.music_app.controller;

import com.danielesteban.music_app.DataDummy;
import com.danielesteban.music_app.dto.AlbumDTO;
import com.danielesteban.music_app.service.IAlbumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AlbumControllerStatus400Test extends ControllerSpec {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IAlbumService albumServiceMock;

    private ObjectMapper objectMapper;
    private static final Long INVALID_ID = 1L;
    private static final String RESOURCE_PATH = "/v1/album";

    @BeforeEach
    void init() {
        objectMapper = new ObjectMapper();
        when(albumServiceMock.findById(eq(INVALID_ID)))
                .thenThrow(NoSuchElementException.class);
        when(albumServiceMock.save(eq(DataDummy.ALBUM_DTO_INVALID)))
                .thenReturn(DataDummy.ALBUM_DTO_INVALID);
    }

    @Test
    void findByIdTest() throws Exception {
        final var uri = RESOURCE_PATH + "/" + INVALID_ID;
        mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(albumServiceMock).findById(eq(INVALID_ID));
    }

    @Test
    void saveTest() throws Exception {
        when(albumServiceMock.save(any(AlbumDTO.class)))
                .thenReturn(DataDummy.ALBUM_DTO);
        mockMvc.perform(post(RESOURCE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DataDummy.ALBUM_DTO_INVALID)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errors").isMap())
                .andExpect(jsonPath("$.errors.name").value("Must start with Upper"))
                .andExpect(jsonPath("$.errors.autor").value("Must start with Upper"));

        verify(albumServiceMock, times(0)).save(eq(DataDummy.ALBUM_DTO_INVALID));
    }

    @Test
    void updateTest() throws Exception {
        final var uri = RESOURCE_PATH + "/" + INVALID_ID;
        final var albumToUpdate = DataDummy.ALBUM_DTO;
        albumToUpdate.setAutor("Author updated");

        when(albumServiceMock.update(eq(albumToUpdate), eq(INVALID_ID)))
                .thenThrow(NoSuchElementException.class);

        mockMvc.perform(
                        put(uri).contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(albumToUpdate))
                ).andExpect(status().isNotFound());

        verify(albumServiceMock).update(eq(albumToUpdate), eq(INVALID_ID));
    }

    @Test
    void deleteTest() throws Exception {
        final var uri = RESOURCE_PATH + "/" + INVALID_ID;
        doThrow(NoSuchElementException.class)
                .when(albumServiceMock).delete(eq(INVALID_ID));

        mockMvc.perform(delete(uri))
                .andExpect(status().isNotFound());

        verify(albumServiceMock).delete(eq(INVALID_ID));
    }
}
