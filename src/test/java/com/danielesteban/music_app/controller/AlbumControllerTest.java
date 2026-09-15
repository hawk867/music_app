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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AlbumControllerTest extends ControllerSpec {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IAlbumService albumServiceMock;

    private ObjectMapper objectMapper;
    private static final Long VALID_ID = 1L;
    private static final String RESOURCE_PATH = "/v1/album";

    @BeforeEach
    void init() {
        objectMapper = new ObjectMapper();
        when(albumServiceMock.findById(eq(VALID_ID)))
                .thenReturn(DataDummy.ALBUM_DTO);
        when(albumServiceMock.save(eq(DataDummy.ALBUM_DTO)))
                .thenReturn(DataDummy.ALBUM_DTO);
    }

    @Test
    void findByIdTest() throws Exception {
        final var uri = RESOURCE_PATH + "/" + VALID_ID;
        mockMvc
                .perform(get(uri).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(DataDummy.ALBUM_DTO.getName()))
                .andExpect(jsonPath("$.autor").value(DataDummy.ALBUM_DTO.getAutor()));

        verify(albumServiceMock).findById(eq(VALID_ID));
    }

    @Test
    void saveTest() throws Exception {
        when(albumServiceMock.save(any(AlbumDTO.class)))
                .thenReturn(DataDummy.ALBUM_DTO);
        mockMvc.perform(post(RESOURCE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DataDummy.ALBUM_DTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateTest() throws Exception {
        final var uri = RESOURCE_PATH + "/" + VALID_ID;
        final var albumToUpdate = DataDummy.ALBUM_DTO;
        albumToUpdate.setAutor("Author updated");

        when(albumServiceMock.update(eq(albumToUpdate), eq(VALID_ID)))
                .thenReturn(albumToUpdate);

        mockMvc.perform(
                        put(uri).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(albumToUpdate))
                ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.autor").value("Author updated"));

        verify(albumServiceMock).update(eq(albumToUpdate), eq(VALID_ID));
    }

    @Test
    void deleteTest() throws Exception {
        final var uri = RESOURCE_PATH + "/" + VALID_ID;
        mockMvc.perform(delete(uri))
                .andExpect(status().isNoContent());

        verify(albumServiceMock).delete(eq(VALID_ID));
    }
}
