package com.cloudblue.connect.internal.listeners;

import org.mule.runtime.core.api.context.notification.MuleContextNotification;
import org.mule.runtime.core.api.context.notification.MuleContextNotificationListener;

import static org.mule.runtime.core.api.context.notification.MuleContextNotification.*;

public class MuleContextStopListener implements MuleContextNotificationListener<MuleContextNotification> {

    private volatile boolean stopping = false;

    @Override
    public synchronized void onNotification(MuleContextNotification notification) {
        if (notification.getAction().getActionId() == CONTEXT_STOPPING) {
            stopping = true;
        } else if (notification.getAction().getActionId() == CONTEXT_STARTING) {
            stopping = false;
        }
    }

    synchronized boolean isStopping() {
        return stopping;
    }
}
