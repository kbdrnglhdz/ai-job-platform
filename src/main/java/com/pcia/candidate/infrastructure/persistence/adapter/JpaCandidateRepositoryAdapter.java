package com.pcia.candidate.infrastructure.persistence.adapter;

import com.pcia.candidate.domain.model.Candidate;
import com.pcia.candidate.domain.model.CandidateId;
import com.pcia.candidate.domain.repository.CandidateRepository;
import com.pcia.candidate.infrastructure.persistence.entity.CandidateJpaEntity;
import com.pcia.candidate.infrastructure.persistence.mapper.CandidateMapper;
import com.pcia.candidate.infrastructure.persistence.repository.CandidateJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaCandidateRepositoryAdapter implements CandidateRepository {

    private final CandidateJpaRepository jpaRepository;
    private final CandidateMapper mapper;

    @Override
    public void save(Candidate candidate) {
        CandidateJpaEntity entity = mapper.toEntity(candidate);
        // Ensure bidirectional relationships are set if not handled by mapper
        if (entity.getCurriculum() != null) {
            entity.getCurriculum().setCandidate(entity);
        }
        entity.getSocialLinks().forEach(link -> link.setCandidate(entity));

        jpaRepository.save(entity);
    }

    @Override
    public Optional<Candidate> findById(CandidateId id) {
        return jpaRepository.findById(id.getValue().toString())
                .map(mapper::toDomain);
    }
}
