package org.formacion.procesosservicios.services;

import org.junit.jupiter.api.*;


public class ComandoLsServiceTest {
    ComandoLsService comandoLsService;

    @BeforeEach
    void before() {
        comandoLsService = new ComandoLsService();
        comandoLsSerive.setComando("ls");
    }

    @Test
    void validarTest() {
        String[] arrayComando = {"ls", "-la"};
        boolean valida = comandoLsService.validar(arrayComando);
        Assertions.assertTrue(valida, "Se ha producido un error de validacion");
    }
}
