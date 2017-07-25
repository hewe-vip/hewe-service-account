package vip.hewe.service;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vip.hewe.api.domain.Member;
import vip.hewe.api.service.SignService;

@SpringBootApplication
public class HeweServiceAccountApplication implements CommandLineRunner {

    @Autowired
    private SignService signService;

    public static void main(String[] args) {
        SpringApplication.run(HeweServiceAccountApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Member member = signService.selectByPrimaryKey("123");
        if (member != null) {
            System.out.println(member.toString());
        } else {
            System.out.println("null");
        }
    }
}
