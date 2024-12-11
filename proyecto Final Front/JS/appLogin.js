// Obtener el formulario y sus campos
const formularioLogin = document.getElementById("formularioLogin");
const emailInput = document.getElementById("email");
const passwordInput = document.getElementById("password");

formularioLogin.addEventListener("submit", function (event) {
    event.preventDefault();

    const email = emailInput.value;
    const password = passwordInput.value;

    const loginData = {
        email: email,
        password: password
    };

    fetch("http://localhost:8080/api/clientes/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(loginData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Credenciales inválidas"); 
        }
        return response.json();
    })
    .then(data => {
        console.log(data);
        if (data.cliente) {
            // Guardar la información del cliente en sessionStorage
            sessionStorage.setItem("id_cliente", data.cliente.id); 
            sessionStorage.setItem("nombre_cliente", data.cliente.nombre);
            sessionStorage.setItem("direccion_cliente", data.cliente.direccion);
            sessionStorage.setItem("email_cliente", data.cliente.email); 
        }
        alert("Sesión iniciada correctamente");

        // Redirigir al usuario a otra página, por ejemplo, al index
        window.location.href = "carrito.html"; 
    })



    .catch(error => {
        // Mostrar el error si ocurre uno (como credenciales inválidas)
        alert(error.message);
    });
});
