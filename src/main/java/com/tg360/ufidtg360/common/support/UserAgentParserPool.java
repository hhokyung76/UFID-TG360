package com.tg360.ufidtg360.common.support;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import ua_parser.Parser;

public class UserAgentParserPool extends BasePooledObjectFactory<Parser> {

    @Override
    public Parser create() throws Exception {
        // TODO Auto-generated method stub
        return new Parser();
    }

    @Override
    public PooledObject<Parser> wrap(Parser parser) {
        // TODO Auto-generated method stub
        return new DefaultPooledObject<Parser>(parser);
    }

}

