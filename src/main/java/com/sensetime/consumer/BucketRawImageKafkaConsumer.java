package com.sensetime.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sensetime.channel.BucketRawImageSink;
import com.sensetime.channel.SpecialPasserSource;
import com.sensetime.entity.Device;
import com.sensetime.service.DeviceService;
import com.sensetime.util.Img2Base64Util;
import com.sensetime.entityforkafka.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@EnableBinding(BucketRawImageSink.class)
public class BucketRawImageKafkaConsumer {

    @Autowired
    SendSpecialService sendSpecialService;

    @Autowired
    DeviceService deviceService;

    @StreamListener(BucketRawImageSink.INPUT3)
    public void handlerAlarm(String message) {

        //Received
        Event<String> bucketRawImageEvent = JSON.parseObject(message, new TypeReference<Event<String>>() {});
        if(!"faceRawImage".equals(bucketRawImageEvent.getEventName())){
            return;
        }
        String bucketRawImageStr = bucketRawImageEvent.getData();
        JSONObject map = JSONObject.parseObject(bucketRawImageStr);
        Image image = JSON.parseObject(map.get("image").toString(), new TypeReference<Image>() {});
        Camera camera = JSON.parseObject(map.get("camera").toString(), new TypeReference<Camera>() {});
        List<DetectTaskInfo> detectTaskInfos = JSON.parseObject(map.get("detectTaskInfos").toString(), new TypeReference<List<DetectTaskInfo>>() {});

        //Send

        String tarLibName = detectTaskInfos.get(0).getRecognisedInfos().get(0).getSimilars().get(0).getTarget().getTarLibName();
        String blackDetectStatus = "";
        if(tarLibName.isEmpty()){
            return;
        }
        if("MetroBlackList".equals(tarLibName)){
            blackDetectStatus = "2";
        }else if("PoliceBlackList".equals(tarLibName)){
            blackDetectStatus = "1";
        }else{
            blackDetectStatus = "3";
        }

        String imageUrl = image.getUrl();
        String imageInBase64 = "";
        if(imageUrl.isEmpty()){
            return;
        }
        try{
            imageInBase64 = Img2Base64Util.getImgFromUrlToBase64(imageUrl);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }

        Device device = deviceService.getById(camera.getReferId());
        String securityCheckId = "";
        if(device != null){
            securityCheckId = device.getDeviceSerialNo().substring(0,11);
        }

        sendSpecialService.sendSpecial("messagePublish",securityCheckId,blackDetectStatus,imageUrl,imageInBase64);

    }

    @EnableBinding(SpecialPasserSource.class)
    class SendSpecialService {
        @Autowired
        private SpecialPasserSource source;

        public void sendSpecial(String cmd,String securityCheckId,String actionType1,String actionTarget11,String actionTarget12,
                                String actionContent1,String actionType2,String actionTarget21,String actionContent2,String blackDetectStatus,String nationStatus,
                                String whiteDetectStatus,String notInListStatus,String imageUrl,String image) {
            try {
                ArrayList<String> actionTarget1 = new ArrayList<String>();
                actionTarget1.add(actionTarget11);
                actionTarget1.add(actionTarget12);
                Action action1 = new Action(actionType1,actionTarget1,actionContent1);

                ArrayList<Action> publishActions = new ArrayList<Action>();
                publishActions.add(action1);

                Special special = new Special(cmd,securityCheckId,publishActions,blackDetectStatus,nationStatus,whiteDetectStatus,notInListStatus,imageUrl,image);
                Event event = new Event<Special>("messagePublishEvent","messagePublishEvent", special);
                source.output2().send(MessageBuilder.withPayload(event).build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void sendSpecial(String cmd,String securityCheckId,String blackDetectStatus,String imageUrl,String image){
            sendSpecial("messagePublish",securityCheckId,"","","","","","","",
                    blackDetectStatus,"","","",imageUrl,image);
        }
    }
}