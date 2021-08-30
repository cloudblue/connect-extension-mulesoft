/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operation.usage;

import com.cloudblue.connect.api.models.CBCResponseAttributes;
import com.cloudblue.connect.api.parameters.usage.chunk.DownloadChunkFile;
import com.cloudblue.connect.internal.connection.CBCConnection;

import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;


import static com.cloudblue.connect.api.clients.constants.APIConstants.CollectionKeys.*;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

public class ChunkFileOperations {

    @MediaType(value = ANY, strict = false)
    @DisplayName("Download Usage Chunk File")
    public Result<Void, CBCResponseAttributes> downloadChunkFiles(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Download Chunk File Details")
                    DownloadChunkFile downloadChunkFile) throws MuleException {

        return connection.newQ()
                .collection(USAGE)
                .collection(CHUNKS, downloadChunkFile.getId())
                .collection("download")
                .download(
                        downloadChunkFile.getLocation(),
                        downloadChunkFile.getFileName()
                );
    }
}
