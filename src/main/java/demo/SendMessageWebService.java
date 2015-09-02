package demo;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
@Stateless
//Name: OrderQueue
//JNDI Names: java:/jms/queue/OrderQueue 
public class SendMessageWebService {
    @Inject
    private JMSContext context;

    @Resource(mappedName = "java:/jms/queue/OrderQueue")
    private Queue queue;

    @WebMethod
    public String sendMessage() {
        String text = "Message from producer";
        context.createProducer().send(queue, context.createTextMessage(text));
        return text;
    }
}
