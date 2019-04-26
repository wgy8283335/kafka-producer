package com.sensetime.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface BucketRawImageSource {

    //String OUTPUT3 = "BucketRawImage";
    String OUTPUT3 = "OUTPUT3";
    @Output(OUTPUT3)
    MessageChannel output3();
}

