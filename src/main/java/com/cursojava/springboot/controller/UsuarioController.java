package com.cursojava.springboot.controller;

import com.cursojava.springboot.dao.UsuarioDao;
import com.cursojava.springboot.model.Usuario;
import com.cursojava.springboot.utils.JWTUtils;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtils jwtUtils;

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    private Usuario getUsuario(@PathVariable Long id){
        Usuario usuario= new Usuario();
        usuario.setId(id);
        usuario.setNombre("Danny");
        usuario.setApellidos("Bueno");
        usuario.setEmail("mauricio@utp.com");
        usuario.setTelefono("320704");
        usuario.setPassword("1234");

        return usuario;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    private List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token){

        return isTokenValido(token) ? usuarioDao.getUsuarios(): null;
    }

    private boolean isTokenValido(String token){
        String usurioID = jwtUtils.getKey(token);
        return usurioID != null;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    private void registrarUsuario(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashPasswor= argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hashPasswor);

        usuarioDao.registar(usuario);
    }

    @RequestMapping(value = "usuario2")
    private Usuario editar(){
        Usuario usuario= new Usuario();
        usuario.setNombre("Danny");
        usuario.setApellidos("Bueno");
        usuario.setEmail("mauricio@utp.com");
        usuario.setTelefono("320704");
        usuario.setPassword("1234");

        return usuario;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    private void eliminar(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        if (!isTokenValido(token)) return;
        usuarioDao.eliminar(id);
    }


}
/* 1)
    $ @RestController: Los controller son los que nos permiten manipular las URL las debemos anotar con: RestController
    $ @RequestMapping: Para establecer un path (la URL) y que responda con la función que tenga la URL
    $ @PathVariable: Para indicar que el valor se entregara manual

   7) Vamos a inyectar la clase UsuarioDao, para eso hacemos la instancia con la siguiente anotación:

    $ @Autowired: Permite inyectar unas dependencias con otras, esta anotación le dice a spring que busque en su
      contenedor loC(Contenedor de inversion de control) un Bean que coincida con el tipo de la propiedad, método o
      constructor que se quiera inyectar y lo pase como argumento de esta forma no tenemos que crear manualmente las
      instancias de las clases que necesitemos, sino que Spring se encarga de esto y resolver las dependencias entre ellas

    Method = RequestMethod. GET: agregar esta sentencia no cambia en nada la consulta, ya que por defecto el método será tipo GET

    10) Ahora agregamos la funcionalidad para que inserte un registro a la base de datos y lo declaramos en UsuarioDao y
        agregamos la lógica en UsuarioDaoImp

     $ @RequestBody: Indica que Spring debe deserializar el cuerpo de la solicitud HTTP en un objeto Java, que se pasa como
        parámetro al método del controlador, para la funcionalidad REGISTRAR Spring convertirá el cuerpo de la solicitud
        JSON en un Objeto Usuario. Se usa principalmente para operaciones POST o PUT, que envían datos complejos o JSON

     $ Argon2: Lo que sigue es cifrar la contraseña

     16) Verificar el token

 */
