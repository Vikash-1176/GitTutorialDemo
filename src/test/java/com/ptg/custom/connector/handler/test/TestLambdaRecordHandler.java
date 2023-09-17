package com.ptg.custom.connector.handler.test;

import com.amazonaws.appflow.custom.connector.lambda.handler.BaseLambdaConnectorHandler;
import com.amazonaws.appflow.custom.connector.model.ImmutableConnectorContext;
import com.amazonaws.appflow.custom.connector.model.credentials.AuthenticationType;
import com.amazonaws.appflow.custom.connector.model.credentials.ImmutableCredentials;
import com.amazonaws.appflow.custom.connector.model.query.ImmutableQueryDataRequest;
import com.amazonaws.appflow.custom.connector.model.query.QueryDataRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptg.custom.connector.handler.LambdaRequestHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestLambdaRecordHandler {

    @Mock
    Context context;

    @Mock
    LambdaLogger loggerMock;

    private BaseLambdaConnectorHandler requestHandler = null;

    @Before
    public void setUp() throws Exception {
        when(context.getLogger()).thenReturn(loggerMock);
        doAnswer(call -> {
            System.out.println((String)call.getArgument(0));//print to the console
            return null;
        }).when(loggerMock).log(anyString());
        requestHandler = new LambdaRequestHandler();
    }

        @Test
        public void testQueryDataRequestTest() throws IOException {

            final ObjectMapper objectMapper = new ObjectMapper();

            QueryDataRequest request = ImmutableQueryDataRequest.builder()
                    .entityIdentifier("JsonToS3")
                    .selectedFieldNames(Arrays.asList("id", "name", "gender", "latitude","longitude"))
                    .maxResults((long) 1000)
                    .nextToken("2000")
                    .connectorContext(
                            ImmutableConnectorContext.builder()
                                    .apiVersion("v1")
                                    .credentials(
                                            ImmutableCredentials.builder().secretArn("your-secret-arn")
                                                    .authenticationType(AuthenticationType.BasicAuth)
                                                    .build()).build())
                    .build();

           requestHandler.handleRequest(
                    new ByteArrayInputStream(objectMapper.writeValueAsBytes(request)),
                    Mockito.mock(OutputStream.class),
                    context
            );
        }
}
