package com.pcia.candidate.domain.model;

import java.util.Objects;
import java.util.regex.Pattern;

public final class SocialLink {
    private final String url;
    private final SocialLinkType type;

    private static final Pattern URL_PATTERN = Pattern
            .compile("^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?$");

    public SocialLink(String url, SocialLinkType type) {
        this.url = Objects.requireNonNull(url, "URL cannot be null");
        this.type = Objects.requireNonNull(type, "Type cannot be null");
        validateUrl(url);
    }

    private void validateUrl(String url) {
        if (!URL_PATTERN.matcher(url).matches()) {
            throw new IllegalArgumentException("Invalid social link URL format");
        }
    }

    public String getUrl() {
        return url;
    }

    public SocialLinkType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SocialLink that = (SocialLink) o;
        return Objects.equals(url, that.url) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, type);
    }
}
