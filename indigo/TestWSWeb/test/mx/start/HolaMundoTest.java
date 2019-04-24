

/**
 * HolaMundoTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package mx.start;

    /*
     *  HolaMundoTest Junit test case
    */

    public class HolaMundoTest extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testsaluda() throws java.lang.Exception{

        mx.start.HolaMundoStub stub =
                    new mx.start.HolaMundoStub();//the default implementation should point to the right endpoint

           mx.start.HolaMundoStub.Saluda saluda4=
                                                        (mx.start.HolaMundoStub.Saluda)getTestObject(mx.start.HolaMundoStub.Saluda.class);
                    // TODO : Fill in the saluda4 here
                
                        assertNotNull(stub.saluda(
                        saluda4));
                  



        }
        
         /**
         * Auto generated test method
         */
        public  void testStartsaluda() throws java.lang.Exception{
            mx.start.HolaMundoStub stub = new mx.start.HolaMundoStub();
             mx.start.HolaMundoStub.Saluda saluda4=
                                                        (mx.start.HolaMundoStub.Saluda)getTestObject(mx.start.HolaMundoStub.Saluda.class);
                    // TODO : Fill in the saluda4 here
                

                stub.startsaluda(
                         saluda4,
                    new tempCallbackN65548()
                );
              


        }

        private class tempCallbackN65548  extends mx.start.HolaMundoCallbackHandler{
            public tempCallbackN65548(){ super(null);}

            public void receiveResultsaluda(
                         mx.start.HolaMundoStub.SaludaResponse result
                            ) {
                
            }

            public void receiveErrorsaluda(java.lang.Exception e) {
                fail();
            }

        }
      
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    