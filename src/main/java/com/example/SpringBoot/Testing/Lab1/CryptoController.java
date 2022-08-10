package com.example.SpringBoot.Testing.Lab1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CryptoController {
    Logger logger = LoggerFactory.getLogger(CryptoController.class);

    @Autowired
    private BitCoinService bitCoinService;

    public CryptoController(BitCoinService bitCoinService){
        logger.trace("Entering CryptoController");
        this.bitCoinService = bitCoinService;
        logger.trace("Leaving CryptoController");
    }

    @GetMapping("/crypto/{cryptoname}")
    public String price(@PathVariable String cryptoname) {
            logger.trace("Entering CryptoController.price");
            String bitCoinPrice = bitCoinService.getCryptoPrice(cryptoname);
        logger.trace("Leaving CryptoController.price");

        return String.format(cryptoname+": "+bitCoinPrice);

    }
}
