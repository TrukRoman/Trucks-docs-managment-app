package by.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "driverLicenceNum")
    private String driverLicenceNum;

    @Column(name = "category")
    private String category;

    @Column(name = "dlvalidity")
    private Date dlvalidity;

    @Column(name = "medicalCertificateNumber")
    private String medicalCertificateNumber;

    @Column(name = "mcvalidaty")
    private Date mcvalidaty;

    public Driver() {}

    public Driver(String name, String driverLicenceNum, String category, Date dlvalidity, String medicalCertificateNumber, Date mcvalidaty) {
        super();
        this.name = name;
        this.driverLicenceNum = driverLicenceNum;
        this.category = category;
        this.dlvalidity = dlvalidity;
        this.medicalCertificateNumber = medicalCertificateNumber;
        this.mcvalidaty = mcvalidaty;
    }

    public Driver(int id, String name, String driverLicenceNum, String category, Date dlvalidity, String medicalCertificateNumber, Date mcvalidaty) {
        super();
        this.id = id;
        this.name = name;
        this.driverLicenceNum = driverLicenceNum;
        this.category = category;
        this.dlvalidity = dlvalidity;
        this.medicalCertificateNumber = medicalCertificateNumber;
        this.mcvalidaty = mcvalidaty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriverLicenceNum() {
        return driverLicenceNum;
    }

    public void setDriverLicenceNum(String driverLicenceNum) {
        this.driverLicenceNum = driverLicenceNum;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDlvalidity() {
        return dlvalidity;
    }

    public void setDlvalidity(Date dlvalidity) {
        this.dlvalidity = dlvalidity;
    }

    public String getMedicalCertificateNumber() {
        return medicalCertificateNumber;
    }

    public void setMedicalCertificateNumber(String medicalCertificateNumber) {
        this.medicalCertificateNumber = medicalCertificateNumber;
    }

    public Date getMcvalidaty() {
        return mcvalidaty;
    }

    public void setMcvalidaty(Date mcvalidaty) {
        this.mcvalidaty = mcvalidaty;
    }
}