package com.ptg.custom.connector.handler;

import com.amazonaws.appflow.custom.connector.handlers.MetadataHandler; 
import com.amazonaws.appflow.custom.connector.model.metadata.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.print.DocFlavor.URL;

public class LambdaMetadataHandler implements MetadataHandler {
	
    @Override
    public ListEntitiesResponse listEntities(ListEntitiesRequest request) {
    	final List<Entity> records = new ArrayList<Entity>();
        ListEntitiesResponse response = null;
        try{
            System.out.println("Method listEntities invoked from class LambdaMetadataHandler");
            records.add(
                    ImmutableEntity.builder()
                            .entityIdentifier("JsonToS3")
                            .description("JsonToS3")
                            .label("JsonToS3")
                            .hasNestedEntities(false)
                            .build());
          
            response =  ImmutableListEntitiesResponse.builder()
                    .addAllEntities(records)
                    .isSuccess(true)
                    .build();
        }catch(Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public DescribeEntityResponse describeEntity(DescribeEntityRequest request) {
       
        //ClassLoader  classLoader=Thread.currentThread().getContextClassLoader();

        System.out.println("Method describeEntity invoked from class LambdaMetadataHandler");
        final List<FieldDefinition> fieldDefinitionsList = new ArrayList<>();
            /*try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("dummydata.json"))
        	{   	
        		    ObjectMapper mapper = new ObjectMapper();
        		    JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
        		    String jsonString = mapper.writeValueAsString(jsonNode);   		    
        		    Map<String,Object> map = mapper.readValue(jsonString, Map.class);    		    
        		    Set<String> k = map.keySet();
        		    Collection<Object> v = map.values();
        		    String[] keys = k.toArray(new String[k.size()]);
        		    String[] values = v.toArray(new String[v.size()]);
        		    for(int i=0;i<keys.length;i++) {
        		    	FieldDefinition feilddefinition=ImmutableFieldDefinition.builder()
        		    			.fieldName(keys[i]).dataType(FieldDataType.String)
        		    			.label(keys[i])
        		    			.description(keys[i])
        		    			.defaultValue(values[i])
        		    			.isPrimaryKey(false)
        		    			.readProperties(ImmutableReadOperationProperty.builder().isQueryable(true)
        		    					.isRetrievable(true)
        		    					.isTimestampFieldForIncrementalQueries(true)
        		    					.build())
        		    			.build();
        		    	fieldDefinitionsList.add(feilddefinition);
        		    			
        		    }
        	}
        		catch(Exception e){
        		throw new RuntimeException(e);
        		}*/
        
    	
            return ImmutableDescribeEntityResponse.builder()
                    .isSuccess(true)
                    .entityDefinition(
                            ImmutableEntityDefinition.builder()
                                    .entity(
                                            ImmutableEntity.builder()
                                                    .entityIdentifier(request.entityIdentifier())
                                                    .description(request.entityIdentifier())
                                                    .label(request.entityIdentifier())
                                                    .hasNestedEntities(false)
                                                    .build())
                                    .fields(fieldDefinitionsList).build())
                    .build();
    }
    public static void main(String[] args) 
    {
    	try 
    	{
            // files from src/main/resources/json
            List<File> collect = Files.walk(Paths.get(LambdaMetadataHandler.class.getClassLoader().getResource("json").toURI()))
                    .filter(Files::isRegularFile)
                    .map(x -> x.toFile())
                    .collect(Collectors.toList());
            for (File file : collect) 
            {
                //System.out.println("file : " + file);
                List<String> lines;
                try {
                    lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
                    lines.forEach(System.out::println);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
