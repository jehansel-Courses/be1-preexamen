package test;

import dao.impl.DaoEnMemoria;
import model.Veterinario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.VeterinarioService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class VeterinarioServiceTestMemoria {
    VeterinarioService veterinarioService = new VeterinarioService(new DaoEnMemoria());

    @Test
    @DisplayName("Testear que un veterinario fue cargado correctamente")
    void caso1(){
        //Dado
        Veterinario veterinario = new Veterinario("33245", "Laura", "Atehortúa", "Pediatra");
        //cuando
        Veterinario veterinarioDesdeMemoria = veterinarioService.guardarVeterinario(veterinario);
        // entonces
        assertNotNull(veterinarioDesdeMemoria.getId());
    }

}