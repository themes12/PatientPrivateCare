package com.thiti.patientprivatecare;

public class Date {
    String medID;
    String setHour;
    String setMin;

    public Date(){

    }

    public Date(String medID, String setHour, String setMin) {
        this.medID = medID;
        this.setHour = setHour;
        this.setMin = setMin;
    }

    public String getMedID() {
        return medID;
    }

    public String getSetHour() {
        return setHour;
    }

    public String getSetMin() {
        return setMin;
    }
}
