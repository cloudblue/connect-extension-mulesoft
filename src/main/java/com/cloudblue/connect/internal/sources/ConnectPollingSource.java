package com.cloudblue.connect.internal.sources;

import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.common.CBCEntity;
import com.cloudblue.connect.api.parameters.enums.PollingType;
import com.cloudblue.connect.api.parameters.filters.Filter;
import com.cloudblue.connect.api.parameters.filters.OrderBy;
import com.cloudblue.connect.api.polling.PollingAttributes;
import com.cloudblue.connect.api.polling.PollingOutputEntityResolver;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;
import com.cloudblue.connect.internal.sources.common.ListObject;

import com.cloudblue.connect.internal.sources.utils.PollingCollection;
import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.api.metadata.MediaType;
import org.mule.runtime.extension.api.annotation.metadata.MetadataKeyId;
import org.mule.runtime.extension.api.annotation.metadata.MetadataScope;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.extension.api.runtime.source.PollContext;
import org.mule.runtime.extension.api.runtime.source.PollingSource;
import org.mule.runtime.extension.api.runtime.source.SourceCallbackContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@MetadataScope(outputResolver = PollingOutputEntityResolver.class)
public class ConnectPollingSource extends PollingSource<CBCEntity, PollingAttributes> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectPollingSource.class);

    @MetadataKeyId
    @Parameter
    PollingType objectType;

    @Parameter
    @Optional
    Filter filter;

    @Parameter
    @Optional
    OrderBy orderBy;

    @Parameter
    @Optional(defaultValue = "false")
    private boolean idempotency = false;

    @Connection
    private ConnectionProvider<CBCConnection> connectionProvider;

    private CBCConnection connection;

    @Override
    protected void doStart() throws MuleException {
        connection = connectionProvider.connect();
    }

    @Override
    protected void doStop() {
        // Do nothing on termination.
    }

    @Override
    public void poll(PollContext<CBCEntity, PollingAttributes> pollContext) {
        if(pollContext.isSourceStopping()){
            return;
        }

        ListObject listObject = new ListObject();
        listObject.setFilter(this.filter);
        listObject.setOrderBy(this.orderBy);

        try {
            PollingCollection.PollingObjectInfo objectInfo = PollingCollection.resolve(this.objectType);
            List list = (List) listObject.list(
                    this.connection,
                    objectInfo.getTypeRef(),
                    objectInfo.getUrl()
            );

            if (list != null && !list.isEmpty()) {
                for (Object object : list) {
                    CBCEntity entity = (CBCEntity) object;

                    if(pollContext.isSourceStopping()){
                        break;
                    }

                    pollContext.accept(item -> {
                        Result<CBCEntity, PollingAttributes> result = buildResult(entity, objectInfo);
                        item.setResult(result);
                        if (this.idempotency) {
                            String id = getId(entity, objectInfo.getClazz());
                            item.setId(id);
                        }

                    });

                }
            }
        } catch (CBCException e) {
            LOGGER.error("Error during polling data.", e);
        }


    }

    private Result<CBCEntity, PollingAttributes> buildResult(CBCEntity entity, PollingCollection.PollingObjectInfo objectInfo) {
        Result.Builder<CBCEntity, PollingAttributes> resultBuilder = Result.builder();
        PollingAttributes attributes = new PollingAttributes();
        attributes.setObjectType(objectInfo.getUrl());

        return resultBuilder.output(entity).mediaType(MediaType.APPLICATION_JAVA).attributes(attributes).build();
    }

    private String getId(Object object, Class clazz) {
        String idValue = null;
        try {
            Method idMethod = clazz.getDeclaredMethod("getId");
            idValue = (String)idMethod.invoke(object);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            LOGGER.error("Error during getting id value from entity.", e);
        }

        return idValue;
    }

    @Override
    public void onRejectedItem(
            Result<CBCEntity, PollingAttributes> result,
            SourceCallbackContext sourceCallbackContext
    ) {
        // Do nothing on rejection.
    }
}
