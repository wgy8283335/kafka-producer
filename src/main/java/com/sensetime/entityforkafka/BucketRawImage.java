package com.sensetime.entityforkafka;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
public class BucketRawImage {
    Camera camera;
    String capturedTime;
    FaceInfo faceInfo;
    String faceImageTag;
    Image image;
    List<DetectTaskInfo> detectTaskInfos;

    public BucketRawImage(String cameraReferId,Integer cameraType,String capturedTime,String faceInfoImageUrl,Integer faceInfoTraceId,String faceImageTag,
                   Integer imageHeight ,Integer imageWeight,String imageUrl,Float score,String aliasname,String name,String serial,String tarLibName,
                   String tarLibSerial,String url,String personType,String idNumber,String icNumber){
        Camera camera = new Camera(cameraReferId,cameraType);
        this.camera = camera;
        this.capturedTime = capturedTime;
        InnerImage innerImage = new InnerImage(faceInfoImageUrl);
        FaceInfo faceInfo = new FaceInfo(innerImage,faceInfoTraceId);
        this.faceInfo = faceInfo;
        this.faceImageTag = faceImageTag;
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

