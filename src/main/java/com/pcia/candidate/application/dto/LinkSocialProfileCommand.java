package com.pcia.candidate.application.dto;

import com.pcia.candidate.domain.model.CandidateId;
import com.pcia.candidate.domain.model.SocialLinkType;
import lombok.Value;

@Value
public class LinkSocialProfileCommand {
    CandidateId candidateId;
    String url;
    SocialLinkType type;
}
