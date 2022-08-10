package com.example.SpringBoot.Testing.Lab1;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BitCoinService {

    public String getCryptoPrice(String name) {
        String apiURL = "https://api.coincap.io/v2/assets/"+name;
        RestTemplate restTemplate = new RestTemplate();
        Data result = restTemplate.getForObject(apiURL, Data.class);

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