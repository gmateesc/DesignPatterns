package gabriel.messaging.service;

public class SMSService implements MessageService {

    @Override
	public void sendMessage(String msg, String receiver) {

	// send SMS message
	System.out.println("Message " + msg + " sent to " + receiver + " by SMS");

    }

}

