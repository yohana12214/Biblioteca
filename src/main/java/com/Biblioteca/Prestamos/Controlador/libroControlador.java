package com.Biblioteca.Prestamos.Controlador;

import com.Biblioteca.Prestamos.Entidades.Libro;
import com.Biblioteca.Prestamos.Servicios.libroServicio;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController //para que me active el http
public class libroControlador {

    private libroServicio servicio;

    //constru
    public libroControlador(libroServicio servicio) {
        this.servicio = servicio;
    }
    //ahora si puedo llamar todos los metodos que tiene el servicio
    //análogo a crud

    /*---------para LISTAR o buscar libros  "GET"------------------*/
    //ruta
    @GetMapping("/ListarLibros")
    public ArrayList<Libro> listar(){
        return servicio.listarLibros();
    }

    @GetMapping("/BuscarLibro/{id}")
    public Optional<Libro> buscarLibro(@PathVariable("id") String isbn){
        return servicio.buscarLibro(isbn);
    }

    @GetMapping("/BuscarAutor/{autor}")
    public ArrayList<Libro> buscarAutor(@PathVariable("autor") String autor){
        return servicio.buscarAutor(autor);
    }

    /*----------------------para INSERTAR o guardar  "POST"---------------------------------------*/

    @PostMapping("/AgregarLibro")
    public String agregarLibro(@RequestBody Libro libro){
    return servicio.agregarLibro(libro);
    }

    /*------------------------------------para ACTUALIZAR O modificar  "PUT"-----------------------------------------*/

        @PutMapping("/ActualizarLibro")
        public String actualizarLibro(@RequestBody Libro libro){//toca hacerlo manualmente
            return servicio.actualizarLibro(libro);
        }
    /*-------------------para ACTUALIZAR O modificar  "PATCH"------------------------------------------------------------*/

    @PatchMapping ("/ActualizarEdit/{isbn}/{edit}")
    public String actualizarEdit(@PathVariable("isbn")String isbn,@PathVariable("edit")String editorial){//se estan recibiendo por la ruta 2 variables
        return servicio.actualizarEditorial(isbn,editorial);
    }


    /*-------------2 opcion------para ACTUALIZAR O modificar  "PATCH"------------------------------------------------------------*/
    @PatchMapping("ActualizarCampo/{isbn}")
    public Libro actualizarCampo(@PathVariable("isbn") String isbn, @RequestBody Map<Object,Object> libroMap){
        return servicio.actualizarCampo(isbn,libroMap);
    }



    /*-------------------------------para ELIMINAR o borrar  "DELETE"----------------------------------------------*/

    @DeleteMapping("/EliminarLibro/{id}")
    public String eliminarLibro(@PathVariable("id") String isbn){
        return servicio.eliminarLibro(isbn);
    }



}
