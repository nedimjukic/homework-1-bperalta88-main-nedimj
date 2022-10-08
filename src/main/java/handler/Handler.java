package handler;

import dto.BaseDto;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;
import response.RestApiAppResponse;

import java.util.HashMap;
import java.util.List;

/* Made some kind of mid-class between BaseHandler and concrete handler,
as I wanted concrete handlers to only process the request and not to care about how to handle errors and responses.
We have abstract method which is being called from concrete method, that's called Abstract Method design pattern.
As we can see, concrete handlers only process the request while handling the responses and errors is localized
in this base class.*/

public abstract class Handler<T extends BaseDto> implements BaseHandler{
    protected abstract List<T> processRequest(ParsedRequest request);

    @Override
    public CustomHttpResponse handleRequest(ParsedRequest request) {
        try{
            List<T> data = processRequest(request);

            return createSuccessResponse(data);
        }
        catch (Exception e){
            return createFailedResponse();
        }
    }

    private CustomHttpResponse createFailedResponse() {
        RestApiAppResponse response = new RestApiAppResponse(true, null, "");

        return new ResponseBuilder()
                .setStatus("500 INTERNAL SERVER ERROR")
                .setVersion("HTTP/1.1")
                .setBody(response)
                .setHeaders(new HashMap<>())
                .build();
    }

    private CustomHttpResponse createSuccessResponse(List<T> data) {
        RestApiAppResponse response = new RestApiAppResponse(true, data, "");

        return new ResponseBuilder()
                .setStatus("200 OK")
                .setVersion("HTTP/1.1")
                .setBody(response)
                .setHeaders(new HashMap<>())
                .build();
    }
}
