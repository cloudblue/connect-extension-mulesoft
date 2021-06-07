package com.cloudblue.connect.api.clients.entity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileEntity {
    private final Map<String, File> files = new HashMap<>();

    private final Map<String, String> values = new HashMap<>();

    public Map<String, File> getFiles() {
        return files;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public FileEntity addFile(String key, File file) {
        this.files.put(key, file);

        return this;
    }

    public FileEntity addValue(String key, String value) {
        this.values.put(key, value);

        return this;
    }
}
