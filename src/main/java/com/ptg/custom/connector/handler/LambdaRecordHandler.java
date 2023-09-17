package com.ptg.custom.connector.handler;

import com.amazonaws.appflow.custom.connector.handlers.RecordHandler;
import com.amazonaws.appflow.custom.connector.model.query.ImmutableQueryDataResponse;
import com.amazonaws.appflow.custom.connector.model.query.QueryDataRequest;
import com.amazonaws.appflow.custom.connector.model.query.QueryDataResponse;
import com.amazonaws.appflow.custom.connector.model.retreive.RetrieveDataRequest;
import com.amazonaws.appflow.custom.connector.model.retreive.RetrieveDataResponse;
import com.amazonaws.appflow.custom.connector.model.write.WriteDataRequest;
import com.amazonaws.appflow.custom.connector.model.write.WriteDataResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class LambdaRecordHandler implements RecordHandler {

    public RetrieveDataResponse retrieveData(RetrieveDataRequest request) {
        System.out.println("Method retrieveData invoked from class LambdaRecordHandler");
        return null;
    }

    /**
     * This custom connector is source connector hence we are not implementing the write operation.
     * @param request
     * @return
     */
    @Override
    public WriteDataResponse writeData(WriteDataRequest request) {
        System.out.println("Method writeData invoked from class LambdaRecordHandler");
        return null;
    }

    @Override
    public QueryDataResponse queryData(QueryDataRequest request) {
        try{
            System.out.println("Method queryData invoked from class LambdaRecordHandler");
            System.out.println("request.selectedFieldNames()"+request.selectedFieldNames());
            System.out.println("request.entityIdentifier()"+request.entityIdentifier());
            System.out.println("queryDataRequest.connectorContext().entityDefinition()"+request.connectorContext().entityDefinition());
            //id,name,gender,latitude,longitude
            Map<String, String> data = new HashMap<>();
            data.put("id","313");data.put("name","Sagar");data.put("gender","male");data.put("latitude","1.3.0");data.put("longitude","1.5.0");

            final ObjectMapper objectMapper = new ObjectMapper();
            List<String> queryDataList = new ArrayList<>();
            queryDataList.add(objectMapper.writeValueAsString(data));
            //Iterable<String> queryDateListIterator = queryDateList::iterator;
            return ImmutableQueryDataResponse.builder()
                    .records(queryDataList)
                    .isSuccess(true)
                    .build();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
