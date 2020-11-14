package by.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "trucks")
public class Truck {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "model")
    private String model;

    @Column(name = "type")
    private String type;

    @Column(name = "yearOfProduction")
    private Date yearOfProduction;

    @Column(name = "registerSign")
    private String registerSign;

    @Column(name = "insuranceNumber")
    private String insuranceNumber;

    @Column(name = "insuranceValidity")
    private Date insuranceValidity;

    @Column(name = "technicalInspectionValidity")
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
