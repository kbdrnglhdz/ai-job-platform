package com.pcia.candidate.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcia.candidate.application.port.in.LinkSocialProfileUseCase;
import com.pcia.candidate.application.port.in.UploadCurriculumUseCase;
import com.pcia.candidate.domain.model.CandidateId;
import com.pcia.candidate.domain.model.SocialLinkType;
import com.pcia.candidate.presentation.dto.SocialLinkRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CandidateController.class)
class CandidateControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UploadCurriculumUseCase uploadCurriculumUseCase;

    @MockitoBean
    private LinkSocialProfileUseCase linkSocialProfileUseCase;

    @Test
    void shouldUploadCurriculum() throws Exception {
        CandidateId id = CandidateId.generate();
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "resume.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                "%PDF-1.4".getBytes());

        doNothing().when(uploadCurriculumUseCase).uploadCurriculum(any());

        mockMvc.perform(multipart("/api/v1/candidates/{id}/curriculums", id.getValue().toString())
                .file(file))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Curriculum uploaded successfully"));
    }

    @Test
    void shouldLinkSocialProfile() throws Exception {
        CandidateId id = CandidateId.generate();
        SocialLinkRequest request = new SocialLinkRequest();
        request.setUrl("https://linkedin.com/in/johndoe");
        request.setType(SocialLinkType.LINKEDIN);

        doNothing().when(linkSocialProfileUseCase).linkSocialProfile(any());

        mockMvc.perform(post("/api/v1/candidates/{id}/social-links", id.getValue().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Social profile linked successfully"));
    }

    @Test
    void shouldReturnBadRequestWhenSocialLinkInvalid() throws Exception {
        CandidateId id = CandidateId.generate();
        SocialLinkRequest request = new SocialLinkRequest();
        request.setUrl("invalid-url");
        request.setType(SocialLinkType.LINKEDIN);

        doThrow(new IllegalArgumentException("Invalid social link URL format"))
                .when(linkSocialProfileUseCase).linkSocialProfile(any());

        mockMvc.perform(post("/api/v1/candidates/{id}/social-links", id.getValue().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Invalid social link URL format"));
    }

    @Test
    void shouldReturnBadRequestWhenSocialLinkTypeMissing() throws Exception {
        CandidateId id = CandidateId.generate();
        SocialLinkRequest request = new SocialLinkRequest();
        request.setUrl("https://linkedin.com/in/johndoe");
        request.setType(null); // Triggers MethodArgumentNotValidException

        mockMvc.perform(post("/api/v1/candidates/{id}/social-links", id.getValue().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("Validation failed")));
    }

    @Test
    void shouldReturnInternalServerErrorOnUnexpectedException() throws Exception {
        CandidateId id = CandidateId.generate();
        SocialLinkRequest request = new SocialLinkRequest();
        request.setUrl("https://linkedin.com/in/johndoe");
        request.setType(SocialLinkType.LINKEDIN);

        doThrow(new RuntimeException("Unexpected error"))
                .when(linkSocialProfileUseCase).linkSocialProfile(any());

        mockMvc.perform(post("/api/v1/candidates/{id}/social-links", id.getValue().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("An unexpected error occurred. Please try again later."));
    }
}
