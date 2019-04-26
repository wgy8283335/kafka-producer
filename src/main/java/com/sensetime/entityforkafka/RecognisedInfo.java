package com.sensetime.entityforkafka;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecognisedInfo{
    List<Similar> similars;
}
