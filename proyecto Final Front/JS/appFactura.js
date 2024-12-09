document.addEventListener("DOMContentLoaded", async () => {
    // Obtener el id_cliente de localStorage
    const idCliente = localStorage.getItem("idCliente");
    console.log(idCliente);
    // Hacer la petición a la API para obtener los datos del cliente
    if (idCliente) {
        try {
            const response = await fetch(`http://localhost:8080/api/clientes/${idCliente}`);
            if (!response.ok) {
                throw new Error("No se pudo obtener los datos del cliente");
            }

            const cliente = await response.json();

            // Rellenar los campos con los datos del cliente
            document.getElementById("nombreCompleto").value = cliente.nombre;
            document.getElementById("direccion").value = cliente.domicilio;
        } catch (error) {
            console.error("Error al obtener los datos del cliente:", error);
        }
    } else {
        alert("No se pudo encontrar el cliente");
    }
});