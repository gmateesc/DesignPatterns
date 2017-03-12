package gabriel.messaging.depinjectpattern.injector;

import gabriel.messaging.client.MessageClient;
import gabriel.messaging.service.SMSService;


public class SMSServiceInjector implements MessageServiceInjector {

    @Override
	public MessageClient getClient() {

	return new MessageClient(new SMSService() );
    }

}




