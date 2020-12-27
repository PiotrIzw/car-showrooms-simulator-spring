package com.company.carshowroomssimulatorspring.model;

public enum RatingEnum {
    One(1), Two(2), Three(3), Four(4), Five(5);
    private int value;

    RatingEnum(int value) {
        this.value = value;
    }

    public static RatingEnum getValue(int value) {
        RatingEnum values[] = RatingEnum.values();
        return values[value];
    }
}
