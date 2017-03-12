package optravis.messaging.factorypattern.factory;


import optravis.messaging.service.*;


public class MessageServiceFactory {
	
    /** The getMessageService method is used to 
     * get an object of type MessageService.
     */
   public MessageService getMessageService(String serviceType) {

      if ( serviceType == null ) {
         return null;
      }		

      if ( serviceType.equalsIgnoreCase("EMAIL") ) {

         return new EmailService();
         
      } 
      else if ( serviceType.equalsIgnoreCase("SMS") ) {

         return new SMSService();
      }  

      return null;
   }
}

