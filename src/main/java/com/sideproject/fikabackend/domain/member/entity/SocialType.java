package com.sideproject.fikabackend.domain.member.entity;

public enum SocialType {

    KAKAO("kakao"),
    GOOGLE("google");

    private final String value;

    SocialType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
