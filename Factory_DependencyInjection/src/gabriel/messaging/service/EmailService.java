package gabriel.messaging.service;

public class EmailService implements MessageService {

    @Override
	public void sendMessage(String msg, String receiver) {

	// send email message
	System.out.println("Message " + msg + " sent to " + receiver + " by email");
    }

}
