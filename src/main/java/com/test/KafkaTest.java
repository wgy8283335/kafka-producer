package com.test;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaTest extends Thread {
    // 声明KafkaProducer类型的全局变量，由于在多线程环境，所以声明为final类型
    private final KafkaProducer<String,String> producer;
    /* 声明用于存储topic名称的全局变量，由于在多线程环境下，声明为final类型 */
    private final String topic;
    //设置一次发送消息的条数
    private final int messageNumToSend = 1;

    /**
     * 在构造函数中创建KafkaProducer对象
     * @param topicName topic名称
     */
    public KafkaTest(String topicName){
        // 配置信息
        Properties kafkaProps = new Properties();
        //kafkaProps.put("bootstrap.servers", "192.168.1.158:32400,192.168.1.158:32401,193.168.1.158:32402");
        kafkaProps.put("bootstrap.servers", "192.168.1.154:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(kafkaProps);
        //设置全局变量topic的值
        topic = topicName;
    }
    /**
     * 生产者线程执行函数，循环发送消息
     */
    @Override
    public void run() {
        //用于记录发送消息的条数，同时作为消息的key值
        int messageNo = 0;
        while (messageNo < messageNumToSend){
            //所发送消息的内容
             String tagMessage = "{\n" +
                     "  \"eventName\": \"EventHandler\",\n" +
                     "  \"eventAction\": \"FaceTagImage\",\n" +
                     "  \"data\": {\n" +
                     "    \"triggerTime\": \"2019-04-10 16:11:16\",\n" +
                     "    \"triggerDevice\": {\n" +
                     "      \"deviceId\": \"C030101B1C10100\",\n" +
                     "      \"deviceType\": \"3\",\n" +
                     "      \"deviceSerial\": \"\",\n" +
                     "      \"deviceTag\": \"C030101B1C1\"\n" +
                     "    },\n" +
                     "    \"personId\": \"5b064c92-6077-4909-be7a-191067fd60a2\",\n" +
                     "    \"personName\": \"\",\n" +
                     "    \"personType\": \"\",\n" +
                     "    \"personFeature\": \"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\\u003d\",\n" +
                     "    \"version\": 24601,\n" +
                     "    \"triggerImageUrl\": \"/images/metro/C030101B1C1/metal/2019-04-03/1554245416435.jpg\",\n" +
                     "    \"triggerImageTag\": \"metal\"\n" +
                     "  }\n" +
                     "}";

             String testMessage = "{\n" +
                     "  \"eventName\": \"EventHandler\",\n" +
                     "  \"eventAction\": \"FaceVerificationEvent\",\n" +
                     "  \"data\": {\n" +
                     "    \"triggerTime\": \"2019-04-02 12:17:41\",\n" +
                     "    \"triggerDevice\": {\n" +
                     "      \"deviceId\": \"C030101B1C10100\",\n" +
                     "      \"deviceType\": \"\",\n" +
                     "      \"deviceSerial\": \"\",\n" +
                     "      \"deviceTag\": \"C030101B1C1\"\n" +
                     "    },\n" +
                     "    \"personId\": \"\",\n" +
                     "    \"personName\": \"\",\n" +
                     "    \"personType\": \"\",\n" +
                     "    \"personFeature\": \"Wy0wLjA0MjIzODMsLTAuMDE3NjQ1NCwwLjA4OTYyMjUsMC4wMDkyNTk0NSwtMC4wODIxNDE2LC0wLjA2OTUxNDcsMC4wNDM1NzMzLDAuMDIxNTg5MiwtMC4wOTE1Mjc4LC0wLjEyNTkyNSwtMC4wNjkxOTU4LC0wLjAzNzM4OTIsLTAuMDI3NDUzOSwwLjA2MjYxODcsMC4wMzIyNTMyLC0wLjA5NTUwMTEsMC4wODUxMTY3LC0wLjA3MDg5MywtMC4wNDg1MDE1LDAuMDE5Nzg5NywtMC4wNDQxNTA0LDAuMDAwMjY2NDk5LDAuMDY2MDAxMywtMC4wMDM2MjQxOCwtMC4wNTEyMTAxLC0wLjAwMjYxNjEyLDAuMDA3MDIzNjgsLTAuMDEwOTY4MywwLjAwODU4NTE1LC0wLjA1MTEwMzYsMC4wMTY1MzMzLDAuMDU4NDYzOCwtMC4wMTUyMjcsMC4xMTIyNzEsLTAuMDA4NTczMzEsLTAuMDAzNTkyNywtMC4wNDU3Njk4LC0wLjAzOTA4MjksMC4wMzQ2Nzg0LC0wLjAzNDYxMTksMC4wNjQ4MjA3LDAuMDA0MzUwMzgsMC4wODU0MTU3LC0wLjA5OTkwMjEsMC4xOTczMTgsLTAuMTA5MjA4LC0wLjEwNjg5MSwwLjAwODA4Mjg4LC0wLjAyMTQxMTksMC4wMzQxOTgsLTAuMDQ3MzU3MiwtMC4wMTU2MDI4LC0wLjA3NzcxMjcsMC4wMzM0NTg3LDAuMDE2MTg1NCwwLjA3NDc2ODMsLTAuMDIyNTA5NiwtMC4wMjU2Mjk1LC0wLjA1NTExNzksLTAuMDM0Nzg2NywwLjA3MTMzMTgsMC4wNDUzMzc2LDAuMDI1NjQxOCwwLjAzNjk0MDMsMC4wMDEyOTI0NCwwLjAyODc2NzMsLTAuMDA0NDc3ODQsMC4wNDc1MTk3LC0wLjAwMjMsLTAuMDA0NzAwNzYsLTAuMDI0NywtMC4wNzI5Nzk0LC0wLjEyMjMzOCwtMC4xMDA4MDUsLTAuMDIxMTIzOSwtMC4wMzY1NTA0LDAuMDI3NDA0MSwwLjAyNTU4MDYsMC4wNzA2NTE5LDAuMTA1MDMxLC0wLjA2Njg3MjksLTAuMTA3MjI2LDAuMDE4NDQ4NCwtMC4wMzI2ODYyLDAuMTA5MDExLDAuMDUyMjQ5NywtMC4wODA1MzQxLC0wLjAyNjc3MzgsLTAuMTE1MDk3LDAuMDc3MDcxMSwtMC4wNDg3ODc0LDAuMDQ5NzE2NCwtMC4wMjQzODY5LC0wLjAzMTc3NjYsMC4wMjA0MTg5LDAuMDkwMjg5NywwLjA2MDczNDgsMC4wMzk0NzMxLC0wLjA4NTY2NjMsLTAuMDE1MTEzNiwwLjA0ODMwMDgsMC4wMTgzNTA0LC0wLjAwNzg5Njk4LDAuMDUzOTcwMSwtMC4wNzk3Mzc4LC0wLjAzNDk3NjYsMC4wMDE0ODUzOSwtMC4xMzk2MjMsMC4wNjUyMjY1LDAuMDA3MDAxNzcsLTAuMTYwMzc3LC0wLjA2OTExODIsMC4wMTMwNjYzLC0wLjAzNzExMjksMC4wMTk4MjI0LC0wLjAyODkxNzIsMC4wNTQ3NjA1LDAuMDI2OTI4MiwtMC4wMjIwMzQ4LDAuMDcyOTc5NCwwLjAyNDQzMzgsLTAuMDEzODg4MiwwLjAwNzYxODksMC4wODk4MjM1LC0wLjEyMzc1MSwwLjA5NjI1MTUsLTAuMDAzMjAwMjUsMC4wNTQxNTM3LDAuMDI1NDAwOCwwLjA5Njc2NDMsMC4wMTY2OTg1LC0wLjEwNzg3NywwLjAzOTc0MjksMC4wODcyOTUzLC0wLjAzODU3NzksLTAuMDU2OTM2NiwwLjAxOTc1OSwwLjA4MDAxMDksLTAuMDExNTQ4NCwtMC4xMDI1ODUsMC4wODA4ODIyLDAuMTI2NzMxLC0wLjAyODcxMjQsMC4wMjU4MTk0LDAuMDU4MDE0MiwwLjAxMjgyOCwtMC4wOTAyOTk2LC0wLjAyOTU4MTksLTAuMDE3NzQ1NiwtMC4wNDEzOTQsMC4wMzQ5MjgsLTAuMTU1Mzc5LC0wLjA2ODY5OCwwLjA0NzA4OTIsLTAuMDMzMDIxNywtMC4wMTEwMjE0LC0wLjEwMjQxNywtMC4wNjE2NzE2LC0wLjA4MDMwMDgsLTAuMDA5Mjc4MzQsLTAuMDUyMDY2LC0wLjAwMDczMjc0NiwwLjAxNDAyMzcsMC4wMTI3MjI0LDAuMDc3OTYyNSwwLjA0MzY0NTYsMC4wMDU0Nzk2LC0wLjA2MzgwNjksMC4wMTIyNTc5LDAuMDgwNDc2NCwwLjAxNjgxMzksMC4wNTc2NTI5LDAuMDA0MDg4NDIsLTAuMDgyODcwMSwtMC4wMTAwMTEzLC0wLjAxMzE0MjgsMC4wOTM2Mzg5LC0wLjAyNjI2NTUsLTAuMDQ2NjUxNywwLjAyNTEyOTIsLTAuMDA1ODU5NywtMC4xMDQ0MTIsMC4xMDY4NTIsMC4wMzA4OTQsLTAuMDIxODk4NSwwLjAxMTc1ODIsLTAuMDk3MTUwMiwwLjA2NjE2LC0wLjAyMzE4MTEsLTAuMDQ0MTAxMywwLjAwNzUxMzM2LDAuMDM3MTUwMiwwLjA2OTU4NjIsMC4wMTMxNjM3LDAuMDY2NjEyOSwwLjA5MDgwMTMsLTAuMDExMTEzMSwtMC4wNDE5ODI0LC0wLjAzMjE0NzYsLTAuMDg0MTc0NCwtMC4wMjMyMzM1LDAuMDY5MTAyMywtMC4wMDM1Mjk0NywtMC4wNTE4NDE5LDAuMDU4MzYzLDAuMTA3NjAzLC0wLjAxNDg5NSwwLjA1MDg2NTMsLTAuMTA3NzA3LDAuMDczNTk0OCwwLjAzNzM1OCwwLjAxMTQ0MzgsLTAuMDI3NTY5OCwtMC4wOTkyNjk0LDAuMDE4NDI5MiwwLjAwNzE0MTU3LC0wLjExNTIyMywtMC4xMzk4MzQsLTAuMDI3MTMyMywtMC4wMjI0MjE0LC0wLjAzNTM3NzEsMC4wMDI0NDg2MiwwLjA0OTU3MjgsLTAuMDU4MTMxOCwtMC4wMzY4NTY3LDAuMDE4MDY5OCwwLjExNzYwMywwLjAzNjE2OTMsLTAuMTMzNzI4LC0wLjA2MDI3MTYsLTAuMDYyMDA3MSwwLjA2NDA2MjgsMC4wNTgxODU3LC0wLjAwOTI4NzE2LDAuMDIxODM1OCwtMC4wNzcwNTQ3LC0wLjAyNjcyMDksLTAuMTE3MTM2LC0wLjAwNjc2ODI3LDAuMTIzNTY5LDAuMDM3OTIyMiwtMC4xMDczNjQsMC4wMjkwODQ5LC0wLjAyMTU0NzYsLTAuMDMzMjQ2NiwwLjAzNDM4MjEsMC4wNDAyNzg0LC0wLjAxNzAyOTIsLTAuMDM5NTYxNywtMC4xNzA1NTQsMC4wNzE3ODg1LC0wLjA0MDQzLC0wLjA1ODM1MzcsLTAuMDczNzE0NywtMC4wMjY4NDY0LC0wLjAzNTQ0ODld\",\n" +
                     "    \"version\": 24503,\n" +
                     "    \"triggerImageUrl\": \"/home/shangtang/qtprojects/whole-compare/images/metro/C030101B1C10100/mblack/2019-03-28/1553761541.jpg\",\n" +
                     "    \"triggerImageTag\": \"mblack\"\n" +
                     "  }\n" +
                     "}";

             String tt =" {\n" +
                     "      \"video_source\": \"rtsp://admin:admin1234@10.8.29.171\",\n" +
                     "      \"context_id\": 0,\n" +
                     "      \"context\": {\n" +
                     "        \"video_width\": 1920,\n" +
                     "        \"video_height\": 1080,\n" +
                     "        \"min_face_size\": 10,\n" +
                     "        \"max_face_size\": 500,\n" +
                     "        \"max_buffer_size\": 10,\n" +
                     "        \"max_tracklet_num\": 25,\n" +
                     "        \"max_tracklet_item_size\": 5,\n" +
                     "        \"quick_response_time\": 1,\n" +
                     "        \"quality_thresh\": 0.3,\n" +
                     "        \"expand_ratio\": 1.2,\n" +
                     "        \"drop_thresh\": 40,\n" +
                     "        \"roi\": \"(1,1),(1918, 1),(1918, 1078),(1, 1078)\"\n" +
                     "      }";

             String taskMessage1 = "{\n" +
                     "  \"eventAction\": \"CREATE_TASK\",\n" +
                     "  \"eventName\": \"BLACK_WHITE_LIST_TASK\",\n" +
                     "  \"task\": {\n" +
                     "    \"accountForIp\": \"admin\",\n" +
                     "    \"createTime\": 1554983993736,\n" +
                     "    \"deviceId\": \"42a20f7c3d5547bd9676f1a6e9356436\",\n" +
                     "    \"deviceIp\": \"192.168.1.109\",\n" +
                     "    \"deviceSerialNumber\": \"C100101D0C40201\",\n" +
                     "    \"id\": 29,\n" +
                     "    \"isDeleted\": false,\n" +
                     "    \"metroLine\": \"eb26dee9db3340948e3c9567d9ab2ee5\",\n" +
                     "    \"metroSite\": \"a7c1a5ed47d34dd0973cb21dc0dd289f\",\n" +
                     "    \"modifiedTime\": 1554983993736,\n" +
                     "    \"passwordForIp\": \"admin1234\",\n" +
                     "    \"polygons\": [],\n" +
                     "    \"status\": 0,\n" +
                     "    \"taskSource\": \"DLC\",\n" +
                     "    \"taskType\": \"BLACK_WHITE_LIST_TASK\"\n" +
                     "  }\n" +
                     "}";

            String taskMessage2 = "{\n" +
                    "  \"eventAction\": \"CREATE_TASK\",\n" +
                    "  \"eventName\": \"BLACK_WHITE_LIST_TASK\",\n" +
                    "  \"task\": {\n" +
                    "    \"accountForIp\": \"admin\",\n" +
                    "    \"createTime\": 1554983993736,\n" +
                    "    \"deviceId\": \"52a20f7c3d5547bd9676f1a6e9356436\",\n" +
                    "    \"deviceIp\": \"192.168.1.110\",\n" +
                    "    \"deviceSerialNumber\": \"C100101D0C40202\",\n" +
                    "    \"id\": 30,\n" +
                    "    \"isDeleted\": false,\n" +
                    "    \"metroLine\": \"eb26dee9db3340948e3c9567d9ab2ee5\",\n" +
                    "    \"metroSite\": \"a7c1a5ed47d34dd0973cb21dc0dd289f\",\n" +
                    "    \"modifiedTime\": 1554983993736,\n" +
                    "    \"passwordForIp\": \"admin1234\",\n" +
                    "    \"polygons\": [],\n" +
                    "    \"status\": 0,\n" +
                    "    \"taskSource\": \"DLC\",\n" +
                    "    \"taskType\": \"BLACK_WHITE_LIST_TASK\"\n" +
                    "  }\n" +
                    "}";
            String taskMessage3 = "{\n" +
                    "  \"eventAction\": \"CREATE_TASK\",\n" +
                    "  \"eventName\": \"BLACK_WHITE_LIST_TASK\",\n" +
                    "  \"task\": {\n" +
                    "    \"accountForIp\": \"admin\",\n" +
                    "    \"createTime\": 1554983993736,\n" +
                    "    \"deviceId\": \"62a20f7c3d5547bd9676f1a6e9356436\",\n" +
                    "    \"deviceIp\": \"192.168.1.108\",\n" +
                    "    \"deviceSerialNumber\": \"C100101D0C40203\",\n" +
                    "    \"id\": 31,\n" +
                    "    \"isDeleted\": false,\n" +
                    "    \"metroLine\": \"eb26dee9db3340948e3c9567d9ab2ee5\",\n" +
                    "    \"metroSite\": \"a7c1a5ed47d34dd0973cb21dc0dd289f\",\n" +
                    "    \"modifiedTime\": 1554983993736,\n" +
                    "    \"passwordForIp\": \"admin1234\",\n" +
                    "    \"polygons\": [],\n" +
                    "    \"status\": 0,\n" +
                    "    \"taskSource\": \"DLC\",\n" +
                    "    \"taskType\": \"BLACK_WHITE_LIST_TASK\"\n" +
                    "  }\n" +
                    "}";
            String taskMessage4 = "{\n" +
                    "  \"eventAction\": \"CREATE_TASK\",\n" +
                    "  \"eventName\": \"BLACK_WHITE_LIST_TASK\",\n" +
                    "  \"task\": {\n" +
                    "    \"accountForIp\": \"admin\",\n" +
                    "    \"createTime\": 1554983993736,\n" +
                    "    \"deviceId\": \"72a20f7c3d5547bd9676f1a6e9356436\",\n" +
                    "    \"deviceIp\": \"10.8.29.173\",\n" +
                    "    \"deviceSerialNumber\": \"C100101D0C40204\",\n" +
                    "    \"id\": 32,\n" +
                    "    \"isDeleted\": false,\n" +
                    "    \"metroLine\": \"eb26dee9db3340948e3c9567d9ab2ee5\",\n" +
                    "    \"metroSite\": \"a7c1a5ed47d34dd0973cb21dc0dd289f\",\n" +
                    "    \"modifiedTime\": 1554983993736,\n" +
                    "    \"passwordForIp\": \"admin1234\",\n" +
                    "    \"polygons\": [],\n" +
                    "    \"status\": 0,\n" +
                    "    \"taskSource\": \"DLC\",\n" +
                    "    \"taskType\": \"BLACK_WHITE_LIST_TASK\"\n" +
                    "  }\n" +
                    "}";
            String taskMessage5 = "{\n" +
                    "  \"eventAction\": \"CREATE_TASK\",\n" +
                    "  \"eventName\": \"BLACK_WHITE_LIST_TASK\",\n" +
                    "  \"task\": {\n" +
                    "    \"accountForIp\": \"admin\",\n" +
                    "    \"createTime\": 1554983993736,\n" +
                    "    \"deviceId\": \"75a20f7c3d5547bd9676f1a6e9356436\",\n" +
                    "    \"deviceIp\": \"10.8.29.175\",\n" +
                    "    \"deviceSerialNumber\": \"C100101D0C40205\",\n" +
                    "    \"id\": 35,\n" +
                    "    \"isDeleted\": false,\n" +
                    "    \"metroLine\": \"eb26dee9db3340948e3c9567d9ab2ee5\",\n" +
                    "    \"metroSite\": \"a7c1a5ed47d34dd0973cb21dc0dd289f\",\n" +
                    "    \"modifiedTime\": 1554983993736,\n" +
                    "    \"passwordForIp\": \"admin1234\",\n" +
                    "    \"polygons\": [],\n" +
                    "    \"status\": 0,\n" +
                    "    \"taskSource\": \"DLC\",\n" +
                    "    \"taskType\": \"BLACK_WHITE_LIST_TASK\"\n" +
                    "  }\n" +
                    "}";
            String taskMessage6 = "{\n" +
                    "  \"eventAction\": \"CREATE_TASK\",\n" +
                    "  \"eventName\": \"BLACK_WHITE_LIST_TASK\",\n" +
                    "  \"task\": {\n" +
                    "    \"accountForIp\": \"admin\",\n" +
                    "    \"createTime\": 1554983993736,\n" +
                    "    \"deviceId\": \"76a20f7c3d5547bd9676f1a6e9356436\",\n" +
                    "    \"deviceIp\": \"10.8.29.176\",\n" +
                    "    \"deviceSerialNumber\": \"C100101D0C40206\",\n" +
                    "    \"id\": 36,\n" +
                    "    \"isDeleted\": false,\n" +
                    "    \"metroLine\": \"eb26dee9db3340948e3c9567d9ab2ee5\",\n" +
                    "    \"metroSite\": \"a7c1a5ed47d34dd0973cb21dc0dd289f\",\n" +
                    "    \"modifiedTime\": 1554983993736,\n" +
                    "    \"passwordForIp\": \"admin1234\",\n" +
                    "    \"polygons\": [],\n" +
                    "    \"status\": 0,\n" +
                    "    \"taskSource\": \"DLC\",\n" +
                    "    \"taskType\": \"BLACK_WHITE_LIST_TASK\"\n" +
                    "  }\n" +
                    "}";
            String taskMessage7 = "{\n" +
                    "  \"eventAction\": \"CREATE_TASK\",\n" +
                    "  \"eventName\": \"BLACK_WHITE_LIST_TASK\",\n" +
                    "  \"task\": {\n" +
                    "    \"accountForIp\": \"admin\",\n" +
                    "    \"createTime\": 1554983993736,\n" +
                    "    \"deviceId\": \"77a20f7c3d5547bd9676f1a6e9356436\",\n" +
                    "    \"deviceIp\": \"10.8.29.177\",\n" +
                    "    \"deviceSerialNumber\": \"C100101D0C40207\",\n" +
                    "    \"id\": 37,\n" +
                    "    \"isDeleted\": false,\n" +
                    "    \"metroLine\": \"eb26dee9db3340948e3c9567d9ab2ee5\",\n" +
                    "    \"metroSite\": \"a7c1a5ed47d34dd0973cb21dc0dd289f\",\n" +
                    "    \"modifiedTime\": 1554983993736,\n" +
                    "    \"passwordForIp\": \"admin1234\",\n" +
                    "    \"polygons\": [],\n" +
                    "    \"status\": 0,\n" +
                    "    \"taskSource\": \"DLC\",\n" +
                    "    \"taskType\": \"BLACK_WHITE_LIST_TASK\"\n" +
                    "  }\n" +
                    "}";
            String taskMessage8 = "{\n" +
                    "  \"eventAction\": \"CREATE_TASK\",\n" +
                    "  \"eventName\": \"BLACK_WHITE_LIST_TASK\",\n" +
                    "  \"task\": {\n" +
                    "    \"accountForIp\": \"admin\",\n" +
                    "    \"createTime\": 1554983993736,\n" +
                    "    \"deviceId\": \"78a20f7c3d5547bd9676f1a6e9356436\",\n" +
                    "    \"deviceIp\": \"10.8.29.178\",\n" +
                    "    \"deviceSerialNumber\": \"C100101D0C40208\",\n" +
                    "    \"id\": 38,\n" +
                    "    \"isDeleted\": false,\n" +
                    "    \"metroLine\": \"eb26dee9db3340948e3c9567d9ab2ee5\",\n" +
                    "    \"metroSite\": \"a7c1a5ed47d34dd0973cb21dc0dd289f\",\n" +
                    "    \"modifiedTime\": 1554983993736,\n" +
                    "    \"passwordForIp\": \"admin1234\",\n" +
                    "    \"polygons\": [],\n" +
                    "    \"status\": 0,\n" +
                    "    \"taskSource\": \"DLC\",\n" +
                    "    \"taskType\": \"BLACK_WHITE_LIST_TASK\"\n" +
                    "  }\n" +
                    "}";
            String taskMessage9 = "{\n" +
                    "  \"eventAction\": \"CREATE_TASK\",\n" +
                    "  \"eventName\": \"BLACK_WHITE_LIST_TASK\",\n" +
                    "  \"task\": {\n" +
                    "    \"accountForIp\": \"admin\",\n" +
                    "    \"createTime\": 1554983993736,\n" +
                    "    \"deviceId\": \"79a20f7c3d5547bd9676f1a6e9356436\",\n" +
                    "    \"deviceIp\": \"10.8.29.179\",\n" +
                    "    \"deviceSerialNumber\": \"C100101D0C40209\",\n" +
                    "    \"id\": 39,\n" +
                    "    \"isDeleted\": false,\n" +
                    "    \"metroLine\": \"eb26dee9db3340948e3c9567d9ab2ee5\",\n" +
                    "    \"metroSite\": \"a7c1a5ed47d34dd0973cb21dc0dd289f\",\n" +
                    "    \"modifiedTime\": 1554983993736,\n" +
                    "    \"passwordForIp\": \"admin1234\",\n" +
                    "    \"polygons\": [],\n" +
                    "    \"status\": 0,\n" +
                    "    \"taskSource\": \"DLC\",\n" +
                    "    \"taskType\": \"BLACK_WHITE_LIST_TASK\"\n" +
                    "  }\n" +
                    "}";
            String taskMessage10 = "{\n" +
                    "  \"eventAction\": \"CREATE_TASK\",\n" +
                    "  \"eventName\": \"BLACK_WHITE_LIST_TASK\",\n" +
                    "  \"task\": {\n" +
                    "    \"accountForIp\": \"admin\",\n" +
                    "    \"createTime\": 1554983993736,\n" +
                    "    \"deviceId\": \"80a20f7c3d5547bd9676f1a6e9356436\",\n" +
                    "    \"deviceIp\": \"10.8.29.180\",\n" +
                    "    \"deviceSerialNumber\": \"C100101D0C40210\",\n" +
                    "    \"id\": 40,\n" +
                    "    \"isDeleted\": false,\n" +
                    "    \"metroLine\": \"eb26dee9db3340948e3c9567d9ab2ee5\",\n" +
                    "    \"metroSite\": \"a7c1a5ed47d34dd0973cb21dc0dd289f\",\n" +
                    "    \"modifiedTime\": 1554983993736,\n" +
                    "    \"passwordForIp\": \"admin1234\",\n" +
                    "    \"polygons\": [],\n" +
                    "    \"status\": 0,\n" +
                    "    \"taskSource\": \"DLC\",\n" +
                    "    \"taskType\": \"BLACK_WHITE_LIST_TASK\"\n" +
                    "  }\n" +
                    "}";
            //构造消息记录
            ProducerRecord<String,String> record1 = new ProducerRecord<>(topic,taskMessage1);
//            ProducerRecord<String,String> record2 = new ProducerRecord<>(topic,taskMessage2);
//            ProducerRecord<String,String> record3 = new ProducerRecord<>(topic,taskMessage3);
//            ProducerRecord<String,String> record4 = new ProducerRecord<>(topic,taskMessage4);
//            ProducerRecord<String,String> record5 = new ProducerRecord<>(topic,taskMessage5);
//            ProducerRecord<String,String> record6 = new ProducerRecord<>(topic,taskMessage6);
//            ProducerRecord<String,String> record7 = new ProducerRecord<>(topic,taskMessage7);
//            ProducerRecord<String,String> record8 = new ProducerRecord<>(topic,taskMessage8);
//            ProducerRecord<String,String> record9 = new ProducerRecord<>(topic,taskMessage9);
//            ProducerRecord<String,String> record10 = new ProducerRecord<>(topic,taskMessage10);
            //发送
            producer.send(record1);
//            producer.send(record2);
//            producer.send(record3);
//            producer.send(record4);
//            producer.send(record5);
//            producer.send(record6);
//            producer.send(record7);
//            producer.send(record8);
//            producer.send(record9);
//            producer.send(record10);
            //累加
            messageNo++;
        }
        //刷新缓存，将消息发送到kafka集群
        producer.flush();
    }

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        for (int i=0;i<1;i++){
            new KafkaTest("BucketTask").start();
        }

        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);

    }
}