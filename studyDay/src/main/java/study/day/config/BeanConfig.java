package study.day.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan
//@ComponentScan("study.day.config")
public class BeanConfig {
    @Bean
    public Person newPerson(){
        return new Person();
    }
}
