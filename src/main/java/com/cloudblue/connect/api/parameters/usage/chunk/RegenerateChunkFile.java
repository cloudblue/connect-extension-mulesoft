package com.cloudblue.connect.api.parameters.usage.chunk;

public class RegenerateChunkFile extends ChunkFileAction {
    @Override
    public Object buildEntity() {
        return null;
    }

    @Override
    public String action() {
        return "regenerate";
    }
}
