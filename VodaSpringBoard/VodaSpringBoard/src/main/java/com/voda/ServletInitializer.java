package com.voda;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(VodaSpringBoardApplication.class);
	}

<<<<<<< HEAD
}
=======
}
>>>>>>> e484cca1b55f86259538034f4b577b15647d80d0
