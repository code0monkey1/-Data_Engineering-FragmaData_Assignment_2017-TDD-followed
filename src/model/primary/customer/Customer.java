package model.primary.customer;

import conditions.Condition;
import conditions.CustomerID;

public final class Customer {

    private final int id;
    private final Gender gender;
    private AgeRange ageRange;
    private Occupation occupation;
    private String zipCode;
    private final int MIN = 0;
    private final int MAX = 20;

    //UserID::Gender::Age::Occupation::Zip-code
    public Customer(String id,
                    String gender,
                    String age,
                    String occupation,
                    String zipCode) {

        this.id = ID(id);
        this.gender = gender(gender);
        this.ageRange = ageRange(age);
        this.occupation = occupation(occupation);
        this.zipCode = zipCode;
    }

    private Occupation occupation(String occupation) {
        occupation = occupation.trim();

        final int OCCUPATION_INDEX = Integer.parseInt(occupation);

        if (!occupationIndexInRange(OCCUPATION_INDEX))
            throw new IllegalArgumentException("undefined occupation");

        final Occupation eOccupation = occupationType(OCCUPATION_INDEX);

        return eOccupation;

    }

    private Occupation occupationType(int OCCUPATION_INDEX) {
        Occupation values[] = Occupation.values();

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
        return OCCUPATION >= MIN && OCCUPATION <= MAX;
    }

    private AgeRange ageRange(String age) {
        age = age.trim();

        final int AGE = Integer.parseInt(age);

        if (!ageGreaterThanZero(AGE)) throw new IllegalArgumentException("age is negative or zero");

        return range(AGE);
    }


    private boolean ageGreaterThanZero(int AGE) {
        return AGE > 0;
    }

    private AgeRange range(int AGE) {

        switch (AGE) {

            case 1:
                return AgeRange.UNDER_EIGHTEEN;

            case 18:
                return AgeRange.EIGHTEEN_TO_TWENTY_FOUR;

            case 25:
                return AgeRange.TWENTY_FIVE_TO_THIRTY_FOUR;

            case 35:
                return AgeRange.THIRTY_FIVE_TO_FORTY_FOUR;

            case 45:
                return AgeRange.FORTY_FIVE_TO_FORTY_NINE;

            case 50:
                return AgeRange.FIFTY_TO_FIFTY_FIVE;

            case 56:
                return AgeRange.FIFTY_SIX_AND_OVER;

            default:
                throw new IllegalArgumentException(" age not in permissible range ");
        }
    }


    private int ID(String id) {
        id = id.trim();
        int ID = Integer.parseInt(id);

        Condition condition = new CustomerID(ID);
        if (!condition.isValid()) throw new IllegalArgumentException("user id not in range ");

        return ID;
    }


    private Gender gender(String gender) {
        gender = gender.trim();

        if (gender.equals("M")) {
            return Gender.MALE;
        } else if (gender.equals("F")) {
            return Gender.FEMALE;
        } else {
            throw new IllegalArgumentException("gender not male or female");
        }

    }

    public int getId() {
        return id;
    }

    public Gender getGender() {
        return gender;
    }

    public AgeRange getAgeRange() {
        return ageRange;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public String getZipCode() {
        return zipCode;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", gender=" + gender +
                ", ageRange=" + ageRange +
                ", occupation=" + occupation +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
