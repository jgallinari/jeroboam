package com.jeroboam.api;

import java.math.BigInteger;
import java.util.Map;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Holder;
import clojure.java.api.Clojure;
import clojure.lang.IFn;

/**
 * 
 */
@WebService(name = "Basic", targetNamespace = "http://api.jeroboam.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class BasicImpl implements Basic {


    /**
     * 
     * @param userLogin
     * @param requestId
     * @param sessionAuthId
     * @param userPwd
     */
    @WebMethod(action = "http://api.jeroboam.com/login")
    public void login(
        @WebParam(name = "userLogin", partName = "userLogin")
        String userLogin,
        @WebParam(name = "userPwd", partName = "userPwd")
        String userPwd,
        @WebParam(name = "requestId", mode = WebParam.Mode.OUT, partName = "requestId")
        Holder<BigInteger> requestId,
        @WebParam(name = "sessionAuthId", mode = WebParam.Mode.OUT, partName = "sessionAuthId")
        Holder<String> sessionAuthId) {

        IFn requireFn = Clojure.var("clojure.core", "require");
        requireFn.invoke(Clojure.read("com.jeroboam.api.api"));

        IFn loginFn = Clojure.var("com.jeroboam.api.api", "login");
        Map result = (Map) loginFn.invoke(userLogin, userPwd);

        requestId.value = new BigInteger(result.get("requestId").toString());
        sessionAuthId.value = result.get("sessionAuthId").toString();
    }
}
