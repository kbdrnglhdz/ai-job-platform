package com.pcia.candidate.application.service;

import com.pcia.candidate.application.dto.LinkSocialProfileCommand;
import com.pcia.candidate.application.port.in.LinkSocialProfileUseCase;
import com.pcia.candidate.domain.model.Candidate;
import com.pcia.candidate.domain.model.SocialLink;
import com.pcia.candidate.domain.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LinkSocialProfileService implements LinkSocialProfileUseCase {

    private final CandidateRepository candidateRepository;

    @Override
    @Transactional
    public void linkSocialProfile(LinkSocialProfileCommand command) {
        Candidate candidate = candidateRepository.findById(command.getCandidateId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Candidate with ID " + command.getCandidateId().getValue() + " not found"));

        SocialLink socialLink = new SocialLink(command.getUrl(), command.getType());
        candidate.addSocialLink(socialLink);

        candidateRepository.save(candidate);
    }
}
