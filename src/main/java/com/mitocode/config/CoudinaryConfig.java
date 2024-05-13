package com.mitocode.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoudinaryConfig {
    @Bean
    public Cloudinary Cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
           "cloud_name", "dmyiseq0q",
           "api_key", "567713429663341",
           "api_secret", "mqQdmyx7qiTTeLqT-vA5DJ3Viyw"
        ));
    }
}
