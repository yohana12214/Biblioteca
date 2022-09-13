package com.Biblioteca.Prestamos.Repositorio;

import com.Biblioteca.Prestamos.Entidades.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepositorio extends JpaRepository<Estudiante,String> {




}
