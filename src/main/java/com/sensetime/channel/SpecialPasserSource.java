package com.sensetime.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SpecialPasserSource {

    //String OUTPUT2 = "SpecialPasserTopic";
    String OUTPUT2 = "OUTPUT2";
    @Output(OUTPUT2)
    MessageChannel output2();

}

