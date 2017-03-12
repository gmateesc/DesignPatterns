package gabriel.messaging.depinjectpattern.demo;

import gabriel.messaging.client.*;
import gabriel.messaging.depinjectpattern.injector.*;


public class MessageServiceDepinjectpatternDemo  {


    public static void main(String[] args) {

        // Message to send
	String msg = "Hello, world";

	MessageClient client = null;
	MessageServiceInjector injector = null;
	
	// Send message by email
	injector = new EmailServiceInjector();
	String email = "joe@example.com";
	client = injector.getClient();
	client.messenger(msg, email);	

	// Send message by SMS
	injector = new SMSServiceInjector();
	String phone = "888-123-4567";
	client = injector.getClient();
	client.messenger(msg, phone);
    }

}



