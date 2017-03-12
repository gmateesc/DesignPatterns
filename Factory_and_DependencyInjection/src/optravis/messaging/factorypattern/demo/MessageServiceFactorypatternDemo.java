package optravis.messaging.factorypattern.demo;

import optravis.messaging.service.*;
import optravis.messaging.factorypattern.factory.*;


public class MessageServiceFactorypatternDemo {

    public static void main(String[] args) {

	MessageService service = null;

	MessageServiceFactory messageFactory = new MessageServiceFactory();

	String msg = "Hello, world";

	// Get an EmailService object and call its "sendMessage" method
	service = messageFactory.getMessageService("EMAIL");
	String receiver = "joe@eample.com";
	service.sendMessage(msg, receiver); 	

	// Get an SMSService object and call its "sendMessage" method
	service = messageFactory.getMessageService("SMS");
	receiver = "888-123-4567";
	service.sendMessage(msg, receiver); 	

    }
}


