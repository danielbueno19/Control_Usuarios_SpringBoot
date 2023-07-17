// Call the dataTables jQuery plugin
$(document).ready(function() {
  //on ready
});

async function registrarUsuario(){
    let datos= {}
    datos.nombre= document.getElementById("txtNombre").value;
    datos.apellidos= document.getElementById("txtApellido").value;
    datos.email= document.getElementById("txtEmail").value;
    datos.telefono= document.getElementById("txtTelefono").value;
    datos.password= document.getElementById("txtPassword").value;

    let repetirPassword= document.getElementById("txtRepeatPassword").value;
    if(repetirPassword != datos.password){
        alert("Las contraseñas no coinciden")
        return;
    }


    const request = await fetch("api/usuarios", {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    alert("La cuenta fue creada con éxito!!!");
    window.location.href= "login.html"
}



/*  9) Dar al botón de registrarse la funcionalidad de agregar un usuario a la BD
    $ Ahora la Request sera de tipo POST, es la que se usa para casos de actualizar o insertar registros
      body: JSON.stringify(datos): que hará un Casteo a JSON los datos que se han creado
    $ Declaramos una variable DATOS en la que se guardara el nombre de cada uno de los atributos que declaramos en el ENTITY


*/