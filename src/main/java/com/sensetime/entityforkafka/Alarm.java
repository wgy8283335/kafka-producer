package com.sensetime.entityforkafka;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Alarm {
        String cmd;
        String lightId;
        String lightName;
        String lightType;
        String personImage;
        String personUUID;
        String reason;
}
