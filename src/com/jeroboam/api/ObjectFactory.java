
package com.jeroboam.api;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.jeroboam.api package. 
 * &lt;p&gt;An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.jeroboam.api
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Addon }
     * 
     */
    public Addon createAddon() {
        return new Addon();
    }

    /**
     * Create an instance of {@link ArrayOfAddon }
     * 
     */
    public ArrayOfAddon createArrayOfAddon() {
        return new ArrayOfAddon();
    }

}
