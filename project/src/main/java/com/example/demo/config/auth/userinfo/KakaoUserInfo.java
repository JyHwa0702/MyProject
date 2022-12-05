package com.example.demo.config.auth.userinfo;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo{
    private Map<String,Object> attributes; //OAuth2User.getAttributes();
    private Map<String,Object> attributesResponse;

    public KakaoUserInfo(Map<String,Object> attributes){
        this.attributes = (Map<String, Object>) attributes.get("response");
        this.attributesResponse = (Map<String, Object>) attributes.get("response");
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getProviderId() {
        return attributesResponse.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return attributesResponse.get("email").toString();
    }

    @Override
    public String getName() {
        return attributesResponse.get("name").toString();
    }
}
