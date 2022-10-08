package handler;

import dao.UserDao;
import dto.BaseDto;
import dto.UserDto;
import request.ParsedRequest;
import response.CustomHttpResponse;

import java.util.List;
import java.util.UUID;

public class CreateUserHandler extends Handler<UserDto>{
    @Override
    protected List<UserDto> processRequest(ParsedRequest request) {
        UserDto userDto = GsonTool.gson.fromJson(request.getBody(), UserDto.class);
        userDto.setUniqueId(UUID.randomUUID().toString());
        UserDao.getInstance().put(userDto);

        return List.of(userDto);
    }
}
