package com.sensetime.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface FaceRawEventSink {

    //String INPUT4 = "FaceRawEvent";
    String INPUT4 = "INPUT4";
    @Input(INPUT4)
    SubscribableChannel input4();

}
