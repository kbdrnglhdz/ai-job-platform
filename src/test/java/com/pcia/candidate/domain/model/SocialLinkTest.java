package com.pcia.candidate.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SocialLinkTest {

    @Test
    void should_create_valid_linkedin_link() {
        SocialLink link = new SocialLink("https://www.linkedin.com/in/user", SocialLinkType.LINKEDIN);
        assertEquals("https://www.linkedin.com/in/user", link.getUrl());
        assertEquals(SocialLinkType.LINKEDIN, link.getType());
    }

    @Test
    void should_throw_exception_when_url_is_invalid() {
        assertThrows(IllegalArgumentException.class, () -> new SocialLink("invalid-url", SocialLinkType.LINKEDIN));
    }

    @Test
    void should_throw_exception_when_url_is_null() {
        assertThrows(NullPointerException.class, () -> new SocialLink(null, SocialLinkType.LINKEDIN));
    }
}
