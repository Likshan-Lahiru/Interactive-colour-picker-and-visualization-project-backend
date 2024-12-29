package pos.spring.interactivecolourpickerandvisualizationprojectbackend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InteractiveColourPickerAndVisualizationProjectBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(InteractiveColourPickerAndVisualizationProjectBackendApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
