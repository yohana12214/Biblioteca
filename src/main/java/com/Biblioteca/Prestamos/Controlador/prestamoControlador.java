package com.Biblioteca.Prestamos.Controlador;

import com.Biblioteca.Prestamos.Entidades.Prestamo;
import com.Biblioteca.Prestamos.Servicios.PrestamoServicio;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class prestamoControlador {

    PrestamoServicio servicio;

    public prestamoControlador(PrestamoServicio servicio) {
        this.servicio = servicio;
    }

    @PostMapping("AgregarPrestamo/{isbn}/{doc}")
    public Prestamo agregarPrestamo (@PathVariable("isbn") String isbn,
                                     @PathVariable("doc") String doc,
                                     @RequestBody Prestamo prestamo){
        return servicio.agregarPrestamo(isbn,doc,prestamo);
    }


    @GetMapping("/porEstudiante/{doc}")//consultar por documen estudia y me muestra los prestamos de estudia
    public ArrayList<Prestamo> porEstudiante(@PathVariable("doc") String doc){
        return servicio.porEstudiante(doc);
    }



}
