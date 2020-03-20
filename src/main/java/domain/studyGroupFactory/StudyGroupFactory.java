package domain.studyGroupFactory;

import domain.exception.VerifyException;
import domain.studyGroupFactory.idProducer.IdProducer;
import domain.studyGroup.FormOfEducation;
import domain.studyGroup.Semester;
import domain.studyGroup.StudyGroup;
import domain.studyGroup.StudyGroupDTO;
import domain.studyGroup.coordinates.Coordinates;
import domain.studyGroup.person.Person;

import java.time.LocalDateTime;

public class StudyGroupFactory implements IStudyGroupFactory {

    private final IdProducer idProducer;

    public StudyGroupFactory(IdProducer idProducer){
        this.idProducer = idProducer;
    }

    @Override
    public StudyGroup createNewStudyGroup(StudyGroupDTO studyGroupDTO) throws VerifyException {
        if (studyGroupDTO == null){
            return null;
        }

        Long id = idProducer.getId();
        String name = studyGroupDTO.name;
        Coordinates coordinates = Coordinates.createCoordinates(studyGroupDTO.coordinates);
        LocalDateTime creationDate = LocalDateTime.now();
        int studentsCount = studyGroupDTO.studentsCount;
        Long shouldBeExpelled = studyGroupDTO.shouldBeExpelled;
        FormOfEducation formOfEducation = FormOfEducation.getFormOfEducation(studyGroupDTO.formOfEducation);
        Semester semesterEnum = Semester.getSemesterEnum(studyGroupDTO.semesterEnum);
        Person groupAdmin = Person.createPerson(studyGroupDTO.groupAdmin);



        return new StudyGroup(id,
                            name,
                            coordinates,
                            creationDate,
                            studentsCount,
                            shouldBeExpelled,
                            formOfEducation,
                            semesterEnum,
                            groupAdmin);
    }

}