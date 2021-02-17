package com.parcel.domain;

import lombok.Data;

@Data
public class LoginResponse {
    private String uid;
    private String message;
    private String role;
}
