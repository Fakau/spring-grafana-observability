package com.engine.fakau.servicepayment;

import com.engine.fakau.servicepayment.entities.CarteCreditEntity;
import com.engine.fakau.servicepayment.repositories.CarteCreditRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class ServicePaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePaymentApplication.class, args);
    }

    @Autowired
    CarteCreditRepository carteCreditRepository;
    @Bean
    public List<CarteCreditEntity> init() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        TypeReference<List<CarteCreditEntity>> typeReference = new TypeReference<List<CarteCreditEntity>>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/data.json");
        try {
            List<CarteCreditEntity> carteCredits = mapper.readValue(inputStream, typeReference);
            return carteCreditRepository.saveAll(carteCredits);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save CarteCredits: " + e.getMessage());
        }
    }

}
