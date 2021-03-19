package com.example.fooducate;

import java.util.Date;

public class FirebaseModel {
    private ResponseObject object;
    private Date scanDate;

    public FirebaseModel(){

    }
    public FirebaseModel(ResponseObject object, Date scanDate) {
        this.object = object;
        this.scanDate = scanDate;
    }

    public ResponseObject getObject() {
        return object;
    }

    public void setObject(ResponseObject object) {
        this.object = object;
    }

    public Date getScanDate() {
        return scanDate;
    }

    public void setScanDate(Date scanDate) {
        this.scanDate = scanDate;
    }
}
