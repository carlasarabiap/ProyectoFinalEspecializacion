document.addEventListener('DOMContentLoaded', () => {
    // Seleccionamos el formulario
    const formulario = document.querySelector('form');
  
    formulario.addEventListener('submit', async (event) => {
      event.preventDefault(); // Evitamos que el formulario recargue la página.
  
      // Capturamos los valores de los campos del formulario
      const nombre = document.getElementById('apellidoNombre').value.trim();
      const direccion = document.getElementById('direccion').value.trim();
      const email = document.getElementById('email').value.trim();
      const password = document.getElementById('password').value.trim();
      const telefono = parseInt(document.getElementById('telefono').value.trim(), 10);
      const edad = parseInt(document.getElementById('edad').value.trim(), 10);
  
      // Validamos los datos antes de enviarlos
      if (!nombre || !direccion || !email || !password || isNaN(telefono) || isNaN(edad)) {
        alert('Por favor, completa todos los campos correctamente.');
        return;
      }
  
      // Creamos el objeto JSON con los datos del cliente
      const cliente = {
        nombre,
        email,
        telefono,
        direccion,
        edad,
        password,
      };
  
      try {
        // Hacemos la solicitud POST al servidor
        const response = await fetch('http://localhost:8080/api/clientes', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(cliente),
        });
  
        if (response.ok) {
            // Si la solicitud es exitosa, mostramos un mensaje y reseteamos el formulario
            const data = await response.json();
            alert('Cliente registrado con éxito.');
            console.log('Respuesta del servidor:', data);
            formulario.reset(); // Limpiamos el formulario

            // Redirige al login después de 2 segundos (opcional para que el usuario vea el mensaje)
                setTimeout(() => {
                    window.location.href = 'sesion.html'; // Asegúrate de que esta sea la URL correcta
                }, 2000);
        } else {
          // Si hay un error, mostramos un mensaje al usuario
          const error = await response.json();
          alert('Error al registrar cliente: ' + (error.message || 'Error desconocido.'));
          console.error('Error del servidor:', error);
        }
      } catch (error) {
        // Si hay un error en la conexión o en el proceso, lo manejamos aquí
        console.error('Error en la solicitud:', error);
        alert('Error al conectar con el servidor. Inténtalo de nuevo más tarde.');
      }
    });
  });
  