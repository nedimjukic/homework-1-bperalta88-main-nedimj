package handler;

import dao.MessageDao;
import dto.MessageDto;
import dto.UserDto;
import request.ParsedRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GetMessagesHandler extends Handler<MessageDto> {
    @Override
    protected List<MessageDto> processRequest(ParsedRequest request) {
        String fromId = request.getQueryParam("fromId");
        String toId = request.getQueryParam("toId");

        return MessageDao.getInstance().getAll().stream()
                .filter(messageDto -> messageDto.getFromId().equals(fromId) && messageDto.getToId().equals(toId))
                .collect(Collectors.toList());
    }
}
