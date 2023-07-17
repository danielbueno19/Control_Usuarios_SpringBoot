package com.cursojava.springboot.controller;

import com.cursojava.springboot.dao.UsuarioDao;
import com.cursojava.springboot.model.Usuario;
import com.cursojava.springboot.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtils jwtUtils;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    private String login(@RequestBody Usuario usuario){
        Usuario usuarioLogin = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if(usuarioLogin != null){
            String tokenJwt = jwtUtils.create(String.valueOf(usuarioLogin.getId()), usuarioLogin.getEmail());

            return tokenJwt;
        }
        return "FAIL";
    }
}

/* 12) Clase para separar las funciones/responsabilidades Usuario de Autenticación/inicio Sesión
    $ @RestController: Se utiliza para crear servicios web RESTful, indicando que es un controlador que maneja las solicitudes
      HTTP y que sus métodos devuelven Objetos que se serializan automáticamente en el cuerpo de la respuesta HTTP.

    Servicio web RESTful: es una aplicación cliente-servidor que manipula el estado de los recursos.
      Estos servicios se generan a partir de los principios de REST, que es un conjunto de ideas para transferir recursos de una forma elegante.
      En un servicio web de RESTful, el servidor es responsable de producir contestaciones y de proporcionar una
      interfaz que permita que el usuario sostenga por sí solo el estado de la aplicación

   14) Hacemos la implementación de la clase JWTUtils

   $ tokenJwt: para este punto en esta variable ya se tiene mucha de la parte der servidor ya hecha, para generarlo y guardarlo
     en el lado del cliente y después verificarlo.


 */