
# Design Patterns in Java


## Table of Contents

- [The Factory pattern](#p1)
  - [The service interface and its implementation](#p11)
  - [The MessageServiceFactory class](#p12)
  - [Putting it together: the MessageServiceFactorypatternDemo application](#p13)


- [The Dependency injection pattern](#p2)
  - [The service interface and its implementation](#p21)
  - [Decoupling the application from the service implementation](#p22)
  - [Putting it together: the MessageServiceDepinjectpatternDemo application](#p23)


- [Running the demo applications](#p3)





<a name="p1" id="p1"></a>
## The factory pattern

The factory pattern enables creating objects without exposing the creation 
logic to the client and refering to the objects created by the factory 
using a uniform interface.

We illustrate the factory pattern for the case of creating intances of two classes 
that implement the ![MessageService](https://github.com/gmateesc/DesignPatterns/blob/master/Factory_DependencyInjection/src/gabriel/messaging/service/MessageService.java) application shows how the factory pattern enables creating objects of different subtypes of MessageService (EmailService and SMSService) using a uniform interface:


<a name="p11" id="p11"></a>
### The service interface and its implementation 

The ![MessageService](https://github.com/gmateesc/DesignPatterns/blob/master/Factory_DependencyInjection/src/gabriel/messaging/service/MessageService.java) interface is:

```
package gabriel.messaging.service;

public interface MessageService {

   void sendMessage(String msg, String receiver);

}
```

and the two implementations of this service interface are: 

* ![EmailService](https://github.com/gmateesc/maven-projects/blob/master/DesignPatterns/src/gabriel/messaging/service/EmailService.java) and 

* ![SMSService](https://github.com/gmateesc/maven-projects/blob/master/DesignPatterns/src/gabriel/messaging/service/SMSService.java) 


The classes are:

```
package gabriel.messaging.service;

public class EmailService implements MessageService {

    @Override
    public void sendMessage(String msg, String receiver) {

    // send email message
    System.out.println("Message " + msg + " sent to " + receiver + " by email");
    }

}
```

and

```
package gabriel.messaging.service;

public class SMSService implements MessageService {

    @Override
    public void sendMessage(String msg, String receiver) {

    // send SMS message
    System.out.println("Message " + msg + " sent to " + receiver + " by SMS");

    }

}
```




<a name="p12" id="p12"></a>
## The MessageServiceFactory class


The ![MessageServiceFactory](https://github.com/gmateesc/maven-projects/blob/master/DesignPatterns/src/gabriel/messaging/factorypattern/factory/MessageServiceFactory.java) creates objects for both classes that implemet the MessageService interface:
```
package gabriel.messaging.factorypattern.factory;

import gabriel.messaging.service.*;

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

```



<a name="p13" id="p13"></a>
## Putting it together: the MessageServiceFactorypatternDemo application

The sample ![MessageServiceFactorypatternDemo](https://github.com/gmateesc/DesignPatterns/blob/master/Factory_DependencyInjection/src/gabriel/messaging/depinjectpattern/demo/MessageServiceDepinjectpatternDemo.java) application shows how the factory pattern enables creating objects of different subtypes of MessageService (EmailService and SMSService) using a uniform interface:


```
package gabriel.messaging.factorypattern.demo;

import gabriel.messaging.service.*;
import gabriel.messaging.factorypattern.factory.*;


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


```





<a name="p2" id="p2"></a>
## The dependency injection pattern

Consider a [MessageService](#p21) interface that has several existing implementations 
and some potential future implementations, and an application that uses this service.

The dependency injection pattern allows to decouple applications 
that use the MessageServuice from the implementation of the service. 

Below, I describe the a MessageService interface and two of its 
implementations and then I show how dependency injection allows to 
write application code that does not need to be changed when the 
service implementation chganges.

So  I will describe the following components: 
* the service interface and two implementations of the interface;
* the client class, 
* the injector interface, and the injector class



<a name="p21" id="p21"></a>
### The service interface and its implementation 

Define a ![MessageService](https://github.com/gmateesc/maven-projects/tree/master/DesignPatterns/src/gabriel/messaging/service) interface:

```
package gabriel.messaging.service;

public interface MessageService {

   void sendMessage(String msg, String receiver);

}
```


Then create two implementations of this service interface: 

* ![EmailService](https://github.com/gmateesc/maven-projects/blob/master/DesignPatterns/src/gabriel/messaging/service/EmailService.java) and 

* ![SMSService](https://github.com/gmateesc/maven-projects/blob/master/DesignPatterns/src/gabriel/messaging/service/SMSService.java) 


The classes are:

```
package gabriel.messaging.service;

public class EmailService implements MessageService {

    @Override
    public void sendMessage(String msg, String receiver) {

    // send email message
    System.out.println("Message " + msg + " sent to " + receiver + " by email");
    }

}
```

and

```
package gabriel.messaging.service;

public class SMSService implements MessageService {

    @Override
    public void sendMessage(String msg, String receiver) {

    // send SMS message
    System.out.println("Message " + msg + " sent to " + receiver + " by SMS");

    }

}
```

NOTE:  The service interface and the implementations are the same as those for the [FactoryPattern](#p11) 


The challenge that dependency injection solves is to allow applications 
to use either of the above implementations or some other implementations, 
without the need for the application to be aware of the service 
implementation details.




<a name="p22" id="p22"></a>
### Decoupling the application from the service implementation 

Three components are used in dependency injection:
* the client class, 
* the injector interface, and 
* the injector class




#### The client class


The class ![MessageClient](https://github.com/gmateesc/maven-projects/blob/master/DesignPatterns/src/gabriel/messaging/client/MessageClient.java) is used by applications to access an implementation of the MessageService interface. 

```
package gabriel.messaging.client;

import gabriel.messaging.service.MessageService;

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
``` 


Using the client class allows applications to always invoke the method 
_messenger()_ on a client class object, even if the service implementation 
changes or a new service implementation is added. 





#### The injector interface and injector class

An application obtains an instance of the client class using an object of type 
injector class, where the inject class that implements the injector interface. 


The injector interface ![MessageServiceInjector.java](https://github.com/gmateesc/maven-projects/blob/master/DesignPatterns/src/gabriel/messaging/depinjectpattern/injector/MessageServiceInjector.java) defines the method _getClient()_ that needs to be implemented by any injector class:

```
package gabriel.messaging.depinjectpattern.injector;

import gabriel.messaging.client.MessageClient;

public interface MessageServiceInjector {

    public MessageClient  getClient();
}
```


There are two injector classes, one for each MessageService class: ![EmailServiceInjector](https://github.com/gmateesc/maven-projects/blob/master/DesignPatterns/src/gabriel/messaging/depinjectpattern/injector/EmailServiceInjector.java) and ![SMSServiceInjector](https://github.com/gmateesc/maven-projects/blob/master/DesignPatterns/src/gabriel/messaging/depinjectpattern/injector/SMSServiceInjector.java). 

```
package gabriel.messaging.depinjectpattern.injector;

import gabriel.messaging.client.MessageClient;
import gabriel.messaging.service.EmailService;

public class EmailServiceInjector implements MessageServiceInjector {

    @Override
    public MessageClient getClient() {

    return new MessageClient(new EmailService() );
    }

}
```
and
```
package gabriel.messaging.depinjectpattern.injector;

import gabriel.messaging.client.MessageClient;
import gabriel.messaging.service.SMSService;


public class SMSServiceInjector implements MessageServiceInjector {

    @Override
    public MessageClient getClient() {

    return new MessageClient(new SMSService() );
    }

}
```





<a name="p23" id="p23"></a>
### Putting it together: the MessageServiceDepinjectpatternDemo application


Once we have defined the interfaces and classes described above, the application  will be easy to write. 

The class ![MessageServiceDepinjectpatternDemo.java](https://github.com/gmateesc/maven-projects/blob/master/DesignPatterns/src/gabriel/messaging/depinjectpattern/demo/MessageServiceDepinjectpatternDemo.java) does the work in the _main()_ method:

```
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
```
The application sends a message using both the EmailService and the SMSService, 
but does mot invoke the services directly; ineasde it uses a MessageClient object 
and invokes the _messenger()_ method of the object, thus protecting the application 
from changes in the service.  If a change in the service interface occurs, only 
the injector class will need to be changed.





<a name="p3" id="p3"></a>
## Running the demo applications

To run both the factory pattern and dependency injection pattern examples, 
enter at the shell prompt
```
   ant 
```

To run the factory pattern example, enter at the shell prompt
```
   ant  demo_factory_pattern
```


To run the dependency injection pattern example, enter at the shell prompt
```
   ant demo_depinject_pattern
```


If you prefer using GNU make, run:
```
   make
```


Here is the result of running the demo applications:

```
  $ ant 
  Buildfile: /Users/gabriel/GitHub/maven-projects/DesignPatterns/build.xml

  compile:
    [mkdir] Created dir: /Users/gabriel/GitHub/maven-projects/DesignPatterns/classes
    [javac] Compiling 10 source files to /Users/gabriel/GitHub/maven-projects/DesignPatterns/classes

  demo_factory_pattern:
     [echo] 
     [echo] Run: java gabriel.messaging/factorypattern/demo/MessageServiceFactorypatternDemo
     [echo] 
     [java] Message Hello, world sent to joe@eample.com by email
     [java] Message Hello, world sent to 888-123-4567 by SMS

  demo_depinject_pattern:
     [echo] 
     [echo] Run: java gabriel.messaging/depinjectpattern/demo/MessageServiceDepinjectpatternDemo
     [echo] 
     [java] Message Hello, world sent to joe@example.com by email
     [java] Message Hello, world sent to 888-123-4567 by SMS

  main:

  BUILD SUCCESSFUL
  Total time: 2 seconds
  ```








