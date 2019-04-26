package danix43.api.modules;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DTOMapper {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
		
}
