package com.pcia.candidate.domain.model;

import java.util.Objects;
import java.util.UUID;

public final class CandidateId {
    private final UUID value;

    public CandidateId(UUID value) {
        this.value = Objects.requireNonNull(value, "Candidate ID value cannot be null");
    }

    public static CandidateId generate() {
        return new CandidateId(UUID.randomUUID());
    }

    public static CandidateId fromString(String value) {
        return new CandidateId(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CandidateId that = (CandidateId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
