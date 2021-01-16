package com.lottery.system.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@PropertySource("classpath:lotteryInfoResource.properties")
public @Data class LobbyUtil {
	
	@Value("${maxSize}")
	private String maxSize;
	
	@Value("${fixPrice}")
	private String price;
	
	@Value("${minPrice}")
	private String minPrice;
	
	@Value("${maxPrice}")
	private String maxPrice;
	
	//Minimum Person Requires to Start the lottery
	@Value("${minPerson}")
	private String minPeron;
	//prize money
	@Value("${prize.percentage}")
	private String prizeMoneyPercentage;
	
	@Value("${lobby.charge}")
	private String lobbyChargePercentage;
	
	@Bean
	public PropertySourcesPlaceholderConfigurer getLobbyUtilBean() {
        return new PropertySourcesPlaceholderConfigurer();
	}
	
	private static class LobbyUtilHelper {
		private static final LobbyUtil INS=new LobbyUtil();
	}
	public static LobbyUtil getIns() {
		return LobbyUtilHelper.INS;
	}
}
