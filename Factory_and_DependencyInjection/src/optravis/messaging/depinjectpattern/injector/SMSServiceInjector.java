package optravis.messaging.depinjectpattern.injector;

import optravis.messaging.client.MessageClient;
import optravis.messaging.service.SMSService;


public class SMSServiceInjector implements MessageServiceInjector {

    @Override
	public MessageClient getClient() {

	return new MessageClient(new SMSService() );
    }

}




