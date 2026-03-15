package com.pcia.candidate.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Candidate {
    private final CandidateId id;
    private final String fullName;
    private final String email;
    private Curriculum curriculum;
    private final List<SocialLink> socialLinks;

    public Candidate(CandidateId id, String fullName, String email) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.fullName = Objects.requireNonNull(fullName, "Full name cannot be null");
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.socialLinks = new ArrayList<>();
    }

    public void updateCurriculum(Curriculum curriculum) {
        this.curriculum = Objects.requireNonNull(curriculum, "Curriculum cannot be null");
    }

    public void addSocialLink(SocialLink socialLink) {
        Objects.requireNonNull(socialLink, "Social link cannot be null");
        // Business rule: only one link of each type (simplification for this story)
        this.socialLinks.removeIf(link -> link.getType() == socialLink.getType());
        this.socialLinks.add(socialLink);
    }

    public CandidateId getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public List<SocialLink> getSocialLinks() {
        return Collections.unmodifiableList(socialLinks);
    }
}
