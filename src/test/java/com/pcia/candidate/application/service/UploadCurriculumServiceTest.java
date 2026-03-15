package com.pcia.candidate.application.service;

import com.pcia.candidate.application.dto.UploadCurriculumCommand;
import com.pcia.candidate.domain.model.Candidate;
import com.pcia.candidate.domain.model.CandidateId;
import com.pcia.candidate.domain.port.StorageProvider;
import com.pcia.candidate.domain.repository.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UploadCurriculumServiceTest {

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private StorageProvider storageProvider;

    @InjectMocks
    private UploadCurriculumService uploadCurriculumService;

    private Candidate candidate;
    private CandidateId candidateId;

    @BeforeEach
    void setUp() {
        candidateId = CandidateId.generate();
        candidate = new Candidate(candidateId, "John Doe", "john@example.com");
    }

    @Test
    void shouldUploadCurriculumSuccessfully() {
        // Arrange
        InputStream inputStream = new ByteArrayInputStream("%PDF-1.4".getBytes());
        UploadCurriculumCommand command = new UploadCurriculumCommand(
                candidateId,
                inputStream,
                "resume.pdf",
                "application/pdf",
                1024L);

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));
        when(storageProvider.upload(any(), any(), any())).thenReturn("s3-key-123");

        // Act
        uploadCurriculumService.uploadCurriculum(command);

        // Assert
        assertNotNull(candidate.getCurriculum());
        assertEquals("resume.pdf", candidate.getCurriculum().getFileName());
        assertEquals("s3-key-123", candidate.getCurriculum().getFileKey());
        verify(candidateRepository, times(1)).save(candidate);
        verify(storageProvider, times(1)).upload(eq(inputStream), eq("resume.pdf"), eq("application/pdf"));
    }

    @Test
    void shouldThrowExceptionWhenCandidateNotFound() {
        // Arrange
        UploadCurriculumCommand command = new UploadCurriculumCommand(
                candidateId,
                new ByteArrayInputStream(new byte[0]),
                "resume.pdf",
                "application/pdf",
                0L);
        when(candidateRepository.findById(candidateId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> uploadCurriculumService.uploadCurriculum(command));
        verify(storageProvider, never()).upload(any(), any(), any());
    }
}
