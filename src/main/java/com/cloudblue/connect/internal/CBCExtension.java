package com.cloudblue.connect.internal;

import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.Configurations;


/**
 * This is the main class of an extension, is the entry point from which configurations, connection providers, operations
 * and sources are going to be declared.
 */
@Xml(prefix = "cloudblue-connect")
@Extension(name = "CloudBlue Connect", vendor = "CloudBlue")
@Configurations(CBCConfiguration.class)
public class CBCExtension {

}
