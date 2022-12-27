package com.dosmart.questService.utils;

import com.dosmart.questService.dtos.AdminDetails;
import com.dosmart.questService.dtos.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static com.dosmart.questService.utils.Constants.AUTHORIZATION;
import static com.dosmart.questService.utils.Urls.*;

public class TokenValidator {

    @Autowired
    RestTemplate restTemplate;
    private TokenValidator(){}
    private static TokenValidator instance;
    public static TokenValidator getInstance()
    {
        if(instance==null)
        {
            instance = new TokenValidator();
        }
        return instance;
    }

    public AdminDetails getUserById(String token,Integer id) throws Exception {
        HttpEntity<String> entity = setTokenInHeaders(token);
        BaseResponse<AdminDetails> baseResponse = restTemplate.exchange(AUTHENTICATION_URL + FETCH_USER + id,HttpMethod.GET,entity,new ParameterizedTypeReference<BaseResponse<AdminDetails>>() {}).getBody();
        if(Objects.nonNull(baseResponse.getValue())) {
            return baseResponse.getValue();
        }
        throw new Exception("User Not Found");
    }

    public Integer validateByToken(String token)
    {
        HttpEntity<String> entity = setTokenInHeaders(token);
        return restTemplate.exchange(AUTHENTICATION_URL + FETCH_ID, HttpMethod.GET,entity,Integer.class).getBody();
    }
    private HttpEntity<String> setTokenInHeaders(String token){
        HttpHeaders httpHeaders = getHeaders();
        httpHeaders.set(AUTHORIZATION,token);
        return new HttpEntity<>(httpHeaders);
    }
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
