package com.pcia.candidate.application.service;

import com.pcia.candidate.application.dto.UploadCurriculumCommand;
import com.pcia.candidate.application.port.in.UploadCurriculumUseCase;
import com.pcia.candidate.domain.model.Candidate;
import com.pcia.candidate.domain.model.Curriculum;
import com.pcia.candidate.domain.port.StorageProvider;
import com.pcia.candidate.domain.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class UploadCurriculumService implements UploadCurriculumUseCase {

    private final CandidateRepository candidateRepository;
    private final StorageProvider storageProvider;
    private final Tika tika = new Tika();

    @Override
    @Transactional
    public void uploadCurriculum(UploadCurriculumCommand command) {
        Candidate candidate = candidateRepository.findById(command.getCandidateId())
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found"));

        // Validate content type with Tika for security
        String verifiedContentType = verifyContentType(command);

        // Early validation of metadata using Curriculum rules
        // This ensures size and type are within limits before uploading
        new Curriculum(command.getFileName(), verifiedContentType, command.getSizeBytes(), "temp-key");

        // 1. Upload to storage provider (e.g. S3)
        String fileKey = storageProvider.upload(
                command.getFileInputStream(),
                command.getFileName(),
                verifiedContentType);

        // 2. Create curriculum and update candidate aggregate
        Curriculum curriculum = new Curriculum(
                command.getFileName(),
                verifiedContentType,
                command.getSizeBytes(),
                fileKey);
        candidate.updateCurriculum(curriculum);

        // 3. Persist changes
        candidateRepository.save(candidate);
    }

    private String verifyContentType(UploadCurriculumCommand command) {
        try {
            InputStream is = command.getFileInputStream();
            if (is.markSupported()) {
                is.mark(1024);
                String detected = tika.detect(is, command.getFileName());
                is.reset();
                return detected;
            }
            // Fallback to provided type if stream doesn't support mark/reset
            return command.getContentType();
        } catch (IOException e) {
            return command.getContentType();
        }
    }
}
