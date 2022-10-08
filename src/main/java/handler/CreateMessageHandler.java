package handler;

import dao.MessageDao;
import dto.MessageDto;
import request.ParsedRequest;

import java.util.List;
import java.util.UUID;

public class CreateMessageHandler extends Handler<MessageDto> {
    @Override
    protected List<MessageDto> processRequest(ParsedRequest request) {
        MessageDto messageDto = GsonTool.gson.fromJson(request.getBody(), MessageDto.class);
        messageDto.setUniqueId(UUID.randomUUID().toString());

        MessageDao.getInstance().put(messageDto);

        return List.of(messageDto);
    }
}
