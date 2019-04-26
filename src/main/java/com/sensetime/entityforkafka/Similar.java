package com.sensetime.entityforkafka;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Similar{
    Float score;
    Target target;
}
