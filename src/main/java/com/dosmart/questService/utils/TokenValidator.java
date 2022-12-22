package com.dosmart.questService.utils;

public class TokenValidator {
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

    public void validateByToken(String token)
    {

    }
}
