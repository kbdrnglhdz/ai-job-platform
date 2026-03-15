package com.pcia.candidate.domain.model;

import java.util.Objects;
import java.util.Set;

public final class Curriculum {
    private final String fileName;
    private final String contentType;
    private final long sizeBytes;
    private final String fileKey; // S3 path or identifier

    private static final long MAX_SIZE = 5 * 1024 * 1024; // 5MB
    private static final Set<String> ALLOWED_TYPES = Set.of(
            "application/pdf",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "application/msword");

    public Curriculum(String fileName, String contentType, long sizeBytes, String fileKey) {
        this.fileName = Objects.requireNonNull(fileName, "File name cannot be null");
        this.contentType = Objects.requireNonNull(contentType, "Content type cannot be null");
        this.fileKey = Objects.requireNonNull(fileKey, "File key cannot be null");
        this.sizeBytes = sizeBytes;
        validate();
    }

    private void validate() {
        if (sizeBytes > MAX_SIZE) {
            throw new IllegalArgumentException("File size exceeds 5MB limit");
        }
        if (!ALLOWED_TYPES.contains(contentType)) {
            throw new IllegalArgumentException("Invalid file format. Please upload PDF or Word documents.");
        }
    }

    public String getFileName() {
        return fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public long getSizeBytes() {
        return sizeBytes;
    }

    public String getFileKey() {
        return fileKey;
    }
}
