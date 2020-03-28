package controller.commands;

import controller.response.Response;
import controller.response.Status;
import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.StudyGroup;
import domain.studyGroupRepository.IStudyGroupRepository;
import domain.studyGroupRepository.concreteSet.AllSet;
import domain.studyGroupRepository.concreteSet.ConcreteSet;

import java.util.Map;
import java.util.Set;

public class ShowCommand extends StudyGroupRepositoryCommand {

    public ShowCommand(String type,
                       Map<String, String> args,
                       IStudyGroupRepository studyGroupRepository) {
        super(type, args, studyGroupRepository);
    }

    @Override
    public Response execute() {
        try {
            ConcreteSet allSet = new AllSet();
            Set<StudyGroup> studyGroupSet = studyGroupRepository.getConcreteSetOfStudyGroups(allSet);

            responseDTO.answer = getMessage(studyGroupSet);

            if (studyGroupSet.isEmpty()) {
                responseDTO.answer = "Коллекция пуста.";
            }
            responseDTO.status = Status.SUCCESSFULLY.getCode();

        } catch (StudyGroupRepositoryException e) {
            responseDTO.answer = e.getMessage();
            responseDTO.status = Status.BAD_REQUEST.getCode();
        }

        return Response.getResponse(responseDTO);
    }

    private String getMessage(Set<StudyGroup> studyGroupSet){

        if(!studyGroupSet.isEmpty()) {
            StringBuilder allSrudyGroups = new StringBuilder();

            for (StudyGroup studyGroup : studyGroupSet){
                allSrudyGroups.append(studyGroup.toString()).append(System.lineSeparator()).append(System.lineSeparator());
            }

            return allSrudyGroups.toString();
        }

        return "Коллекция пуста.";
    }
}
