package com.pcia.candidate.application.dto;

import com.pcia.candidate.domain.model.CandidateId;
import java.io.InputStream;
import lombok.Value;

@Value
public class UploadCurriculumCommand {
    CandidateId candidateId;
    InputStream fileInputStream;
    String fileName;
    String contentType;
    long sizeBytes;
}
