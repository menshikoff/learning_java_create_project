package edu.javacourse.studentorder.domain;

public class PassportOffice {

    private Long officeId;
    private String officeAreaID;
    private String officeName;

    public PassportOffice() {
    }

    public PassportOffice(Long officeId, String officeAreaID, String officeName) {
        this.officeId = officeId;
        this.officeAreaID = officeAreaID;
        this.officeName = officeName;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getOfficeAreaID() {
        return officeAreaID;
    }

    public void setOfficeAreaID(String officeAreaID) {
        this.officeAreaID = officeAreaID;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }
}
