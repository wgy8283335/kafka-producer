package com.sensetime.entityforkafka;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Target{
    String aliasname;
    String name;
    String serial;
    String tarLibName;
    String tarLibSerial;
    String url;
    String personType;
    String idNumber;
    String icNumber;
}
