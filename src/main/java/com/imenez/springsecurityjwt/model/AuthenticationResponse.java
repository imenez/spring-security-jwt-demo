package com.imenez.springsecurityjwt.model;


public class AuthenticationResponse {

    private final String jtwToken;

    public String getJtwToken() {
        return jtwToken;
    }

    public AuthenticationResponse(String jtwToken) {
        this.jtwToken = jtwToken;
    }
}
