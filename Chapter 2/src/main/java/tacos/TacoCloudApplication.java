package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TacoCloudApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(TacoCloudApplication.class, args);
    }
    
    // These lines perform the same job as WebConfig.java
    // Remember imports here if using this method
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) 
//    {
//        registry.addViewController("/").setViewName("home");
//    }
}