/**
 * HolaMundo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.start;

public interface HolaMundo extends javax.xml.rpc.Service {
    public java.lang.String getHolaMundoHttpSoap11EndpointAddress();

    public mx.start.HolaMundoPortType getHolaMundoHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException;

    public mx.start.HolaMundoPortType getHolaMundoHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
