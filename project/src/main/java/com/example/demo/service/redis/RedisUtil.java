package com.example.demo.service.redis;

import com.nimbusds.openid.connect.sdk.federation.policy.operations.ValueOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class RedisUtil {
    private final StringRedisTemplate stringRedisTemplate;

    //key를 통해 value리턴
    public String getData(String key){
        ValueOperations<String, String> valueOperation = stringRedisTemplate.opsForValue();
        return valueOperation.get(key);
    }

    //유효시간동안 (key,value) 저장
    public void setDataExpire(String key,String value,long duration){
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(duration);
        valueOperations.set(key,value,expireDuration);
    }

    //Data 삭제
    public void deleteData(String key){
        stringRedisTemplate.delete(key);
    }
}
