package com.sensetime.entityforkafka;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
public class FaceRawEvent {
    Camera camera;
    String capturedTime;
    SpecialFaceInfo faceInfo;
    Image image;
    List<DetectTaskInfo> detectTaskInfos;

    public FaceRawEvent(String cameraReferId,Integer cameraType,String capturedTime,String faceInfoImageUrl,Integer faceInfoTraceId,
                          Integer imageHeight ,Integer imageWeight,String imageUrl,Float score,String aliasname,String name,String serial,String tarLibName,
                          String tarLibSerial,String url,String personType,String idNumber,String icNumber){
        Camera camera = new Camera(cameraReferId,cameraType);
        this.camera = camera;
        this.capturedTime = capturedTime;
        //SpecialInnerImage innerImage = new SpecialInnerImage();
        SpecialInnerImage innerImage = null;
        SpecialFaceInfo faceInfo = new SpecialFaceInfo(innerImage,faceInfoTraceId);
        this.faceInfo = faceInfo;
        Image image = new Image(imageHeight,imageWeight,imageUrl);
        this.image = image;
        Target target = new Target(aliasname,name,serial,tarLibName,tarLibSerial,url,personType,idNumber,icNumber);
        Similar similar = new Similar(score,target);
        List<Similar> similars = new ArrayList<>();
        similars.add(similar);
        RecognisedInfo recognisedInfo= new RecognisedInfo(similars);
        List<RecognisedInfo> recognisedInfos = new ArrayList<>();
        recognisedInfos.add(recognisedInfo);
        DetectTaskInfo detectTaskInfo = new DetectTaskInfo(recognisedInfos);
        List<DetectTaskInfo> detectTaskInfos = new ArrayList<>();
        detectTaskInfos.add(detectTaskInfo);
        this.detectTaskInfos = detectTaskInfos;
    }
}
