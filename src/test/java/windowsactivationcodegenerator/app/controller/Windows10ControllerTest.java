package windowsactivationcodegenerator.app.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import windowsactivationcodegenerator.app.entity.Windows10;
import windowsactivationcodegenerator.app.entity.Windows8;
import windowsactivationcodegenerator.app.model.*;
import windowsactivationcodegenerator.app.repository.Windows10Repository;
import windowsactivationcodegenerator.app.repository.Windows8Repository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Windows10ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Windows10Repository windows10Repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addNewWindows10ActivationCode() throws Exception {

        AddWin10CodeRequest request = new AddWin10CodeRequest();
        request.setActivationCode("test code 1");
        request.setVersion("test version");


        mockMvc.perform(
                post("/api/windows_10/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            ApiResponse<Windows10Response> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response);
            assertNull(response.getErrors());
        });
    }

    @Test
    void updateExistingWindows10ActivationCode() throws Exception {
        Optional<Windows10> windows10 = windows10Repository.findById(44);
        UpdateWin10CodeRequest request = new UpdateWin10CodeRequest();
        request.setActivationCode("test code 2");
        request.setVersion("test version 2");

        mockMvc.perform(
                patch("/api/windows_10/" + windows10.get().getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            ApiResponse<Windows10Response> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response);
            assertNull(response.getErrors());
            assertEquals(windows10.get().getId(), response.getData().getId());
            assertEquals(request.getActivationCode(), response.getData().getActivationCode());
            assertEquals(request.getVersion(), response.getData().getVersion());
        });
    }

    @Test
    void deleteWindows10ActivationCode() throws Exception {

        Optional<Windows10> windows10 = windows10Repository.findById(44);

        mockMvc.perform(
                delete("/api/windows_10/" + windows10.get().getId())
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