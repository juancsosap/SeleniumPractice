package utils;

import java.util.Random;

public class RandomGen {

    public static int getDigits(int length) {
        Random gen = new Random();
        int top = (int) Math.pow(10, length);
        int botton = (int) Math.pow(10, length-1);
        return gen.nextInt(top - botton) + botton;
    }

}
