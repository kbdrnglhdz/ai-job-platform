package com.pcia.candidate.application.service;

import com.pcia.candidate.application.dto.LinkSocialProfileCommand;
import com.pcia.candidate.domain.model.Candidate;
import com.pcia.candidate.domain.model.CandidateId;
import com.pcia.candidate.domain.model.SocialLinkType;
import com.pcia.candidate.domain.repository.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LinkSocialProfileServiceTest {

    @Mock
    private CandidateRepository candidateRepository;

    @InjectMocks
    private LinkSocialProfileService linkSocialProfileService;

    private Candidate candidate;
    private CandidateId candidateId;

    @BeforeEach
    void setUp() {
        candidateId = CandidateId.generate();
        candidate = new Candidate(candidateId, "John Doe", "john@example.com");
    }

    @Test
    void shouldLinkSocialProfileSuccessfully() {
        // Arrange
        LinkSocialProfileCommand command = new LinkSocialProfileCommand(
                candidateId,
                "https://linkedin.com/in/johndoe",
                SocialLinkType.LINKEDIN);
        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));

        // Act
        linkSocialProfileService.linkSocialProfile(command);

        // Assert
        assertEquals(1, candidate.getSocialLinks().size());
        assertEquals("https://linkedin.com/in/johndoe", candidate.getSocialLinks().get(0).getUrl());
        verify(candidateRepository, times(1)).save(candidate);
    }

    @Test
    void shouldThrowExceptionWhenCandidateNotFound() {
        // Arrange
        LinkSocialProfileCommand command = new LinkSocialProfileCommand(
                candidateId,
                "https://linkedin.com/in/johndoe",
                SocialLinkType.LINKEDIN);
        when(candidateRepository.findById(candidateId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> linkSocialProfileService.linkSocialProfile(command));
    }
}
