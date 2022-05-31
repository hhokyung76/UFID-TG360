package com.tg360.ufidtg360.app.manager;


import com.tg360.ufidtg360.common.support.UserAgentParserPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Component;
import ua_parser.Parser;

import javax.annotation.PostConstruct;

@Component
public class UnifiedManager {
    private GenericObjectPool<Parser> userAgentParserPool;

    public GenericObjectPool<Parser> getUserAgentParserPool() {
        return userAgentParserPool;
    }

    @PostConstruct
    private void init() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(8);

        userAgentParserPool = new GenericObjectPool<Parser>(new UserAgentParserPool(), config);
    }

}
