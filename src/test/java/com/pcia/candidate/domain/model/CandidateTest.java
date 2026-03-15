package com.pcia.candidate.domain.model;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class CandidateTest {

    @Test
    void should_create_candidate_successfully() {
        CandidateId id = new CandidateId(UUID.randomUUID());
        Candidate candidate = new Candidate(id, "John Doe", "john@example.com");

        assertEquals(id, candidate.getId());
        assertEquals("John Doe", candidate.getFullName());
        assertEquals("john@example.com", candidate.getEmail());
        assertTrue(candidate.getSocialLinks().isEmpty());
    }

    @Test
    void should_update_curriculum() {
        Candidate candidate = new Candidate(CandidateId.generate(), "John Doe", "john@example.com");
        Curriculum curriculum = new Curriculum("cv.pdf", "application/pdf", 1024, "s3://cvs/123");

        candidate.updateCurriculum(curriculum);

        assertEquals(curriculum, candidate.getCurriculum());
    }

    @Test
    void should_add_social_link() {
        Candidate candidate = new Candidate(CandidateId.generate(), "John Doe", "john@example.com");
        SocialLink link = new SocialLink("https://linkedin.com/in/johndoe", SocialLinkType.LINKEDIN);

        candidate.addSocialLink(link);

        assertEquals(1, candidate.getSocialLinks().size());
        assertEquals(link, candidate.getSocialLinks().get(0));
    }

    @Test
    void should_replace_social_link_of_same_type() {
        Candidate candidate = new Candidate(CandidateId.generate(), "John Doe", "john@example.com");
        SocialLink oldLink = new SocialLink("https://linkedin.com/in/old", SocialLinkType.LINKEDIN);
        SocialLink newLink = new SocialLink("https://linkedin.com/in/new", SocialLinkType.LINKEDIN);

        candidate.addSocialLink(oldLink);
        candidate.addSocialLink(newLink);

        assertEquals(1, candidate.getSocialLinks().size());
        assertEquals(newLink, candidate.getSocialLinks().get(0));
    }
}
