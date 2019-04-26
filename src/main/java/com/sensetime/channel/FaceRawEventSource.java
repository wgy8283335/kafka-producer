package com.sensetime.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface FaceRawEventSource {

    //String OUTPUT4 = "FaceRawEvent";
    String OUTPUT4 = "OUTPUT4";
    @Output(OUTPUT4)
    MessageChannel output4();
}

