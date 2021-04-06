
package com.jeroboam.api;

import java.math.BigInteger;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Holder;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.0-M4
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Simple", targetNamespace = "http://api.jeroboam.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Simple {


    /**
     * 
     * @param addonList
     * @param sessionAuthId
     * @param requestId
     * @param coId
     */
    @WebMethod(action = "http://api.jeroboam.com/getAddonList")
    public void getAddonList(
        @WebParam(name = "sessionAuthId", partName = "sessionAuthId")
        String sessionAuthId,
        @WebParam(name = "coId", partName = "coId")
        String coId,
        @WebParam(name = "requestId", mode = WebParam.Mode.OUT, partName = "requestId")
        Holder<BigInteger> requestId,
        @WebParam(name = "addonList", mode = WebParam.Mode.OUT, partName = "addonList")
        Holder<ArrayOfAddon> addonList);

}
