package com.javatpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
 // Using a root package also allows the @ComponentScan annotation to be used without needing to specify a basePackage attribute
public class SpringBootConfig 
{
public static void main(String[] args) throws Exception 
{
SpringApplication.run(SpringBootConfig.class, args);            // it will start application
}
}
