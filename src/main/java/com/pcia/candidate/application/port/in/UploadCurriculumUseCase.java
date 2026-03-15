package com.pcia.candidate.application.port.in;

import com.pcia.candidate.application.dto.UploadCurriculumCommand;

public interface UploadCurriculumUseCase {
    void uploadCurriculum(UploadCurriculumCommand command);
}
