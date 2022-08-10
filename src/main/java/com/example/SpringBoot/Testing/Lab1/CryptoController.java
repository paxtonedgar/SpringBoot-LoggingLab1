package com.example.SpringBoot.Testing.Lab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CryptoController {
    @Autowired
    private BitCoinService bitCoinService;

    public CryptoController(BitCoinService bitCoinService){
        this.bitCoinService = bitCoinService;
    }

    @GetMapping("/crypto/{cryptoname}")
    public String price(@PathVariable String cryptoname) {
            String bitCoinPrice = bitCoinService.getCryptoPrice(cryptoname);
            return String.format(cryptoname+": "+bitCoinPrice);

    }
}
