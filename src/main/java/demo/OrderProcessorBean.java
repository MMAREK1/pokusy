package demo;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@MessageDriven(name = "OrderProcessorBean", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", 
        		propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", 
        propertyValue = "jms/queue/OrderQueue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", 
        propertyValue = "Auto-acknowledge") })
public class OrderProcessorBean implements javax.jms.MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage) {
            try {
                System.out.println(">>>>>" + ((TextMessage)message).getText() + "<<<<<");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
