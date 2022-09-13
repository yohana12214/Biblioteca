package com.Biblioteca.Prestamos.Servicios;

import com.Biblioteca.Prestamos.Entidades.Prestamo;
import com.Biblioteca.Prestamos.Repositorio.EstudianteRepositorio;
import com.Biblioteca.Prestamos.Repositorio.LibroRepositorio;
import com.Biblioteca.Prestamos.Repositorio.PrestamoRepositorio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PrestamoServicio {

  private PrestamoRepositorio pRepo;
  private LibroRepositorio libRepo;
  private EstudianteRepositorio estRepo;

    public PrestamoServicio(PrestamoRepositorio pRepo, LibroRepositorio libRepo, EstudianteRepositorio estRepo) {
        this.pRepo = pRepo;
        this.libRepo = libRepo;
        this.estRepo = estRepo;
    }

    public Prestamo agregarPrestamo(String isbn, String doc, Prestamo prestamo){
        libRepo.findById(isbn).map(libro ->{
            prestamo.setLibro(libro);
            return libro;
        });

        return estRepo.findById(doc).map (est ->{
          prestamo.setEstudiante(est);
          return pRepo.save(prestamo);
        }).get();

    }

    public ArrayList<Prestamo> porEstudiante(String doc){
        try {
            return estRepo.findById(doc).map(est -> {
                return pRepo.findByEstudiante(est);
            }).get();
        }catch(Exception ex)   {
            System.out.println("Error:"+ex);
        }
        return null;//trabajar que tipo de excepcion es
    }//busca primero lo que encontró de estudiante y de esa consulta busca el prestamo y lo retorna




}
