
package com.jeroboam.api;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * 
 * 
 *                          MobiquiThings, Inc.
 *                 Copyright 2020 All Rights Reserved.
 * 
 *     These computer program listings and specifications, herein,
 *     are the property of MobiquiThings, Inc. and shall not be
 *     reproduced or copied or used in whole or in part as the basis
 *     for manufacture or sale of items without written permission.
 * 
 *     Simple SOAP API v1.0
 * 
 *     
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.0-M4
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "Simple", targetNamespace = "http://api.jeroboam.com/", wsdlLocation = "file:/Users/jerome/tmp/clojure/api-jeroboam-com/resources/wsdl/Simple.wsdl")
public class Simple_Service
    extends Service
{

    private final static URL SIMPLE_WSDL_LOCATION;
    private final static WebServiceException SIMPLE_EXCEPTION;
    private final static QName SIMPLE_QNAME = new QName("http://api.jeroboam.com/", "Simple");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/Users/jerome/tmp/clojure/api-jeroboam-com/resources/wsdl/Simple.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SIMPLE_WSDL_LOCATION = url;
        SIMPLE_EXCEPTION = e;
    }

    public Simple_Service() {
        super(__getWsdlLocation(), SIMPLE_QNAME);
    }

    public Simple_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), SIMPLE_QNAME, features);
    }

    public Simple_Service(URL wsdlLocation) {
        super(wsdlLocation, SIMPLE_QNAME);
    }

    public Simple_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SIMPLE_QNAME, features);
    }

    public Simple_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Simple_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Simple
     */
    @WebEndpoint(name = "SimpleSOAP")
    public Simple getSimpleSOAP() {
        return super.getPort(new QName("http://api.jeroboam.com/", "SimpleSOAP"), Simple.class);
    }

    /**
     * 
     * @param features
     *     A list of {&#064;link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the &lt;code&gt;features&lt;/code&gt; parameter will have their default values.
     * @return
     *     returns Simple
     */
    @WebEndpoint(name = "SimpleSOAP")
    public Simple getSimpleSOAP(WebServiceFeature... features) {
        return super.getPort(new QName("http://api.jeroboam.com/", "SimpleSOAP"), Simple.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SIMPLE_EXCEPTION!= null) {
            throw SIMPLE_EXCEPTION;
        }
        return SIMPLE_WSDL_LOCATION;
    }

}