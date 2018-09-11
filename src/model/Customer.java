package model;

import model.customerFieldEnums.EAgeRange;
import model.customerFieldEnums.EGender;
import model.customerFieldEnums.EOccupation;
import util.Validate;

public final class Customer {

    private int id;
    private EGender gender;
    private EAgeRange ageRange;
    private EOccupation occupation;
    private String zipCode;

    //UserID::Gender::Age::Occupation::Zip-code
    Customer(String id,
             String gender,
             String age,
             String occupation,
             String zipCode) {

        assignID(id);
        assignGender(gender);
        assignAge(age);
        assignOccupation(occupation);
        this.zipCode = zipCode;
    }

    private void assignOccupation(String occupation) {
        occupation = occupation.trim();

        final int OCCUPATION_INDEX = Integer.parseInt(occupation);

        if (!occupationIndexInRange(OCCUPATION_INDEX))
            throw new IllegalArgumentException("undefined occupation");
        else {
            processOccupation(OCCUPATION_INDEX);
        }

    }

    private void processOccupation(int OCCUPATION_INDEX) {
        EOccupation values[] = EOccupation.values();

        this.occupation = values[OCCUPATION_INDEX];
// From the  Document :
//            *0:"other" or not specified
//	          *1:"academic/educator"
//            *2:"artist"
//            *3:"clerical/admin"
//            *4:"college/grad student"
//            *5:"customer service"
//            *6:"doctor/health care"
//            *7:"executive/managerial"
//            *8:"farmer"
//            *9:"homemaker"
//            *10:"K-12 student"
//            *11:"lawyer"
//            *12:"programmer"
//            *13:"retired"
//            *14:"sales/marketing"
//            *15:"scientist"
//            *16:"self-employed"
//            *17:"technician/engineer"
//            *18:"tradesman/craftsman"
//            *19:"unemployed"
//            *20:"writer"
    }

    private boolean occupationIndexInRange(int OCCUPATION) {
        return OCCUPATION >= 0 && OCCUPATION <= 20;
    }

    private void assignAge(String age) {
        age = age.trim();

        final int AGE = Integer.parseInt(age);

        if (!ageGreaterThanZero(AGE)) throw new IllegalArgumentException("age is negative or zero");
        else this.ageRange = returnAgeRange(AGE);
    }


    private boolean ageGreaterThanZero(int AGE) {
        return AGE > 0;
    }

    private EAgeRange returnAgeRange(int AGE) {

        switch (AGE) {

            case 1:
                return EAgeRange.UNDER_EIGHTEEN;

            case 18:
                return EAgeRange.EIGHTEEN_TO_TWENTY_FOUR;

            case 25:
                return EAgeRange.TWENTY_FIVE_TO_THIRTY_FOUR;

            case 35:
                return EAgeRange.THIRTY_FIVE_TO_FORTY_FOUR;

            case 45:
                return EAgeRange.FORTY_FIVE_TO_FORTY_NINE;

            case 50:
                return EAgeRange.FIFTY_TO_FIFTY_FIVE;

            case 56:
                return EAgeRange.FIFTY_SIX_AND_OVER;

            default:
                throw new IllegalArgumentException(" age not in permissible range ");
        }
    }


    public void assignID(String id) {
        id = id.trim();
        int ID = Integer.parseInt(id);

        if (!Validate.customerIdInRange(ID)) {
            throw new IllegalArgumentException("user id not in range ");
        }
        this.id = ID;
    }


    public void assignGender(String gender) {
        gender = gender.trim();

        if (gender.equals("M")) {
            this.gender = EGender.MALE;
        } else if (gender.equals("F")) {
            this.gender = EGender.FEMALE;
        } else {
            throw new IllegalArgumentException("gender not male or female");
        }

    }

    public int getId() {
        return id;
    }

    public EGender getGender() {
        return gender;
    }

    public EAgeRange getAgeRange() {
        return ageRange;
    }

    public EOccupation getOccupation() {
        return occupation;
    }

    public String getZipCode() {
        return zipCode;
    }


}
