package com.retail.springConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "com.retail.springConfig", "com.retail.model", "com.retail.repository",
        "com.retail.service", "com.retail.web" })
public class SpringRootConfig {
}
