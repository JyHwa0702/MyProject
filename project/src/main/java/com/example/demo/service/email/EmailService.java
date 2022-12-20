package com.example.demo.service.email;

import com.example.demo.dto.MailDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class EmailService {

    private UserRepository userRepository;
    private JavaMailSender javaMailSender;
    private BCryptPasswordEncoder encoder;
    private static final String FROM_ADDRESS = "본인의 이메일 주소를 입력하세요!.";

    public MailDto createMailAndChangePassword(String email){
        String str = getTempPassword();
        MailDto dto = new MailDto();
        Optional<User> byEmail = userRepository.findByEmail();
        String username = byEmail.get().getUsername();
        dto.setAddress(email);
        dto.setTitle(username + "님의 프로젝트 임시비밀번호 안내 이메일입니다.");
        dto.setMessage("안녕하세요. 프로젝트의 임시비밀번호 안내 관련 이메일입니다." + "["+username+"]"+
                "님의 임시 비밀번호는 "+ str + "입니다.");
        updatePassword(str,email);
        return dto;
    }

    public void updatePassword(String str,String email){
        String pw = encoder.encode(str);
        userRepository.updateUserPassword(email,pw);
    }

    public String getTempPassword(){
        char[] charSet = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i=0; i<10; i++){
            idx = (int) (charSet.length* Math.random());
            str +=charSet[idx];
        }
        return str;
    }

    public boolean userEmailCheck(String email){
        Optional<User> user = userRepository.findByEmail(email);

        if(user.get()!=null && user.get().getEmail().equals(email)){
            return true;
        }
        else {
            return false;
        }


    }

}
