package com.pcia.candidate.presentation.dto;

import com.pcia.candidate.domain.model.SocialLinkType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SocialLinkRequest {
    @NotBlank(message = "URL is required")
    private String url;

    @NotNull(message = "Social link type is required")
    private SocialLinkType type;
}
