package com.cursojava.springboot.dao;

import com.cursojava.springboot.model.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void registar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public List<Usuario> getUsuarios() {
        String query= "FROM Usuario";
        List<Usuario> lsResultado= entityManager.createQuery(query).getResultList();

        return lsResultado;
    }

    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario){
        String query= "FROM Usuario WHERE email= :email";
        List<Usuario> lsUsuario= entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if(lsUsuario.isEmpty()){
            return null;
        }

        String passwordHashed = lsUsuario.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(passwordHashed, usuario.getPassword())){
            return lsUsuario.get(0);
        }
        return null;
    }
}
/* 5) Esta clase implementará las funciones de UsuarioDao
    $ @Transactional: Le da la funcionalidad a esta clase de armar las consultas de SQL a la BD
    $ @Repository: hace referencia a la conexión en sí a la BD
    La principal característica de usar Entity Manager administrado por el contenedor, es que el mismo container se encarga de
      abrir y cerrar las transacciones, asi el programador solo se preocupara de la lógica de negocio.

    $ @PersistenceContext: Utilizada em ambientes JEE para crear el EntityManager y se utiliza para indicar que el
      EntityManager debe inyectarse automáticamente, asi que con esta anotación nos aseguramos que inyecte de forma
      automática una referencia válida del EntityManager creado a partir de la unidad de persistencia que hayamos definido
      y podremos usar este EntityManager en cualquier método que definamos y las transacciones serán administradas de forma automática

    "FROM Usuario" para la construcción de esta consulta usamos Usuario como el nombre de la tabla, ya que estamos trabajando con
      los objetos de la clase nuestros de java y el EntityManager deberá ejecutar la query

 */