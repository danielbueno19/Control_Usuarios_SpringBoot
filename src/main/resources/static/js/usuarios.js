// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
  $('#usuarios').DataTable();
  actualizarUsuarioSesion();
});

function actualizarUsuarioSesion(){
    document.getElementById("txt-email-usuario").outerHTML= localStorage.email;
}

async function cargarUsuarios(){
    const request = await fetch("api/usuarios", {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        }
    });

    const lsUsuarios = await request.json();

    let listadoUsuariostoHTML= "";
    for(let usuario of lsUsuarios){
        let botonEliminar = `<a href="#" onclick = "eliminarUsuario(${usuario.id})" class="btn btn-danger btn-circle btn-sm"> <i class="fas fa-trash"></i> </a>`;

        let usuarioHTML = `
            <tr>
                <td>${usuario.id}</td>
                <td>${usuario.nombre} ${usuario.apellidos}</td>
                <td>${usuario.email}</td>
                <td>${usuario.telefono}</td>
                <td>
                    ${botonEliminar}
                </td>
            </tr>
        `
        listadoUsuariostoHTML += usuarioHTML;
    }

    document.querySelector('#usuarios tbody').outerHTML = listadoUsuariostoHTML;
}

async function eliminarUsuario(id){
    if(!confirm("Desea eliminar este usuario?")){
        return;
    }

    const request = await fetch("api/usuarios/"+ id, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        }
    });

    location.reload();
}

/*  3) Esta archivo JS es el que inicializa la tabla de usuarios

    8) Ahora le daremos la funcionalidad de eliminar un registro desde la interfaz
     $ Empezamos por separar el JS del botos eliminar creando una variable que contenga este código.
     $ seguido a ello creamos la función que eliminará el registro
     $ Ahora lo que haremos es que haga el llamado al servidor con la información que le llega a la función eliminar
     $ Esta bueno el hecho de preguntar si desea eliminar el usuario de la base de datos

    15) Implementamos el token
     $ Donde tomamos la informacion del local storage y la agregamos al header
*/