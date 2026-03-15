package com.pcia.candidate.domain.repository;

import com.pcia.candidate.domain.model.Candidate;
import com.pcia.candidate.domain.model.CandidateId;
import java.util.Optional;

public interface CandidateRepository {
    void save(Candidate candidate);

    Optional<Candidate> findById(CandidateId id);
}
