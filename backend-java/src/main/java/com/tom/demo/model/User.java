package com.tom.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tom.demo.validate.UserAddGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@ApiModel("User")
@Entity
@Data
@Accessors(chain = true)
public class User implements Serializable {

    @ApiModelProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
//    @JsonIgnore
    private Long id;

    @ApiModelProperty(name = "Username", required = true)
    @Column(name = "user_name", nullable = false)
    @NotBlank(message = "Username cannot be empty", groups = UserAddGroup.class)
    private String userName;

    @ApiModelProperty(name = "Password", required = true)
    @Column(name = "password", nullable = false)
    @Length(min = 6, max = 60, message = "The length of password must be between 6 and 10")
    @JsonIgnore
    private String password;    //TODO use Spring Security BCrypt to encrypt

    @ApiModelProperty(name = "Email", required = true)
    @Column(name = "email", nullable = false)
//    @NotBlank(message = "Email cannot be empty", groups = UserAddGroup.class)
    private String email;

    @ApiModelProperty(name = "State(Validity)", example = "1: valid, 0: invalid")
    private int state;

    @ApiModelProperty(name = "Create Time")
    @Column(name = "create_time", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "Update Time")
    @Column(name = "update_time", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public int getState() {
//        return state;
//    }
//
//    public void setState(int state) {
//        this.state = state;
//    }
//
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public Date getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(Date updateTime) {
//        this.updateTime = updateTime;
//    }
}