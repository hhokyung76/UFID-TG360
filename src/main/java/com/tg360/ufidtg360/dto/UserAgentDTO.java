package com.tg360.ufidtg360.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserAgentDTO {

    private String browser;
    private String browserVersion;
    private String os;
    private String osVersion;
    private String device;

    private String tgMode;          //tgMode - 생성 모드 (M-1 : tgMid와 tgId 모두 생성, M-2: tgMid존재하면서 tgId미존재시 tgId만 생성)
    private String tgMid;           //tgMid
    private String tgId;            //tgId

    public static class Builder {
        private String browser = "";
        private String browserVersion = "";
        private String os = "";
        private String osVersion = "";
        private String device = "";

        private String tgMode = "";           //webview 에서 madid(헤더) 에 추가 요청
        private String tgMid = "";           //webview 에서 madid(헤더) 에 추가 요청
        private String tgId = "";       //webview 에서 madid(헤더) 에 추가 요청시 idtype 확인.

        public Builder(){
        }


        public Builder browser(String val) {
            browser = val;
            return this;
        }

        public Builder browserVersion(String val) {
            browserVersion = val;
            return this;
        }

        public Builder os(String val) {
            os = val;
            return this;
        }

        public Builder osVersion(String val) {
            osVersion = val;
            return this;
        }

        public Builder device(String val) {
            device = val;
            return this;
        }
        public Builder tgMode(String val) {
            tgMode = val;
            return this;
        }
        public Builder tgMid(String val) {
            tgMid = val;
            return this;
        }
        public Builder tgId(String val) {
            tgId = val;
            return this;
        }

        public UserAgentDTO build() {
            return new UserAgentDTO(this);
        }

    }

    private UserAgentDTO(Builder builder) {
        browser = builder.browser;
        browserVersion = builder.browserVersion;
        os = builder.os;
        osVersion = builder.osVersion;
        device = builder.device;

        tgMode = builder.tgMode;
        tgMid = builder.tgMid;
        tgId = builder.tgId;
    }

}
