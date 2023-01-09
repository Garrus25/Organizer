package com.example.organizerclients.Model;

import java.util.Random;

public class TokenAuthorizeGeneratorService {
    private static final Random random = new Random();

    private static final int MIN_DIGIT = 0;
    private static final int MAX_DIGIT = 9;

    private static int getRandomNumberFromTo(int from,int to){
        return random.nextInt(to - from + 1) + from;
    }

    private static String getRandomNumberSequence(int sequenceLength) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sequenceLength; i++) {
            int randomDigit=getRandomNumberFromTo(MIN_DIGIT, MAX_DIGIT);
            stringBuilder.append(randomDigit);
        }

        return stringBuilder.toString();
    }

    public static String createTokenAuthorizeUser(){
        return TokenAuthorizeGeneratorService.getRandomNumberSequence(6);
    }
}
