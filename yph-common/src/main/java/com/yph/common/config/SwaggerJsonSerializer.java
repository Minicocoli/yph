package com.yph.common.config;

import springfox.documentation.spring.web.json.Json;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * swagger 用fastjson的序列化
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/2
 **/
//public class SwaggerJsonSerializer implements ObjectSerializer, ObjectDeserializer {
//
//    public final static SwaggerJsonSerializer instance = new SwaggerJsonSerializer();
//
//    @Override
//    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
//        SerializeWriter out = serializer.getWriter();
//        Json json = (Json) object;
//        out.write(json.value());
//    }
//
//
//    @Override
//    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
//        return null;
//    }
//
//    @Override
//    public int getFastMatchToken() {
//        return 0;
//    }
//}