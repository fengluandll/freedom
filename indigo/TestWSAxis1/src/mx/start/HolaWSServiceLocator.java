/**
 * HolaWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.start;

public class HolaWSServiceLocator extends org.apache.axis.client.Service implements mx.start.HolaWSService {

    public HolaWSServiceLocator() {
    }


    public HolaWSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public HolaWSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for HolaWS
    private java.lang.String HolaWS_address = "http://localhost:8080/MyServicesAxis1/services/HolaWS";

    public java.lang.String getHolaWSAddress() {
        return HolaWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String HolaWSWSDDServiceName = "HolaWS";

    public java.lang.String getHolaWSWSDDServiceName() {
        return HolaWSWSDDServiceName;
    }

    public void setHolaWSWSDDServiceName(java.lang.String name) {
        HolaWSWSDDServiceName = name;
    }

    public mx.start.HolaWS getHolaWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(HolaWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getHolaWS(endpoint);
    }

    public mx.start.HolaWS getHolaWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            mx.start.HolaWSSoapBindingStub _stub = new mx.start.HolaWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getHolaWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setHolaWSEndpointAddress(java.lang.String address) {
        HolaWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (mx.start.HolaWS.class.isAssignableFrom(serviceEndpointInterface)) {
                mx.start.HolaWSSoapBindingStub _stub = new mx.start.HolaWSSoapBindingStub(new java.net.URL(HolaWS_address), this);
                _stub.setPortName(getHolaWSWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("HolaWS".equals(inputPortName)) {
            return getHolaWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://start.mx", "HolaWSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://start.mx", "HolaWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("HolaWS".equals(portName)) {
            setHolaWSEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
