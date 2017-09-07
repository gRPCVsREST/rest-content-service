package org.grpcsvrest.rcs.rest;

import org.grpcsvrest.rcs.service.ContentDto;
import org.grpcsvrest.rcs.service.ContentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("SpringJavaAutowiringInspection")
@RunWith(SpringRunner.class)
@WebMvcTest
public class ContentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ContentService contentService;

    @Test
    public void testContent() throws Exception {
        // given
        when(contentService.getByIndex(0))
                .thenReturn(new ContentDto(0, "content", true));

        // when
        mockMvc.perform(
                get("/content/0")
        ) // then
                .andExpect(status().is(200))
                .andExpect(content().json("{" +
                        "\"id\": 0," +
                        "\"content\": \"content\"," +
                        "\"next\": \"http://localhost:8080/content/1\"" +
                        "}"));

    }

    @Test
    public void testContent_InvalidIndex() throws Exception {
        // given
        when(contentService.getByIndex(0))
                .thenThrow(new IndexOutOfBoundsException("mock exception"));

        // when
        mockMvc.perform(
                get("/content/0")
        ) // then
                .andExpect(status().is(404));

    }

}