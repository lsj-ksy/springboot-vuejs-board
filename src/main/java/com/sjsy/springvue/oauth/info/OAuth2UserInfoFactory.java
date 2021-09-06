package com.sjsy.springvue.oauth.info;

import com.sjsy.springvue.domain.user.SocialType;
import com.sjsy.springvue.oauth.info.impl.GoogleOAuth2UserInfo;

import java.util.Map;

public interface OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(SocialType socialType, Map<String, Object> attributes) {
        switch (socialType) {
            case GOOGLE: return new GoogleOAuth2UserInfo(socialType, attributes);
            default: throw new IllegalArgumentException("Invalid Provider(Social) Type.");
        }
    }

}
