package com.sensetime.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface BucketRawImageSink {

    //String INPUT3 = "BucketRawImage";
    String INPUT3 = "INPUT3";
    @Input(BucketRawImageSink.INPUT3)
    SubscribableChannel input3();

}
