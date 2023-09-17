package com.ptg.custom.connector.handler;

import com.amazonaws.appflow.custom.connector.lambda.handler.BaseLambdaConnectorHandler;

public class LambdaRequestHandler extends BaseLambdaConnectorHandler {

    public LambdaRequestHandler() {
        super(
                new LambdaMetadataHandler(),
                new LambdaRecordHandler(),
                new LambdaConfigurationHandler()
        );
    }
}
