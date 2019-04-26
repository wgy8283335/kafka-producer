package com.sensetime.entityforkafka;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Camera{
    String referId;
    Integer type;
}
