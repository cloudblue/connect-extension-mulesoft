package com.cloudblue.connect.api.clients.entity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileEntity {
    private final Map<String, File> files = new HashMap<>();

    public Map<String, File> getFiles() {
        return files;
    }

    public FileEntity add(String key, File file) {
        this.files.put(key, file);

        return this;
    }
}
