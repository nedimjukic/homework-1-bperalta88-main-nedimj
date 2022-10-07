package handler;

import request.ParsedRequest;
import response.CustomHttpResponse;

public interface BaseHandler {

  CustomHttpResponse handleRequest(ParsedRequest request);
}
