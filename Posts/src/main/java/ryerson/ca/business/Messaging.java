package ryerson.ca.business;

import io.grpc.stub.StreamObserver;
import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import io.kubemq.sdk.event.EventReceive;
import io.kubemq.sdk.event.Subscriber;
import io.kubemq.sdk.subscription.EventsStoreType;
import io.kubemq.sdk.subscription.SubscribeRequest;
import io.kubemq.sdk.subscription.SubscribeType;
import io.kubemq.sdk.tools.Converter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;
import ryerson.ca.endpoint.MyAppServletContextListener;
import ryerson.ca.persistence.Post_CRUD;


public class Messaging {
    public static void Receiving_Events_Store(String cname) throws SSLException, ServerAddressNotSuppliedException {
        String ChannelName = cname, ClientID = "hello-world-subscribe1r";
                String kubeMQAddress = System.getenv("kubeMQAddress");
        Subscriber subscriber = new Subscriber(kubeMQAddress);
        SubscribeRequest subscribeRequest = new SubscribeRequest();
        subscribeRequest.setChannel(ChannelName);
        subscribeRequest.setClientID(ClientID);
        subscribeRequest.setSubscribeType(SubscribeType.EventsStore);
        subscribeRequest.setEventsStoreType(EventsStoreType.StartAtSequence);
        subscribeRequest.setEventsStoreTypeValue(1);

        StreamObserver<EventReceive> streamObserver = new StreamObserver<EventReceive>() {

            @Override
            public void onNext(EventReceive value) {
                System.out.println("NEXT!");
                try {
                    String val=(String) Converter.FromByteArray(value.getBody());

                    String[] msgParts = val.split(":");
                    if(msgParts[2].equals("sub")){
                        Post_CRUD.subscribe(msgParts[0], Integer.parseInt(msgParts[1]));
                    }
                    else{
                        Post_CRUD.unsubscribe(msgParts[0], Integer.parseInt(msgParts[1]));
                    }
                } catch (ClassNotFoundException e) {
                    System.out.printf("ClassNotFoundException: %s", e.getMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.printf("IOException: %s", e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable t) {
                System.out.printf("onError:  %s", t.getMessage());
            }

            @Override
            public void onCompleted() {

            }

        };
        subscriber.SubscribeToEvents(subscribeRequest, streamObserver);

    }
    
}
