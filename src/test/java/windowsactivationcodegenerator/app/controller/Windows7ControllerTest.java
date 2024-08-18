package windowsactivationcodegenerator.app.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import windowsactivationcodegenerator.app.model.AddWin7CodeRequest;
import windowsactivationcodegenerator.app.model.ApiResponse;
import windowsactivationcodegenerator.app.model.Windows7Response;
import windowsactivationcodegenerator.app.repository.Windows7Repository;

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
}