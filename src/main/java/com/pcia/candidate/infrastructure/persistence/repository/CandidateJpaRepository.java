package com.pcia.candidate.infrastructure.persistence.repository;

import com.pcia.candidate.infrastructure.persistence.entity.CandidateJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateJpaRepository extends JpaRepository<CandidateJpaEntity, String> {
}
