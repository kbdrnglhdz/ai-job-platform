package com.pcia.candidate.infrastructure.persistence.mapper;

import com.pcia.candidate.domain.model.Candidate;
import com.pcia.candidate.domain.model.CandidateId;
import com.pcia.candidate.domain.model.Curriculum;
import com.pcia.candidate.domain.model.SocialLink;
import com.pcia.candidate.domain.model.SocialLinkType;
import com.pcia.candidate.infrastructure.persistence.entity.CandidateJpaEntity;
import com.pcia.candidate.infrastructure.persistence.entity.CurriculumJpaEntity;
import com.pcia.candidate.infrastructure.persistence.entity.SocialLinkJpaEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface CandidateMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "candidateIdToString")
    @Mapping(target = "socialLinks", source = "socialLinks")
    CandidateJpaEntity toEntity(Candidate candidate);

    @Mapping(target = "id", source = "id", qualifiedByName = "stringToCandidateId")
    @Mapping(target = "socialLinks", source = "socialLinks")
    @Mapping(target = "curriculum", ignore = true) // Handled in AfterMapping
    Candidate toDomain(CandidateJpaEntity entity);

    @org.mapstruct.AfterMapping
    default void mapCurriculum(CandidateJpaEntity entity, @org.mapstruct.MappingTarget Candidate candidate) {
        if (entity.getCurriculum() != null) {
            candidate.updateCurriculum(toDomain(entity.getCurriculum()));
        }
    }

    @Mapping(target = "candidate", ignore = true)
    @Mapping(target = "candidateId", ignore = true)
    CurriculumJpaEntity toEntity(Curriculum curriculum);

    Curriculum toDomain(CurriculumJpaEntity entity);

    @Mapping(target = "candidate", ignore = true)
    @Mapping(target = "candidateId", ignore = true)
    SocialLinkJpaEntity toEntity(SocialLink socialLink);

    SocialLink toDomain(SocialLinkJpaEntity entity);

    @Named("candidateIdToString")
    default String candidateIdToString(CandidateId id) {
        return id != null ? id.getValue().toString() : null;
    }

    @Named("stringToCandidateId")
    default CandidateId stringToCandidateId(String id) {
        return id != null ? new CandidateId(UUID.fromString(id)) : null;
    }

    default String map(SocialLinkType type) {
        return type != null ? type.name() : null;
    }

    default SocialLinkType map(String type) {
        return type != null ? SocialLinkType.valueOf(type) : null;
    }
}
