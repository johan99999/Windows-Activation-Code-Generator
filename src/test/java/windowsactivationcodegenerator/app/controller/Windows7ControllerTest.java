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
import windowsactivationcodegenerator.app.model.AddWin7CodeRequest;
import windowsactivationcodegenerator.app.model.ApiResponse;
import windowsactivationcodegenerator.app.model.UpdateWin7CodeRequest;
import windowsactivationcodegenerator.app.model.Windows7Response;
import windowsactivationcodegenerator.app.repository.Windows7Repository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class Windows7ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Windows7Repository windows7Repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addNewWindows7ActivationCode() throws Exception {

        AddWin7CodeRequest request = new AddWin7CodeRequest();
        request.setActivationCode("test code 1");
        request.setVersion("test version");


        mockMvc.perform(
                post("/api/windows_7/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            ApiResponse<Windows7Response> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response);
            assertNull(response.getErrors());
        });
    }

    @Test
    void updateExistingWindows7ActivationCode() throws Exception {
        Optional<Windows7> windows7 = windows7Repository.findById(232);
        UpdateWin7CodeRequest request = new UpdateWin7CodeRequest();
        request.setActivationCode("test code 2");
        request.setVersion("test version 2");

        mockMvc.perform(
                patch("/api/windows_7/" + windows7.get().getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            ApiResponse<Windows7Response> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response);
            assertNull(response.getErrors());
            assertEquals(windows7.get().getId(), response.getData().getId());
            assertEquals(request.getActivationCode(), response.getData().getActivationCode());
            assertEquals(request.getVersion(), response.getData().getVersion());
        });
    }
}