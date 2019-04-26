package com.sensetime.entityforkafka;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event<T> {
    	private String eventName;
    	private String eventAction;
    	private T data;
}
