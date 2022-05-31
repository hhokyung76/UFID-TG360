package com.tg360.ufidtg360.common.support;

import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.UUID;

/**
 * 쿠키 아이디 생성
 * javascript version
 * //-----------------------------------------------------------------------------------------------------------
 // javascript version
 // case 1
 // Array(40).fill().map(() => Math.round(Math.random() * 122)).filter(number => (number >= 48) && (number <= 57 || number >= 65) && (number <= 90 || number >= 97)).slice(1, 8)
 // Array(40).fill().map(() => Math.round(Math.random() * 97)).filter(number => (number <= 57 || number >= 65) && (number <= 90 || number >= 97)).slice(1, 8)
 // case 2
 // Math.random().toString(36).substr(2,11);
 // case 3

 function makeid() {
      var text = "";
      var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

      for (var i = 0; i < 7; i++)
          text += possible.charAt(Math.floor(Math.random() * possible.length));

          return text;
 }

      console.log(makeid());

 */
public class TgCookieGenerator {
    public String getGeneratorTgCookievalue() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int stringLength = 7;
        Random random = new Random();
        String generatedString = random.ints(leftLimit,rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        //-----------------------------------------------------------------------------------------------------------
        /* javascript version
        var arr = Array(40).fill().map(() => Math.round(Math.random() * 122)).filter(number => (number >= 48) && (number <= 57 || number >= 65) && (number <= 90 || number >= 97)).slice(1, 8)
        var generatedStr = "";
        for (i in arr){
            generatedStr+=String.fromCharCode(arr[i]);
        }

        var timeStamp = Math.round(new Date().valueOf()/1000);
        console.log("timeStamp: "+timeStamp);

         */
        //-----------------------------------------------------------------------------------------------------------

        /* Plain Text to Convert MD5 hash */
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        long unixTimestamp = now.toEpochSecond();

        InetAddress clientIp = InetAddress.getLoopbackAddress();

        String plainText = clientIp.getHostName() + "|" + unixTimestamp + "|" + generatedString;

        System.out.println("plainText: "+plainText);
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(plainText.getBytes());
        byte[] digest = md.digest();

        /* ByteTextFormat to UUID */
        UUID eUuid = UUID.nameUUIDFromBytes(digest);
        return eUuid.toString();
    }
}
