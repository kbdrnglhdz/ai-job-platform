package com.pcia.candidate.infrastructure.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import com.pcia.candidate.domain.model.Candidate;
import com.pcia.candidate.domain.model.CandidateId;
import com.pcia.candidate.domain.model.Curriculum;
import com.pcia.candidate.domain.model.SocialLink;
import com.pcia.candidate.domain.model.SocialLinkType;
import com.pcia.candidate.infrastructure.persistence.adapter.JpaCandidateRepositoryAdapter;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;
import com.pcia.candidate.domain.port.StorageProvider;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CandidatePersistenceIT {

    @Autowired
    private JpaCandidateRepositoryAdapter repositoryAdapter;

    @MockitoBean
    private StorageProvider storageProvider;

    @Test
    void shouldSaveAndFindCandidate() {
        // Given
        CandidateId id = CandidateId.generate();
        Candidate candidate = new Candidate(id, "John Doe", "john.doe@example.com");

        Curriculum curriculum = new Curriculum("cv.pdf", "application/pdf", 1024L, "candidates/cv.pdf");
        candidate.updateCurriculum(curriculum);

        SocialLink linkedin = new SocialLink("https://linkedin.com/in/johndoe", SocialLinkType.LINKEDIN);
        candidate.addSocialLink(linkedin);

        // When
        repositoryAdapter.save(candidate);

        // Then
        Optional<Candidate> found = repositoryAdapter.findById(id);

        assertThat(found).isPresent();
        Candidate savedCandidate = found.get();
        assertThat(savedCandidate.getFullName()).isEqualTo("John Doe");
        assertThat(savedCandidate.getEmail()).isEqualTo("john.doe@example.com");

        assertThat(savedCandidate.getCurriculum()).isNotNull();
        assertThat(savedCandidate.getCurriculum().getFileName()).isEqualTo("cv.pdf");

        assertThat(savedCandidate.getSocialLinks()).hasSize(1);
        assertThat(savedCandidate.getSocialLinks().get(0).getType()).isEqualTo(SocialLinkType.LINKEDIN);
    }
}
