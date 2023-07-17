// Call the dataTables jQuery plugin
$(document).ready(function() {
  //on ready
});

async function iniciarSesion(){
    let datos= {}
    datos.email= document.getElementById("txtEmail").value;
    datos.password= document.getElementById("txtPassword").value;

    const request = await fetch("api/login", {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    const respuesta = await request.text();

    if(respuesta != "FAIL"){
        localStorage.token = respuesta;
        localStorage.email = datos.email;
        window.location.href= "usuarios.html"
    } else{
        alert("Credenciales incorrectas, Intenta nuevamente!!")
    }

}
/*  11) Gestionaremos la lógica para el inicio de Sesión
    15) Guardamos en el lado del cliente el token y ya esta!!!

    $ ?allowPublicKeyRetrieval=true&useSSL=false: esta linea se agrega en la properties, ya que MySql no permite
      la recuperación de clave publica por motivos de seguridad o control de acceso.



*/