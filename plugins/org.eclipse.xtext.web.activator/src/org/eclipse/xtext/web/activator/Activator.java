package org.eclipse.xtext.web.activator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		ContextHandler ch1= new ContextHandler();
		Hashtable<String, String> props1 = new Hashtable<>();
		props1.put("Web-ContextPath","/resources");
		props1.put("Jetty-ContextFilePath",  "handlers/cssHandler.xml");
		context.registerService(ContextHandler.class.getName(), ch1, props1);

		ContextHandler ch2= new ContextHandler();
		Hashtable<String, String> props2 = new Hashtable<>();
		props2.put("Web-ContextPath","/resources");
		props2.put("Jetty-ContextFilePath",  "handlers/jsHandler.xml");
		context.registerService(ContextHandler.class.getName() ,ch2, props2);

		ContextHandler ch3= new ContextHandler();
		Hashtable<String, String> props3 = new Hashtable<>();
		props3.put("Web-ContextPath","/resources");
		props3.put("Jetty-ContextFilePath",  "handlers/jarHandler.xml");
		context.registerService(ContextHandler.class.getName() ,ch3, props3);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		bundleContext.ungetService(context.getServiceReference(ContextHandler.class.getName()));
		Activator.context = null;
	}

}
