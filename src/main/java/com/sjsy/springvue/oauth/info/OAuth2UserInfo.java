package com.sjsy.springvue.oauth.info;

import com.sjsy.springvue.domain.user.RoleType;
import com.sjsy.springvue.domain.user.SocialType;
import com.sjsy.springvue.domain.user.User;

import java.util.Map;

public abstract class OAuth2UserInfo {

    protected SocialType socialType;

    protected Map<String, Object> attributes;

    public OAuth2UserInfo(SocialType socialType, Map<String, Object> attributes) {
        this.socialType = socialType;
        this.attributes = attributes;
    }

    public SocialType getSocialType() { return socialType; }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getImageUrl();

    public User toEntity() {
        return User.builder()
                .socialKey(getId())
                .socialType(getSocialType())
                .username(getName())
                .email(getEmail())
                .roleType(RoleType.GUEST)
                .build();
    }

}
