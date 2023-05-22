package com.ssafy.enjoytrip.service;

import java.util.ArrayList;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    private final EntityManager em;

    @Transactional
    public int sendMail(String userEmail) {


        String jpql = "update User u set u.password =:newPw where u.email=:user_email";
        System.out.println("delete 체크 완료");
        int update = em.createQuery(jpql).setParameter("newPw", "ssafy")
                .setParameter("user_email",userEmail).executeUpdate();
        em.clear();
        System.out.println("updated int: "+update);
        ArrayList<String> toUserList = new ArrayList<>();

        toUserList.add(userEmail);

        int toUserSize = toUserList.size();

        SimpleMailMessage simpleMessage = new SimpleMailMessage();

        simpleMessage.setTo(toUserList.toArray(new String[toUserSize]));

        simpleMessage.setSubject("[sharedTrip] : 비밀번호가 임시 비밀번호로 변경되었습니다.");

        simpleMessage.setText("임시 비밀번호는 'ssafy' 입니다.");

        javaMailSender.send(simpleMessage);
        return update;
    }
}