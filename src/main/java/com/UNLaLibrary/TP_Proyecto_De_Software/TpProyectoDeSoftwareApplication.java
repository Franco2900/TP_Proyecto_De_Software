package com.UNLaLibrary.TP_Proyecto_De_Software;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.UNLaLibrary.TP_Proyecto_De_Software.property")
public class TpProyectoDeSoftwareApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpProyectoDeSoftwareApplication.class, args);
	}

}
