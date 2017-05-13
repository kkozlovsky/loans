package ru.kerporation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RandomUser {

    @JsonProperty("lname")
    private String firstName;
    @JsonProperty("fname")
    private String lastName;
    @JsonProperty("patronymic")
    private String pathName;
    @JsonProperty("gender")
    private String date;
    @JsonProperty("date")
    private String gender;
    @JsonProperty("postcode")
    private String postcode;
    @JsonProperty("city")
    private String city;
    @JsonProperty("street")
    private String street;
    @JsonProperty("house")
    private int house;
    @JsonProperty("apartment")
    private int apartment;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("login")
    private String login;
    @JsonProperty("password")
    private String password;
    @JsonProperty("color")
    private String color;
    @JsonProperty("userpic")
    private String userpic;






}
