package com.example.SpringBoot.Testing.Lab1;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Slf4j
public class BitCoinService {
    Logger logger = LoggerFactory.getLogger(BitCoinService.class);

    public String getCryptoPrice(String name) {
        String apiURL = "https://api.coincap.io/v2/assets/"+name;
        RestTemplate restTemplate = new RestTemplate();
        Data result = restTemplate.getForObject(apiURL, Data.class);
        logger.info("API Reached");
        return result.getData().getPriceUsd();
    }
}

@Getter
@Setter
class BitCoin {
    private String id;
    private String symbol;
    private String priceUsd;
}

@Getter
@Setter
class Data{
    private BitCoin data;
}