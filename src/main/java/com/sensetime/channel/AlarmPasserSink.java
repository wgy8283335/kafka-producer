package com.sensetime.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface AlarmPasserSink {

    //String INPUT1 = "AlarmPasserTopic";
    String INPUT1 = "INPUT1";
    @Input(INPUT1)
    SubscribableChannel input1();

}
