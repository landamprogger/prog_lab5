package controller.commands;

import controller.response.Response;
import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.StudyGroup;
import domain.studyGroup.person.Country;
import domain.studyGroupRepository.IStudyGroupRepository;
import domain.studyGroupRepository.concreteSet.AllSet;
import domain.studyGroupRepository.concreteSet.ConcreteSet;

import java.util.Map;
import java.util.Set;

public class CountByGroupAdminCommand extends StudyGroupRepositoryCommand {
    public CountByGroupAdminCommand(String type,
                                    Map<String, String> args,
                                    IStudyGroupRepository studyGroupRepository) {
        super(type, args, studyGroupRepository);
    }

    //Todo: дописать
    @Override
    public Response execute() {
        String passportID = args.get("groupAdminPassportID");
        String name = args.get("groupAdminName");
        Country nationality = Country.getCountry(args.get("groupAdminNationality"));
        int height = Integer.parseInt(args.get("groupAdminHeight"));

        try {
            ConcreteSet allSet = new AllSet();
            Set<StudyGroup> allStudyGroupSet = studyGroupRepository.getConcreteSetOfStudyGroups(allSet);

            int count = 0;
            for (StudyGroup studyGroup : allStudyGroupSet) {
                if(studyGroup.getGroupAdmin().getName().equals(name) &&
                        studyGroup.getGroupAdmin().getHeight() == height &&
                        studyGroup.getGroupAdmin().getNationality().equals(nationality) &&
                        studyGroup.getGroupAdmin().getPassportID().equals(passportID)){
                    count += 1;
                }
            }

            if(count == 0){
                return getPreconditionFailedResponseDTO("Групп с равным значением groupAdmin в коллекции нет.");
            }


            return getSuccessfullyResponseDTO(Integer.toString(count));
        } catch (StudyGroupRepositoryException e) {
            return getBadRequestResponseDTO(e.getMessage());
        }
    }
}
