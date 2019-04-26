package com.sensetime.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface SpecialPasserSink {

    //String INPUT2 = "SpecialPasserTopic";
    String INPUT2 = "INPUT2";
    @Input(INPUT2)
    SubscribableChannel input2();

}
