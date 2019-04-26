package com.sensetime.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AlarmPasserSource {

    //String OUTPUT1 = "AlarmPasserTopic";
    String OUTPUT1 = "OUTPUT1";
    @Output(OUTPUT1)
    MessageChannel output1();
}

