package com.sz.sogain.cfpt;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * 
 *1.使用@SpringBootApplication快速啓動，暫時關閉權限驗證
 *2.关闭需要在启动类上排除SecurityAutoConfiguration和ManagementWebSecurityAutoConfiguration自动注入
 *3.使用@EnableScheduling注入定時任務類，定时任务方法上加 @Scheduled 注解标注（多个定时任务可以创建多个 @Scheduled 注解标注）
 */
@EnableScheduling
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, 
        ManagementWebSecurityAutoConfiguration.class})
public class CfptApplication {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CfptApplication.class);
    }

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(CfptApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
    }
}
