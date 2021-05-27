package com.cloudblue.connect.api.polling;

import com.cloudblue.connect.api.parameters.enums.PollingType;
import com.cloudblue.connect.internal.sources.utils.PollingCollection;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.OutputTypeResolver;

public class PollingOutputEntityResolver implements OutputTypeResolver<PollingType> {
    @Override
    public String getResolverName() {
        return "ConnectPollingResolver";
    }

    @Override
    public MetadataType getOutputType(MetadataContext metadataContext, PollingType pollingType)
            throws MetadataResolvingException, ConnectionException {
        PollingCollection.PollingObjectInfo objectInfo = PollingCollection.resolve(pollingType);
        return metadataContext.getTypeLoader().load(objectInfo.getClazz());
    }

    @Override
    public String getCategoryName() {
        return "ConnectPolling";
    }
}
