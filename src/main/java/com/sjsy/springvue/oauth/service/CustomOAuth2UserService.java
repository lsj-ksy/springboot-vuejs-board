package com.sjsy.springvue.oauth.service;

import com.sjsy.springvue.domain.user.*;
import com.sjsy.springvue.oauth.entity.UserPrincipal;
import com.sjsy.springvue.oauth.info.OAuth2UserInfo;
import com.sjsy.springvue.oauth.info.OAuth2UserInfoFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        SocialType socialType = SocialType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(socialType, user.getAttributes());

        User savedUser = userRepository.findBySocialKey(userInfo.getId());

        if (savedUser != null) {
            if (socialType != savedUser.getSocialType()) {
                throw new OAuth2AuthenticationException(
                        "Looks like you're signed up with" + socialType
                                + " account. Please use your " + savedUser.getSocialType() + " account to login."
                );
            }
            updateUser(savedUser, userInfo);
        } else {
            savedUser = createUser(userInfo, socialType);
        }

        return UserPrincipal.create(savedUser, user.getAttributes());
    }

    private User createUser(OAuth2UserInfo userInfo, SocialType socialType) {
        LocalDateTime now = LocalDateTime.now();
        User user = new User(
                userInfo.getSocialType(),
                userInfo.getId(),
                userInfo.getName(),
                "",
                RoleType.GUEST,
                "",
                userInfo.getEmail(),
                Gender.UNKNOWN,
                userInfo.getImageUrl()
        );

        return userRepository.saveAndFlush(user);
    }

    private User updateUser(User user, OAuth2UserInfo userInfo) {
        boolean nameCheck = userInfo.getName() != null && !user.getUsername().equals(userInfo.getName()) ? true : false;
        boolean imgCheck = userInfo.getImageUrl() != null && !user.getProfileImg().equals(userInfo.getImageUrl()) ? true : false;
        boolean emailCheck = userInfo.getEmail() != null && !user.getEmail().equals(userInfo.getEmail()) ? true : false;

        if (nameCheck || imgCheck || emailCheck) {
            user.update(userInfo.getName(), userInfo.getImageUrl(), userInfo.getEmail());
        }
        return user;
    }

}
