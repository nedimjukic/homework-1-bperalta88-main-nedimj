package request;

import java.util.HashMap;
import java.util.Map;

public class CustomParser {

  // extract java useable values from a raw http request string
  // https://developer.mozilla.org/en-US/docs/Web/HTTP/Messages
  // TODO fill this out

    // because logic for parsing this string can make a lot of noise, I separated it into different functions, which
    // made code more readable and understandable
  public static ParsedRequest parse(String request){
      ParsedRequest parsedRequest = new ParsedRequest();

      String[] data = request.split("\n");

      String method = parseMethod(data);
      String path = parsePath(data);
      Map<String, String> queryMap = parseQueryMap(data);
      String body = parseBody(data);

      parsedRequest.setMethod(method);
      parsedRequest.setQueryMap(queryMap);
      parsedRequest.setPath(path);
      parsedRequest.setBody(body);

      return parsedRequest;
  }

    private static String parseMethod(String[] data) {
        String[] startLine = data[0].split(" ");

        return startLine[0];
    }

    private static String parsePath(String[] data){
        String[] pathLine = getPathLine(data);

        return pathLine[0];
    }

    private static Map<String, String> parseQueryMap(String[] data){
        Map<String, String> queryParams = new HashMap<>();

        String[] pathLine = getPathLine(data);

        if(pathLine.length > 1){
            String[] queryParamsData = pathLine[1].split("&");
            for(String param: queryParamsData){
                String[] queryParam = param.split("=");
                String key = queryParam[0];
                String value = queryParam[1];

                queryParams.put(key, value);
            }
        }

        return queryParams;
    }

    private static String parseBody(String[] data) {
        return data[data.length - 1];
    }

    private static String[] getPathLine(String[] data) {
        String[] startLine = data[0].split(" ");

        return startLine[1].split("\\?");
    }
}
