package com.sensetime.entityforkafka;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpecialFaceInfo {
    SpecialInnerImage image;
    Integer traceId;
}
