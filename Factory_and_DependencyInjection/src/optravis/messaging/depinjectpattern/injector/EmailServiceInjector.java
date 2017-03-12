package optravis.messaging.depinjectpattern.injector;

import optravis.messaging.client.MessageClient;
import optravis.messaging.service.EmailService;


public class EmailServiceInjector implements MessageServiceInjector {

    @Override
	public MessageClient getClient() {

	return new MessageClient(new EmailService() );
    }

}



