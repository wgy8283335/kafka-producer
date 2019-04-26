package com.sensetime.entityforkafka;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Special {
        String cmd;
        String securityCheckId;
        ArrayList<Action> publishActions;
        String blackDetectStatus;
        String nationStatus;
        String whiteDetectStatus;
        String notInListStatus;
        String imageUrl;
        String image;
}

