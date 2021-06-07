package com.cloudblue.connect.internal.operations.usage;

import com.cloudblue.connect.api.clients.constants.HttpMethod;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.usage.CBCUsageChunkFile;
import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;
import com.cloudblue.connect.api.parameters.usage.chunk.ChunkFileAction;
import com.cloudblue.connect.api.parameters.usage.chunk.DownloadChunkFile;
import com.cloudblue.connect.api.parameters.usage.chunk.UpdateChunkFileParameters;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;

import com.fasterxml.jackson.core.type.TypeReference;

import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;


import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.*;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

public class ChunkFileOperations {
    @MediaType(value = ANY, strict = false)
    @DisplayName("Get Usage Chunk Files")
    public CBCUsageChunkFile getChunkFiles(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Usage Chunk File ID") ResourceActionParameter resourceActionParameter
    ) throws CBCException {

        return (CBCUsageChunkFile) connection
                .newQ(new TypeReference<CBCUsageChunkFile>() {})
                .collection(USAGE)
                .collection(CHUNKS, resourceActionParameter.getId())
                .get();
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Update Usage Chunk Files")
    public CBCUsageChunkFile updateChunkFile(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Update Chunk File Details") UpdateChunkFileParameters updateChunkFileParameters
    ) throws CBCException {

        return (CBCUsageChunkFile) connection
                .newQ(new TypeReference<CBCUsageChunkFile>() {})
                .collection(USAGE)
                .collection(CHUNKS, updateChunkFileParameters.getId())
                .update(updateChunkFileParameters.buildEntity());
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Usage Chunk File Action")
    public CBCUsageChunkFile chunkFileAction(
            @Connection CBCConnection connection,
            ChunkFileAction chunkFileActionDetails
    ) throws CBCException {

        return (CBCUsageChunkFile) connection
                .newQ(new TypeReference<CBCUsageChunkFile>() {})
                .collection(USAGE)
                .collection(CHUNKS, chunkFileActionDetails.getId())
                .action(chunkFileActionDetails.action(), HttpMethod.POST, chunkFileActionDetails.buildEntity());
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Download Usage Chunk File")
    public CBCUsageChunkFile downloadChunkFiles(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Download Chunk File Details") DownloadChunkFile downloadChunkFile
            ) throws CBCException {

        connection
                .newQ(null)
                .collection(USAGE)
                .collection(CHUNKS, downloadChunkFile.getId())
                .collection("download")
                .download(
                        downloadChunkFile.getLocation(),
                        downloadChunkFile.getFileName()
                );

        return getChunkFiles(connection, downloadChunkFile);
    }
}
