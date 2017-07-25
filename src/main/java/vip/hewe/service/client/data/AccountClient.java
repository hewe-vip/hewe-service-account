package vip.hewe.service.client.data;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vip.hewe.api.service.SignService;

/**
 *
 */
public class AccountClient {
    private static final Logger logger = LoggerFactory.getLogger(AccountClient.class);

    @Reference
    private SignService signService;




}
