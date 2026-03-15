package com.pcia.candidate.domain.port;

import java.io.InputStream;

public interface StorageProvider {
    /**
     * Uploads a file to the storage provider and returns the unique key.
     * 
     * @param inputStream The content of the file
     * @param fileName    The name of the file
     * @param contentType The MIME type of the file
     * @return The unique key/path of the stored file
     */
    String upload(InputStream inputStream, String fileName, String contentType);
}
