package com.simplon;

import com.simplon.servicemedia.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class Media
{
    public static void main( String[] args )
    {
        SpringApplication.run(Media.class, args);
    }
}
