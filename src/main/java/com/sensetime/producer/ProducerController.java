package com.sensetime.producer;

import com.sensetime.channel.AlarmPasserSource;
import com.sensetime.channel.BucketRawImageSource;
import com.sensetime.channel.FaceRawEventSource;
import com.sensetime.channel.SpecialPasserSource;
import com.sensetime.entityforkafka.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static com.sensetime.util.Img2Base64Util.getImgStr;

/**
 * 模拟发送kafka消息到相应队列
 */
@RestController
public class ProducerController {

    @Autowired
    private SendAlarmService sendAlarmService;

    @Autowired
    private SendSpecialService sendSpecialService;

    @Autowired
    private SendBucketService sendBucketService;

    @Autowired
    private SendFaceService sendFaceService;

    @RequestMapping(value = "/generate/alarm", method = RequestMethod.POST)
    public void sendAlarm(@RequestParam String cmd,@RequestParam  String lightId,@RequestParam  String lightName,@RequestParam  String lightType,
                          @RequestParam String personImage,@RequestParam  String personUUID,@RequestParam  String reason){
        String imgbase=getImgStr(personImage);
        Alarm alarm = new Alarm(cmd,lightId,lightName,lightType,imgbase,personUUID,reason);
        sendAlarmService.sendAlarm(alarm);
    }

    @RequestMapping(value = "/generate/special", method = RequestMethod.POST)
    public void sendSpecial(String cmd,String securityCheckId,String actionType1,String actionTarget11,String actionTarget12,
                            String actionContent1,String actionType2,String actionTarget21,String actionContent2,String blackDetectStatus,String nationStatus,
                            String whiteDetectStatus,String notInListStatus,String url,String image){

        sendSpecialService.sendSpecial(cmd,securityCheckId,actionType1,actionTarget11,actionTarget12,
                actionContent1,actionType2,actionTarget21,actionContent2,blackDetectStatus,nationStatus,
                whiteDetectStatus,notInListStatus,url,image);
    }

    @RequestMapping(value = "/generate/bucket", method = RequestMethod.POST)
    public void sendBucket(String cameraReferId,Integer cameraType,String capturedTime,String faceInfoImageUrl,Integer faceInfoTraceId,String faceImageTag,
                           Integer imageHeight ,Integer imageWeight,String imageUrl,Float score,String aliasname,String name,String serial,String tarLibName,
                           String tarLibSerial,String url,String personType,String idNumber,String icNumber){

        BucketRawImage info=new BucketRawImage(cameraReferId,cameraType,capturedTime,faceInfoImageUrl,faceInfoTraceId,faceImageTag,
                imageHeight ,imageWeight,imageUrl,score,aliasname,name,serial,tarLibName,tarLibSerial,url,personType,idNumber,icNumber);

        sendBucketService.sendBucket(info);
    }

    @RequestMapping(value = "/generate/face", method = RequestMethod.POST)
    public void sendFace(String cameraReferId,Integer cameraType,String capturedTime,String faceInfoImageUrl,Integer faceInfoTraceId,
                           Integer imageHeight ,Integer imageWeight,String imageUrl,Float score,String aliasname,String name,String serial,String tarLibName,
                           String tarLibSerial,String url,String personType,String idNumber,String icNumber){

        FaceRawEvent info=new FaceRawEvent(cameraReferId,cameraType,capturedTime,faceInfoImageUrl,faceInfoTraceId,
                imageHeight ,imageWeight,imageUrl,score,aliasname,name,serial,tarLibName,tarLibSerial,url,personType,idNumber,icNumber);

        sendFaceService.sendFace(info);
    }
}

@EnableBinding(AlarmPasserSource.class)
class SendAlarmService {
    @Autowired
    private AlarmPasserSource source;

    public void sendAlarm(Alarm alarm) {
        try {
            Event event = new Event<Alarm>("lightPublishEvent","lightPublishEvent", alarm);
            source.output1().send(MessageBuilder.withPayload(event).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

@EnableBinding(SpecialPasserSource.class)
class SendSpecialService {
    @Autowired
    private SpecialPasserSource source;

    public void sendSpecial(String cmd,String securityCheckId,String actionType1,String actionTarget11,String actionTarget12,
                            String actionContent1,String actionType2,String actionTarget21,String actionContent2,String blackDetectStatus,String nationStatus,
                            String whiteDetectStatus,String notInListStatus,String url,String image) {
        try {
            ArrayList<String> actionTarget1 = new ArrayList<String>();
            actionTarget1.add(actionTarget11);
            actionTarget1.add(actionTarget12);
            Action action1 = new Action(actionType1,actionTarget1,actionContent1);

            ArrayList<String> actionTarget2 = new ArrayList<String>();
            actionTarget2.add(actionTarget21);
            Action action2 = new Action(actionType2,actionTarget2,actionContent2);

            ArrayList<Action> publishActions = new ArrayList<Action>();
            publishActions.add(action1);
            publishActions.add(action2);

            String imgbase=getImgStr(image);

            Special special = new Special(cmd,securityCheckId,publishActions,blackDetectStatus,nationStatus,whiteDetectStatus,notInListStatus,url,imgbase);
            Event event = new Event<Special>("messagePublishEvent","messagePublishEvent", special);
            source.output2().send(MessageBuilder.withPayload(event).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
@EnableBinding(BucketRawImageSource.class)
class SendBucketService {
    @Autowired
    private BucketRawImageSource source;

    public void sendBucket(BucketRawImage info) {
        try {
            Event event = new Event<BucketRawImage>("faceRawImage","imageCapture", info);
            source.output3().send(MessageBuilder.withPayload(event).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
@EnableBinding(FaceRawEventSource.class)
class SendFaceService {
    @Autowired
    private FaceRawEventSource source;

    public void sendFace(FaceRawEvent info) {
        try {
            Event event = new Event<FaceRawEvent>("senseFaceEvent","patternDetected", info);
            source.output4().send(MessageBuilder.withPayload(event).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
