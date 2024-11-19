package jp.ac.teami.seisakukadaiI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"jp.ac.teami.seisakukadaiI", "jp.ac.teami.seisakukadaiI.controller.tmp"})
@EnableScheduling
public class SeisakukadaiIApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeisakukadaiIApplication.class, args);
    }
}

