package com.docencia.ficheros;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClaseEjemplo {

    private static final Logger logger = LoggerFactory.getLogger(ClaseEjemplo.class);

    public ClaseEjemplo(){
        String variable = "Soy variable";
        String otra = "otra variable";
        logger.info("Soy un mensaje: {}, y soy otra variable {}", variable, otra);
        Exception exception = new Exception("Sucedio un error");
        logger.error("El mensaje que quiero {}, mas la excepcion ",variable, exception);
    }

}
