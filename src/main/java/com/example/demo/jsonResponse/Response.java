package com.example.demo.jsonResponse;

import com.example.demo.entity.Character;
import com.example.demo.entity.Info;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class Response {
    private Info info;
    private List<Character> results;
}
