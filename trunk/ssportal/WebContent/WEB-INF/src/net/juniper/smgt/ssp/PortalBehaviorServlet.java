/**
 * ===========================================================================
 * Licensed Materials - Property of Juniper Networks
 * Juniper Networks SDX(tm)
 *
 *  (C) Copyright Juniper Networks Inc. 2000-2003 All Rights Reserved.
 * ===========================================================================
 */

package net.juniper.smgt.ssp;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import net.juniper.smgt.ssp.model.PortalBehavior;
import net.juniper.smgt.sae.sae.SAEAccess;
import net.juniper.smgt.sae.sae.SAEAccessHelper;
import java.lang.reflect.Constructor;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import net.juniper.smgt.sae.sae.SAEException;


/**
 * The PortalBehaviorServlet creates a global fatory to supply the
 * different actions with portal behavior objects.<p>
 *
 * The name of the property file containing the configuration is
 * configured in the web-application deployment descriptor
 * (WEB-INF/web.xml) in init-param "properties".<p>
 *
 * The following properties are evaluated by the PortalBehaviorServlet:
 * <ul>
 * <li> "Factory.behavior": fully qualified name of a Java class implementing
 *   net.juniper.smgt.ssp.model.PortalBehavior
 * <li> "Factory.locator":  fully qualified name of a Java class implementing
 *   net.juniper.smgt.ssp.SAEFeatureLocator
 * </ul>
 */
public class PortalBehaviorServlet extends HttpServlet {
    public PortalBehaviorServlet() {
    }

    /**
     * Initialize the servlet.<p>
     *
     * The servlet checks that the properties "behavior" and "locator"
     * are configured and that the specified classes implement the
     * expected interfaces.
     *
     * @throws ServletException if the configuration is invalid.
     */
    public void init() throws ServletException {
        SAEFeatureLocator theLocator;
        Constructor theBehavior;

        String confFile = getServletConfig().getInitParameter("properties");

        Properties props = new Properties();
        try {
            InputStream is = getServletContext().getResourceAsStream(confFile);
            props.load(is);
        }
        catch (IOException e) {
            System.err.println("Can't load properties "+confFile+
                               ", will use default values");
        }
        
        String behavior = props.getProperty("Factory.behavior",
                                            "net.juniper.smgt.ssp.model.EquipmentRegistrationBehavior");
        String locator =  props.getProperty("Factory.locator",
                                            "net.juniper.smgt.ssp.LocalFeatureLocator");

        try {
            Class klass = Class.forName(behavior);
            Class[] args = {SAEAccess.class,
                            String.class,
                            Boolean.TYPE,
                            String.class};
            theBehavior = klass.getConstructor(args);
            
        }
        catch (ClassNotFoundException e) {
            throw new ServletException("Unknown Behavior: "+behavior);
        }
        catch (NoSuchMethodException e) {
            throw new ServletException("Wrong Behavior implementation: "+behavior);
        }


        try {
            theLocator = (SAEFeatureLocator)Class.forName(locator).newInstance();
            theLocator.setProperties(props);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Unknown Locator: " +locator);
        }
        catch (Throwable t) {
            t.printStackTrace();
            throw new ServletException("Could not instantiate Locator ["+t+"]");
        }

        PortalBehaviorFactory factory = PortalBehaviorFactory.getInstance();

        factory.setLocator(theLocator);
        factory.setBehavior(theBehavior);
    }

    /**
     * Destroy the servlet and free used resources
     */
    public void destroy() {
        PortalBehaviorFactory factory = PortalBehaviorFactory.getInstance();

        factory.destroy();
    }
}
