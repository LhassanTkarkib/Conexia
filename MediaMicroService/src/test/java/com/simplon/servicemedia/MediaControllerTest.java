package com.simplon.servicemedia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(MediaController.class)
@ExtendWith(SpringExtension.class)
class MediaControllerTest {

    @MockBean
    private IMedia mediaService;
    @MockBean
    MapperConfig mapperConfig;
    @Autowired
    MockMvc mockMvc;
    private MockMultipartFile file;


    @BeforeEach
    public void setUp() throws Exception {
    }
    @Test
    void getAllMedia() {
    }

    @Test
    void addMedia() throws Exception {
        // Create a mock multipart file
        file = new MockMultipartFile("file", "13.png", MediaType.IMAGE_PNG_VALUE, "Hello, World!".getBytes(StandardCharsets.UTF_8));
        when(mediaService.addMedia(file, 1L)).thenReturn(new MediaDTO());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/media/1")
                        .file(file))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void deleteMedia() {
    }
}