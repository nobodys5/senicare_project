package com.korit.sinicare.common.utill;

import java.util.Random;

// 인증번호 생성 클래스
public class AuthNumberCreator {
    
    // 0-9의 4자리 인증번호
    public static String number4() {

        String authNumber = "";

        // random util을 사용하여 4자리의 숫자로 인증번호를 받는 코드
        Random random = new Random();
        for (int count = 0; count < 4; count++) 
            authNumber += random.nextInt(10);
        

        return authNumber;
    }

}
