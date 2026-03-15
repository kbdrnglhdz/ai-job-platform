package com.pcia.candidate.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CurriculumTest {

    @Test
    void should_create_valid_curriculum() {
        Curriculum curriculum = new Curriculum("cv.pdf", "application/pdf", 1024, "key123");
        assertEquals("cv.pdf", curriculum.getFileName());
        assertEquals("application/pdf", curriculum.getContentType());
        assertEquals(1024, curriculum.getSizeBytes());
    }

    @Test
    void should_fail_when_size_exceeds_limit() {
        long exceedingSize = 6 * 1024 * 1024; // 6MB
        assertThrows(IllegalArgumentException.class,
                () -> new Curriculum("large.pdf", "application/pdf", exceedingSize, "key123"));
    }

    @Test
    void should_fail_when_format_is_unsupported() {
        assertThrows(IllegalArgumentException.class, () -> new Curriculum("image.jpg", "image/jpeg", 1024, "key123"));
    }
}
