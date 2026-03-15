package com.pcia.candidate.infrastructure.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "candidates")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateJpaEntity {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private CurriculumJpaEntity curriculum;

    @Builder.Default
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SocialLinkJpaEntity> socialLinks = new ArrayList<>();

    public void setCurriculum(CurriculumJpaEntity curriculum) {
        if (curriculum == null) {
            if (this.curriculum != null) {
                this.curriculum.setCandidate(null);
            }
        } else {
            curriculum.setCandidate(this);
        }
        this.curriculum = curriculum;
    }

    public void addSocialLink(SocialLinkJpaEntity socialLink) {
        socialLinks.add(socialLink);
        socialLink.setCandidate(this);
    }
}
