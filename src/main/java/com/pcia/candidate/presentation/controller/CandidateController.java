package com.pcia.candidate.presentation.controller;

import com.pcia.candidate.application.dto.LinkSocialProfileCommand;
import com.pcia.candidate.application.dto.UploadCurriculumCommand;
import com.pcia.candidate.application.port.in.LinkSocialProfileUseCase;
import com.pcia.candidate.application.port.in.UploadCurriculumUseCase;
import com.pcia.candidate.domain.model.CandidateId;
import com.pcia.candidate.presentation.dto.ApiResponse;
import com.pcia.candidate.presentation.dto.SocialLinkRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Candidate", description = "Candidate management API")
public class CandidateController {

    private final UploadCurriculumUseCase uploadCurriculumUseCase;
    private final LinkSocialProfileUseCase linkSocialProfileUseCase;

    @Operation(summary = "Upload candidate curriculum", description = "Uploads a PDF or Word document as the candidate's curriculum.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Curriculum uploaded successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid file or candidate ID", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @PostMapping(value = "/{id}/curriculums", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Void>> uploadCurriculum(
            @PathVariable @Parameter(description = "Candidate UUID") String id,
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

    @Operation(summary = "Link social profile", description = "Links a social profile (LinkedIn or GitHub) to the candidate.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Social profile linked successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid URL or candidate ID", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @PostMapping("/{id}/social-links")
    public ResponseEntity<ApiResponse<Void>> linkSocialProfile(
            @PathVariable @Parameter(description = "Candidate UUID") String id,
            @Valid @RequestBody SocialLinkRequest request) {

        LinkSocialProfileCommand command = new LinkSocialProfileCommand(
                CandidateId.fromString(id),
                request.getUrl(),
                request.getType());

        linkSocialProfileUseCase.linkSocialProfile(command);

        return ResponseEntity.ok(ApiResponse.success("Social profile linked successfully", null));
    }
}
