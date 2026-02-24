package sec06.client;

import java.util.Date;

public class ClientDTO {
    private String clientNo;
    private String clientName;
    private String clientPhone;
    private String clientAddress;
    private Date clientBirth;
    private String clientHobby;
    private String clientGender;
    private String clientPassword;

    public ClientDTO(String clientNo, String clientName, String clientPhone,
                      String clientAddress, Date clientBirth, String clientHobby,
                     String clientGender, String clientPassword){

        this.clientNo = clientNo;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.clientAddress = clientAddress;
        this.clientBirth = clientBirth;
        this.clientHobby = clientHobby;
        this.clientGender = clientGender;
        this.clientPassword = clientPassword;
    }

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public Date getClientBirth() {
        return clientBirth;
    }

    public void setClientBirth(Date clientBirth) {
        this.clientBirth = clientBirth;
    }

    public String getClientHobby() {
        return clientHobby;
    }

    public void setClientHobby(String clientHobby) {
        this.clientHobby = clientHobby;
    }

    public String getClientGender() {
        return clientGender;
    }

    public void setClientGender(String clientGender) {
        this.clientGender = clientGender;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }
}
