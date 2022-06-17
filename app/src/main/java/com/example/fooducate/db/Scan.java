package com.example.fooducate.db;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "scan")
public class Scan {
    @PrimaryKey(autoGenerate = true)
    private long scanId;

    @ForeignKey
            (entity = User.class,
                    parentColumns = "userId",
                    childColumns = "id_fkcourse",
                    onDelete = CASCADE
            )
    private String id_fkcourse;

    public long getScanId() {
        return scanId;
    }

    public void setScanId(long scanId) {
        this.scanId = scanId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    private String barcode;
    private int status;

    private String status_verbose;

    private Date scanDate;

    public Scan(int status, String barcode, String status_verbose, Date scanDate) {
        this.status = status;
        this.barcode = barcode;
        this.status_verbose = status_verbose;
        this.scanDate = scanDate;
    }

    public long getId_student() {
        return scanId;
    }

    public void setId_student(long scanId) {
        this.scanId = scanId;
    }

    public String getId_fkcourse() {
        return id_fkcourse;
    }

    public void setId_fkcourse(String id_fkcourse) {
        this.id_fkcourse = id_fkcourse;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatus_verbose() {
        return status_verbose;
    }

    public void setStatus_verbose(String status_verbose) {
        this.status_verbose = status_verbose;
    }

    public Date getScanDate() {
        return scanDate;
    }

    public void setScanDate(Date scanDate) {
        this.scanDate = scanDate;
    }
}
