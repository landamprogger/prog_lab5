package studyGroup.person;

import domain.exception.VerifyException;

public class Person {
    private static final String EMPTY_VALUE = "Поле не должно быть пустым.";
    private static final String SHOULD_BE_POSITIVE = "Значение должно быть положительным.";

    private String name; //Поле не может быть null, Строка не может быть пустой
    private int height; //Значение поля должно быть больше 0
    private String passportID; //Строка не может быть пустой, Значение этого поля должно быть уникальным, Поле не может быть null
    private Country nationality; //Поле может быть null

    public Person(String name,
                  int height,
                  String passportID,
                  Country nationality) throws VerifyException{
        checkName(name);
        this.name = name;
        checkHeight(height);
        this.height = height;
        checkPassportID(passportID);
        this.passportID = passportID;
        checkNationality(nationality);
        this.nationality = nationality;
    }

    private void checkName(String name) throws VerifyException{
        if (name == null){
            throw new VerifyException(EMPTY_VALUE);
        }

        if (name.equals("")){
            throw new VerifyException(EMPTY_VALUE);
        }
    }
    private void checkHeight(int height) throws VerifyException{
        if (height <= 0){
            throw new VerifyException(SHOULD_BE_POSITIVE);
        }
    }
    private void checkPassportID(String passportID) throws VerifyException{
        if (passportID == null){
            throw new VerifyException(EMPTY_VALUE);
        }

        if (passportID.equals("")){
            throw new VerifyException(EMPTY_VALUE);
        }
    }
    private void checkNationality(Country nationality) throws VerifyException{
        if (nationality == null){
            throw new VerifyException(EMPTY_VALUE);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }
}
