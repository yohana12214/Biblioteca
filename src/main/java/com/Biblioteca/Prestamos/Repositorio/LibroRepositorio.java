package com.Biblioteca.Prestamos.Repositorio;


import com.Biblioteca.Prestamos.Entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; //importacion de la anotacion Repository

import java.util.ArrayList;

//esta clase debe heredar todos los metodos de jpa repository para Libro con su primary key tipo string
//crea una base de datos virtual en supabase y la entidad lo entiende

@Repository
public interface LibroRepositorio extends JpaRepository<Libro,String> {

 ArrayList<Libro> findByAutor(String autor); //para buscar por autor y decirle a servicios

}
