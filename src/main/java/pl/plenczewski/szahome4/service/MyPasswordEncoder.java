package pl.plenczewski.szahome4.service;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {


    @Override
    public String encode(CharSequence rawPassword) {
        String encodedPassword="";
        for (int i = 0; i < rawPassword.length(); i++) {
            char c = rawPassword.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            encodedPassword+=c;
        }
        return encodedPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if(rawPassword!=null){
            String compareEncodedPassword = encode(rawPassword);
            if(compareEncodedPassword.equals(encodedPassword)){
                return true;
            }
        }
        return false;
    }
}
