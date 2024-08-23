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
import windowsactivationcodegenerator.app.entity.Windows11;
import windowsactivationcodegenerator.app.model.*;
import windowsactivationcodegenerator.app.repository.Windows10Repository;
import windowsactivationcodegenerator.app.repository.Windows11Repository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Windows11ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Windows11Repository windows11Repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addNewWindows11ActivationCode() throws Exception {

        AddWin11CodeRequest request = new AddWin11CodeRequest();
        request.setActivationCode("test code 1");
        request.setVersion("test version");


        mockMvc.perform(
                post("/api/windows_11/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            ApiResponse<Windows11Response> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response);
            assertNull(response.getErrors());
        });
    }

    @Test
    void updateExistingWindows11ActivationCode() throws Exception {
        Optional<Windows11> windows11 = windows11Repository.findById(16);
        UpdateWin11CodeRequest request = new UpdateWin11CodeRequest();
        request.setActivationCode("test code 2");
        request.setVersion("test version 2");

        mockMvc.perform(
                patch("/api/windows_11/" + windows11.get().getId())
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
            assertEquals(windows11.get().getId(), response.getData().getId());
            assertEquals(request.getActivationCode(), response.getData().getActivationCode());
            assertEquals(request.getVersion(), response.getData().getVersion());
        });
    }

    @Test
    void deleteWindows11ActivationCode() throws Exception {

        Optional<Windows11> windows11 = windows11Repository.findById(16);

        mockMvc.perform(
                delete("/api/windows_11/" + windows11.get().getId())
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