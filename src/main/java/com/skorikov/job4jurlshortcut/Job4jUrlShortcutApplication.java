package com.skorikov.job4jurlshortcut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Class applicationstart.
 */
@SpringBootApplication
@EnableJpaAuditing
public class Job4jUrlShortcutApplication {
    /**
     * Method main.
     * @param args arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Job4jUrlShortcutApplication.class, args);
    }

}
