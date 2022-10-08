package handler;

import dao.UserDao;
import dto.UserDto;
import request.ParsedRequest;

import java.util.List;

public class GetUsersHandler extends Handler<UserDto>{
    @Override
    protected List<UserDto> processRequest(ParsedRequest request) {
        return UserDao.getInstance().getAll();
    }
}
