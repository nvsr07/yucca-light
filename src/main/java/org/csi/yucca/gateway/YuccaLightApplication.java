package org.csi.yucca.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class YuccaLightApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuccaLightApplication.class, args);
    }
}
