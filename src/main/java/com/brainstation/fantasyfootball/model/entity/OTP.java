package com.brainstation.fantasyfootball.model.entity;

import javax.persistence.*;

/**
 * @author BS987
 * created date: 10/8/2022
 */
@Entity
@Table(name = "OTP")
public class OTP {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer otp;
    private String userMail;

    public OTP() {}
    public OTP(Integer otp, String userMail) {
        this.otp = otp;
        this.userMail = userMail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

}
