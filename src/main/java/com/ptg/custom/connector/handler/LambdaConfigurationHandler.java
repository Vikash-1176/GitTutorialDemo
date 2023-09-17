package com.ptg.custom.connector.handler;
import com.amazonaws.appflow.custom.connector.handlers.ConfigurationHandler;
import com.amazonaws.appflow.custom.connector.model.connectorconfiguration.ConnectorModes;
import com.amazonaws.appflow.custom.connector.model.connectorconfiguration.DescribeConnectorConfigurationRequest;
import com.amazonaws.appflow.custom.connector.model.connectorconfiguration.DescribeConnectorConfigurationResponse;
import com.amazonaws.appflow.custom.connector.model.connectorconfiguration.ImmutableDescribeConnectorConfigurationResponse;
import com.amazonaws.appflow.custom.connector.model.connectorconfiguration.auth.AuthenticationConfig;
import com.amazonaws.appflow.custom.connector.model.connectorconfiguration.auth.ImmutableAuthenticationConfig;
import com.amazonaws.appflow.custom.connector.model.credentials.ImmutableValidateCredentialsResponse;
import com.amazonaws.appflow.custom.connector.model.credentials.ValidateCredentialsRequest;
import com.amazonaws.appflow.custom.connector.model.credentials.ValidateCredentialsResponse;
import com.amazonaws.appflow.custom.connector.model.settings.ImmutableValidateConnectorRuntimeSettingsResponse;
import com.amazonaws.appflow.custom.connector.model.settings.ValidateConnectorRuntimeSettingsRequest;
import com.amazonaws.appflow.custom.connector.model.settings.ValidateConnectorRuntimeSettingsResponse;

import java.util.Arrays;
import java.util.Collections;

public class LambdaConfigurationHandler implements ConfigurationHandler {
    private static final String CONNECTOR_OWNER = "Sagar";
    private static final String CONNECTOR_NAME = "JsonConnector";
    private static final String CONNECTOR_VERSION = "1.0";

    @Override
    public ValidateConnectorRuntimeSettingsResponse validateConnectorRuntimeSettings(ValidateConnectorRuntimeSettingsRequest request) {
        System.out.println("Method validateConnectorRuntimeSettings invoked from class LambdaConfigurationHandler");
        return ImmutableValidateConnectorRuntimeSettingsResponse.builder().isSuccess(true).build();
    }

    @Override
    public ValidateCredentialsResponse validateCredentials(ValidateCredentialsRequest request) {
        System.out.println("Method validateCredentials invoked from class LambdaConfigurationHandler");
        return ImmutableValidateCredentialsResponse.builder().isSuccess(true).build();
    }

    @Override
    public DescribeConnectorConfigurationResponse describeConnectorConfiguration(DescribeConnectorConfigurationRequest request) {
        System.out.println("Method describeConnectorConfiguration invoked from class LambdaConfigurationHandler");
        AuthenticationConfig config = ImmutableAuthenticationConfig.builder()
                 .isBasicAuthSupported(true)
                .build();
        return ImmutableDescribeConnectorConfigurationResponse.builder()
                .isSuccess(true)
                .connectorOwner(CONNECTOR_OWNER)
                .connectorName(CONNECTOR_NAME)
                .connectorVersion(CONNECTOR_VERSION)
                .connectorModes(Arrays.asList(ConnectorModes.SOURCE,ConnectorModes.DESTINATION))
                .connectorRuntimeSetting(null)
                .authenticationConfig(config)
                .supportedApiVersions(Collections.singletonList("v1"))
                .build();
    }
}
