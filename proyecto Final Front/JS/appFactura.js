// // Obtener referencias a los elementos del DOM
const idPedidoElement = document.getElementById("idPedido");
const totalPedidoElement = document.getElementById("totalPedido");
const formaPagoElement = document.getElementById("formaPago");
const generarFacturaButton = document.querySelector("button.btn-green");
const nombreCompletoElement = document.getElementById("nombreCompleto");
const direccionElement = document.getElementById("direccion");

let idPedido = null;
let totalPedido = 0; 

function inicializarFactura() {

    const urlParams = new URLSearchParams(window.location.search);
    const idPedido = urlParams.get("idPedido");

    const totalPedido = sessionStorage.getItem("total_pedido");

// Recuperar la información del cliente desde sessionStorage
const clienteNombre = sessionStorage.getItem("nombre_cliente");
const clienteDireccion = sessionStorage.getItem("direccion_cliente");

// Verificar si los datos están disponibles
if (clienteNombre && clienteDireccion) {
    nombreCompletoElement.textContent = clienteNombre; // Mostrar nombre
    direccionElement.textContent = clienteDireccion; // Mostrar dirección
} else {
    console.log("No se encontró la información del cliente");
}


    if (idPedido) {
        idPedidoElement.textContent = `ID Pedido: ${idPedido}`;
    } else {
        alert("No se encontró el ID del pedido.");
        return;
    }

    if (totalPedido) {
        totalPedidoElement.textContent = `$${parseFloat(totalPedido).toFixed(2)}`;
    } else {
        alert("No se encontró el total del pedido.");
        return;
    }
}

function enviarFactura() {
    const formaPago = formaPagoElement.value;
    if (!formaPago) {
        alert("Por favor, selecciona una forma de pago.");
        return;
    }

    // Crear el cuerpo del JSON para enviar al servidor
    const facturaData = {
        total: parseFloat(totalPedido),
        pago: formaPago
    };

    const urlParams = new URLSearchParams(window.location.search);
    const idPedido = urlParams.get("idPedido");

    // Enviar la solicitud al servidor
    console.log("antes del fetch id_pedido=", idPedido);
    fetch(`http://localhost:8080/api/facturas/pedidos/${idPedido}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(facturaData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al procesar la factura.");
            }
            return response.json();
        })
        .then(data => {
            alert("Factura generada con éxito.");
            console.log("Respuesta del servidor:", data);
    
            window.location.href = "carrito.html";
        })
        .catch(error => {
            console.error("Error al enviar la factura:", error);
            alert("Hubo un problema al generar la factura.");
        });
    }

    // Inicializar la página al cargar
    window.onload = () => {
        inicializarFactura();


    // Asignar evento al botón de generar factura
    generarFacturaButton.addEventListener("click", enviarFactura);
};





