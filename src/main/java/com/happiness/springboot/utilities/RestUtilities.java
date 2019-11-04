package com.happiness.springboot.utilities;

import com.happiness.springboot.transferobject.GlobalQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Component
@PropertySource({ "classpath:application.properties" })
public class RestUtilities {

    private Logger logger = Logger.getLogger(getClass().getName());

    private RestTemplate restTemplate;
    private String alphavantageRestUrl;

    @Autowired
    public RestUtilities(RestTemplate restTemplate, @Value("${alphavantage.rest.url}") String alphavantageRestUrl) {
        this.restTemplate = restTemplate;
        this.alphavantageRestUrl = alphavantageRestUrl;
        logger.info("Loaded property:  alphavantage.rest.url=" + this.alphavantageRestUrl + ", restTemplate: " + this.restTemplate);
    }

    public Double getSpotPrice(String symbol) {

        GlobalQuote globalQuote = restTemplate.getForObject(alphavantageRestUrl + "?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=780T7F2EMPFB1ML4", GlobalQuote.class);
        logger.info("Recived quote: " + globalQuote);
        return globalQuote.getQuote().getPrice();
    }
}
