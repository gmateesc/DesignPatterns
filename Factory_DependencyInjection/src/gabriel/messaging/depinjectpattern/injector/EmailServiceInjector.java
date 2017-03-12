package gabriel.messaging.depinjectpattern.injector;

import gabriel.messaging.client.MessageClient;
import gabriel.messaging.service.EmailService;


public class EmailServiceInjector implements MessageServiceInjector {

    @Override
	public MessageClient getClient() {

	return new MessageClient(new EmailService() );
    }

}



