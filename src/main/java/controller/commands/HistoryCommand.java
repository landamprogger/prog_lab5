package controller.commands;

import controller.response.Response;
import domain.commandsRepository.Record;
import domain.commandsRepository.ICommandsRepository;

import java.util.List;
import java.util.Map;

public class HistoryCommand extends Command {
    private ICommandsRepository commandsRepository;

    public HistoryCommand(String name,
                          Map<String, String> args,
                          ICommandsRepository commandsRepository) {
        super(name, args);
        this.commandsRepository = commandsRepository;
    }

    @Override
    public Response execute() {
        List<Record> historyList = commandsRepository.getRecords(15);
        String answer = getAnswer(historyList);

        return getSuccessfullyResponseDTO(answer);
    }

    private String getAnswer(List<Record> historyList) {
        StringBuilder answer = new StringBuilder();
        for (Record record : historyList){
            answer.append(record.toString()).append(System.lineSeparator());
        }

        return answer.toString();
    }

}
