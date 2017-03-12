package optravis.messaging.client;

import optravis.messaging.service.MessageService;

public class MessageClient {

    private MessageService service;
    
    public MessageClient( MessageService svc ) {
	this.service = svc;
    }

    
    public void messenger(String msg, String receiver) {

	// delegate sending the message to MessageService object
	this.service.sendMessage(msg, receiver);
    }

}

