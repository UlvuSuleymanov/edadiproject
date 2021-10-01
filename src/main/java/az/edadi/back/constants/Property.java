package az.edadi.back.constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Property {

    @Autowired
    private Environment environment;

   public String getProperty(String property){
        return environment.getProperty(property);
    }
}
