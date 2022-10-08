package handler;

import request.ParsedRequest;

import java.util.Map;

public class HandlerFactory {

  // map handlers by their HTTP method and path, so it's way easier to get the required handler
  // this solution has its own cons, like having to add mapping for every handler you want to add
  private static final Map<String, Handler> handlersMap = Map.of(
          "POST /createUser", new CreateUserHandler(),
          "GET /getUsers", new GetUsersHandler(),
          "POST /createMessage", new CreateMessageHandler(),
          "GET /getMessages", new GetMessagesHandler());

  // routes based on the path. Add your custom handlers here

  // just take the handler with given method and map from handlersMap
  public static BaseHandler getHandler(ParsedRequest request) {
    String method = request.getMethod();
    String path = request.getPath();
    String handlerKey = method + " " + path;

    return handlersMap.get(handlerKey);
  }

}
