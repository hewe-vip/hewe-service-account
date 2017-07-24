package vip.hewe.service;

import grpc.service.MemberMsg;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;
import vip.hewe.service.client.data.AccountClient;

@SpringBootApplication
public class HeweServiceAccountApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(HeweServiceAccountApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        AccountClient accountClient = new AccountClient("localhost", 50051);
        MemberMsg.Builder builder = MemberMsg.newBuilder();
        MemberMsg memberMsg = builder.setId("123").setEmail("23@qq.com").setCel("23223444").setPasswd("asdf").setSalt("aeeee").build();

//        try {
//            int count = accountClient.createMember(memberMsg);
//            System.out.println("count:	" + count);
//        } catch (Exception ee) {
//            ee.printStackTrace();
//        }
        //Thread.sleep(5000);
        StopWatch watch = new StopWatch();

        for (int i = 0; i < 10; i++) {
            watch.start("number:    " + i);
            try {
                MemberMsg msg = accountClient.select("2");
                if (msg == null) {
                    System.out.println("not found");
                } else {
                    System.out.println(msg.getCel());
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            watch.stop();
//        Thread.sleep(5000);
        }
        System.out.println(watch.prettyPrint());

    }
}
