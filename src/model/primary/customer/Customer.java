package model.primary.customer;

import conditions.Condition;
import conditions.CustomerID;

public final class Customer {

    private final int id;
    private final EGender gender;
    private EAgeRange ageRange;
    private EOccupation occupation;
    private String zipCode;

    //UserID::Gender::Age::Occupation::Zip-code
    public Customer(String id,
                    String gender,
                    String age,
                    String occupation,
                    String zipCode) {

        this.id = returnID(id);
        this.gender = returnGender(gender);
        this.ageRange = returnAgeRange(age);
        this.occupation = returnOccupation(occupation);
        this.zipCode = zipCode;
    }

    private EOccupation returnOccupation(String occupation) {
        occupation = occupation.trim();

        final int OCCUPATION_INDEX = Integer.parseInt(occupation);

        if (!occupationIndexInRange(OCCUPATION_INDEX))
            throw new IllegalArgumentException("undefined occupation");

        final EOccupation eOccupation = returnOccupationType(OCCUPATION_INDEX);

        return eOccupation;

    }

    private EOccupation returnOccupationType(int OCCUPATION_INDEX) {
        EOccupation values[] = EOccupation.values();

        return values[OCCUPATION_INDEX];
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

    private EAgeRange returnAgeRange(String age) {
        age = age.trim();

        final int AGE = Integer.parseInt(age);

        if (!ageGreaterThanZero(AGE)) throw new IllegalArgumentException("age is negative or zero");

        return returnRange(AGE);
    }


    private boolean ageGreaterThanZero(int AGE) {
        return AGE > 0;
    }

    private EAgeRange returnRange(int AGE) {

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


    private int returnID(String id) {
        id = id.trim();
        int ID = Integer.parseInt(id);

        Condition condition = new CustomerID(ID);
        if (!condition.isValid()) throw new IllegalArgumentException("user id not in range ");

        return ID;
    }


    private EGender returnGender(String gender) {
        gender = gender.trim();

        if (gender.equals("M")) {
            return EGender.MALE;
        } else if (gender.equals("F")) {
            return EGender.FEMALE;
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
