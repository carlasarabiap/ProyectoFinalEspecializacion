// Selecciona el formulario
const form = document.getElementById('contact-form');

// Escucha el evento de envío del formulario
form.addEventListener('submit', function (event) {
  event.preventDefault(); // Evita el comportamiento predeterminado de recargar la página

  // Obtén los datos del formulario
  const serviceID = "default_service"; // Reemplaza con tu Service ID de EmailJS
  const templateID = "template_0pvdl9j"; // Reemplaza con tu Template ID de EmailJS

    emailjs.sendForm(serviceID, templateID, form)
    .then(() => {
        alert('Mensaje enviado con éxito!');
      form.reset(); // Limpia el formulario
    }, (err) => {
        alert('Hubo un error al enviar el mensaje:', err);
    });
});
