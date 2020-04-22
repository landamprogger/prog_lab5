package controller.commands.studyGroupRep;

import controller.commands.studyGroupRep.StudyGroupRepositoryCommand;
import controller.response.Response;
import domain.exception.StudyGroupRepositoryException;
import domain.studyGroup.StudyGroupDTO;
import domain.studyGroup.coordinates.CoordinatesDTO;
import domain.studyGroup.person.PersonDTO;
import domain.studyGroupRepository.IStudyGroupRepository;

import java.time.LocalDateTime;
import java.util.Map;

public class AddCommand extends StudyGroupRepositoryCommand {
    public AddCommand(String type,
                      Map<String, String> args,
                      IStudyGroupRepository studyGroupRepository) {
        super(type, args, studyGroupRepository);
    }

    @Override
    public Response execute() {

        CoordinatesDTO coordinatesDTO = new CoordinatesDTO();
        coordinatesDTO.x = Integer.parseInt(args.get("xCoordinate"));
        coordinatesDTO.y = Integer.parseInt(args.get("yCoordinate"));

        PersonDTO personDTO = new PersonDTO();
        personDTO.passportID = args.get("groupAdminPassportID");
        personDTO.name = args.get("groupAdminName");
        personDTO.nationality = args.get("groupAdminNationality");
        personDTO.height = Integer.parseInt(args.get("groupAdminHeight"));

        StudyGroupDTO studyGroupDTO = new StudyGroupDTO();
        studyGroupDTO.name =  args.get("StudyGroupName");
        studyGroupDTO.coordinates = coordinatesDTO;
        studyGroupDTO.studentsCount = Integer.parseInt(args.get("studentsCount"));
        studyGroupDTO.shouldBeExpelled = Long.parseLong(args.get("shouldBeExpelled"));
        studyGroupDTO.formOfEducation = args.get("formOfEducation")  ;
        studyGroupDTO.semesterEnum = args.get("semesterEnum");
        studyGroupDTO.groupAdmin = personDTO;
        studyGroupDTO.creationDate = LocalDateTime.now();

        try {
            studyGroupRepository.add(studyGroupDTO);

            return getSuccessfullyResponseDTO("Группа добавлена" + System.lineSeparator());
        } catch (StudyGroupRepositoryException e) {

            return getBadRequestResponseDTO(e.getMessage());
        }

    }
}
