package com.tg360.ufidtg360;

import com.tg360.ufidtg360.common.support.TgCookieGenerator;

import javax.servlet.http.Cookie;
import javax.xml.bind.DatatypeConverter;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.IntStream;

public class Test2 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
//        String testItem = "123456";
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        md.update(testItem.getBytes());
//        byte[] digest = md.digest();
//
//        System.out.println("digest byte: "+digest);
//
//        String s = new String(digest, StandardCharsets.UTF_8);
//        System.out.println("digest str : "+s);
//
//        // BASE64
//        String base64Code = Base64.getEncoder().encodeToString(digest);
//        System.out.println("base64Code digest str : "+base64Code);
//        // d41d8cd98f00b204e9800998ecf8427e
        String hash = "35454B055CC325EA1AF2126E27707052";
        String password = "ILoveJava";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

        System.out.println("hash: "+hash);
        System.out.println("myHash: "+myHash);


        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        long unixTimestamp = now.toEpochSecond();
        System.out.println(unixTimestamp);

        getGeneratorTgCookievalue();


    }

    public static  void getGeneratorTgCookievalue() {
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

const crypto = require('crypto');

function javaHash(input) {
  let md5Bytes = crypto.createHash('md5').update(input).digest();
  md5Bytes[6]  &= 0x0f;
        md5Bytes[6]  |= 0x30;  // set to version 3
        md5Bytes[8]  &= 0x3f;  // clear variant
        md5Bytes[8]  |= 0x80;  // set to IETF variant
  const hex = md5Bytes.toString('hex')
  const uuid = hex.replace(/(\w{8})(\w{4})(\w{4})(\w{4})(\w{12})/, "$1-$2-$3-$4-$5");
        return uuid;
    }
         */
        //-----------------------------------------------------------------------------------------------------------

        /* Plain Text to Convert MD5 hash */
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        long unixTimestamp = now.toEpochSecond();

        InetAddress clientIp = InetAddress.getLoopbackAddress();

        String plainText = clientIp.getHostName() + "|" + unixTimestamp + "|" + generatedString;

        System.out.println("##plainText: "+plainText);
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(plainText.getBytes());
        byte[] digest = md.digest();

        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        System.out.println("##myHash: "+myHash);

        /* ByteTextFormat to UUID */
        UUID eUuid = UUID.nameUUIDFromBytes(digest);
        System.out.println("Generated UUID Last: "+eUuid.toString());
    }


    public static void main2(String[] args) {
        String tgId = "";
        String tgSt = "";

        if ((tgId != null && !tgId.equals("")) || (tgSt != null && !tgSt.equals(""))) {
            System.out.println("good not null and not empty");
        }else {
            System.out.println("good null or  empty");
        }


        InetAddress clientIp = InetAddress.getLoopbackAddress();
        System.out.println("clientIp: "+clientIp.getHostAddress());
        System.out.println("clientIp: "+clientIp.getHostName());

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int stringLength = 7;
        Random random = new Random();
        String generatedString = random.ints(leftLimit,rightLimit + 1)
                .filter(e -> (e <= 57 || e >= 65) && (e <= 90 || e >= 97))
                .limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        //-----------------------------------------------------------------------------------------------------------
        // javascript version
        // case 1
        // Array(40).fill().map(() => Math.round(Math.random() * 122)).filter(number => (number >= 48) && (number <= 57 || number >= 65) && (number <= 90 || number >= 97)).slice(1, 8)
        // Array(40).fill().map(() => Math.round(Math.random() * 97)).filter(number => (number <= 57 || number >= 65) && (number <= 90 || number >= 97)).slice(1, 8)
        // case 2
        // Math.random().toString(36).substr(2,11);
        // case 3
        /*
        function makeid() {
          var text = "";
          var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

          for (var i = 0; i < 7; i++)
            text += possible.charAt(Math.floor(Math.random() * possible.length));

          return text;
        }

        console.log(makeid());
         */
        //-----------------------------------------------------------------------------------------------------------
        System.out.println("generatedString: "+generatedString);


        IntStream temp = random.ints(48, 122).limit(7);
        //System.out.println(temp.toArray().toString());

        List<Integer> result = temp.collect(ArrayList::new, List::add, List::addAll);
        System.out.println("result: "+result.toArray());

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        long unixTimestamp = now.toEpochSecond();
        System.out.println(unixTimestamp);

    }
}
