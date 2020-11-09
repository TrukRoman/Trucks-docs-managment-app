package by.model;

import java.sql.Date;

public class Truck {
    private int id;
    private String model;
    private String type;
    private Date yearOfProduction;
    private String registerSign;
    private String insuranceNumber;
    private Date insuranceValidity;
    private Date technicalInspectionValidity;

    public Truck() {}

    public Truck(String model, String type, Date yearOfProduction, String registerSign, String insuranceNumber, Date insuranceValidity, Date technicalInspectionValidity) {
        this.model = model;
        this.type = type;
        this.yearOfProduction = yearOfProduction;
        this.registerSign = registerSign;
        this.insuranceNumber = insuranceNumber;
        this.insuranceValidity = insuranceValidity;
        this.technicalInspectionValidity = technicalInspectionValidity;
    }

    public Truck(int id, String model, String type, Date yearOfProduction, String registerSign, String insuranceNumber, Date insuranceValidity, Date technicalInspectionValidity) {
        this.id = id;
        this.model = model;
        this.type = type;
        this.yearOfProduction = yearOfProduction;
        this.registerSign = registerSign;
        this.insuranceNumber = insuranceNumber;
        this.insuranceValidity = insuranceValidity;
        this.technicalInspectionValidity = technicalInspectionValidity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Date yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getRegisterSign() {
        return registerSign;
    }

    public void setRegisterSign(String registerSign) {
        this.registerSign = registerSign;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public Date getInsuranceValidity() {
        return insuranceValidity;
    }

    public void setInsuranceValidity(Date insuranceValidity) {
        this.insuranceValidity = insuranceValidity;
    }

    public Date getTechnicalInspectionValidity() {
        return technicalInspectionValidity;
    }

    public void setTechnicalInspectionValidity(Date technicalInspectionValidity) {
        this.technicalInspectionValidity = technicalInspectionValidity;
    }
}
