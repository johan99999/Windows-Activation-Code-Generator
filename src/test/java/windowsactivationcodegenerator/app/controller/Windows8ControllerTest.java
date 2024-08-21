package windowsactivationcodegenerator.app.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import windowsactivationcodegenerator.app.entity.Windows7;
import windowsactivationcodegenerator.app.entity.Windows8;
import windowsactivationcodegenerator.app.model.*;
import windowsactivationcodegenerator.app.repository.Windows7Repository;
import windowsactivationcodegenerator.app.repository.Windows8Repository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Windows8ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Windows8Repository windows8Repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addNewWindows8ActivationCode() throws Exception {

        AddWin8CodeRequest request = new AddWin8CodeRequest();
        request.setActivationCode("test code 1");
        request.setVersion("test version");


        mockMvc.perform(
                post("/api/windows_8/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            ApiResponse<Windows8Response> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response);
            assertNull(response.getErrors());
        });
    }

    @Test
    void updateExistingWindows8ActivationCode() throws Exception {
        Optional<Windows8> windows8 = windows8Repository.findById(7);
        UpdateWin7CodeRequest request = new UpdateWin7CodeRequest();
        request.setActivationCode("test code 2");
        request.setVersion("test version 2");

        mockMvc.perform(
                patch("/api/windows_8/" + windows8.get().getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            ApiResponse<Windows8Response> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response);
            assertNull(response.getErrors());
            assertEquals(windows8.get().getId(), response.getData().getId());
            assertEquals(request.getActivationCode(), response.getData().getActivationCode());
            assertEquals(request.getVersion(), response.getData().getVersion());
        });
    }

    @Test
    void deleteWindows7ActivationCode() throws Exception {

        Optional<Windows8> windows8 = windows8Repository.findById(7);

        mockMvc.perform(
                delete("/api/windows_8/" + windows8.get().getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            ApiResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals("OK", response.getData());
        });
    }
}