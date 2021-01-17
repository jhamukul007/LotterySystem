package com.lottery.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.lottery.system.repository.LobbyInfoRepository;

@SpringBootApplication(scanBasePackages = {"com.lottery.services","com.lottery.system.apis","com.lottery.system.repository"})
@ComponentScan(basePackageClasses  = {LobbyInfoRepository.class} )
public class LotterySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LotterySystemApplication.class, args);
	}
}
