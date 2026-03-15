package com.pcia.candidate.presentation.controller;

import com.pcia.candidate.application.dto.LinkSocialProfileCommand;
import com.pcia.candidate.application.dto.UploadCurriculumCommand;
import com.pcia.candidate.application.port.in.LinkSocialProfileUseCase;
import com.pcia.candidate.application.port.in.UploadCurriculumUseCase;
import com.pcia.candidate.domain.model.CandidateId;
import com.pcia.candidate.presentation.dto.ApiResponse;
import com.pcia.candidate.presentation.dto.SocialLinkRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final UploadCurriculumUseCase uploadCurriculumUseCase;
    private final LinkSocialProfileUseCase linkSocialProfileUseCase;

    @PostMapping(value = "/{id}/curriculums", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Void>> uploadCurriculum(
            @PathVariable String id,
            @RequestParam("file") MultipartFile file) throws IOException {

        UploadCurriculumCommand command = new UploadCurriculumCommand(
                CandidateId.fromString(id),
                file.getInputStream(),
                file.getOriginalFilename(),
                file.getContentType(),
                file.getSize());

        uploadCurriculumUseCase.uploadCurriculum(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Curriculum uploaded successfully", null));
    }

    @PostMapping("/{id}/social-links")
    public ResponseEntity<ApiResponse<Void>> linkSocialProfile(
            @PathVariable String id,
            @Valid @RequestBody SocialLinkRequest request) {

        LinkSocialProfileCommand command = new LinkSocialProfileCommand(
                CandidateId.fromString(id),
                request.getUrl(),
                request.getType());

        linkSocialProfileUseCase.linkSocialProfile(command);

        return ResponseEntity.ok(ApiResponse.success("Social profile linked successfully", null));
    }
}
