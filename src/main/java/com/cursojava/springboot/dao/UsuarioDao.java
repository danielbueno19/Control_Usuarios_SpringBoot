package com.cursojava.springboot.dao;

import com.cursojava.springboot.model.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
/* 4) DAO: Data Access Object
    Todas las clases que harán conexión a la BD y cada clase representara una tabla.
    Una interfaz se puede ver como un archivo en el que indicamos que funciones debería tener una clase, por ejemplo:
        getUsuario, getUsuarios, save, delete etc. Haciendo que si una clase implementa esta interfaz debe si o si tener
        estas funciones.


 */