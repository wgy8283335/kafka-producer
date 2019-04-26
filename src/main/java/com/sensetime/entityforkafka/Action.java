package com.sensetime.entityforkafka;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Action{
    String actionType;
    ArrayList<String> actionTarget;
    String actionContent;
}