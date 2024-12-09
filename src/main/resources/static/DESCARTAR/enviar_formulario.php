<?php
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\SMTP;
use PHPMailer\PHPMailer\Exception;

require 'path/to/PHPMailer/src/Exception.php';
require 'path/to/PHPMailer/src/PHPMailer.php';
require 'path/to/PHPMailer/src/SMTP.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Dirección de correo a la que se enviará el formulario
    $para = "bettycalatayud@gmail.com";

    // Datos del formulario
    $email = $_POST["email"];
    $nombreApellido = $_POST["nombreApellido"];
    $telefono = $_POST["telefono"];
    $comentario = $_POST["comentario"];

    // Asunto del correo
    $asunto = "Nuevo mensaje de contacto";

    // Cuerpo del correo
    $mensaje = "Correo: $email\n";
    $mensaje .= "Nombre y Apellido: $nombreApellido\n";
    $mensaje .= "Teléfono: $telefono\n";
    $mensaje .= "Comentario: $comentario";

    // Cabeceras del correo
    $cabeceras = "From: $email";

    // Enviar correo
    if (mail($para, $asunto, $mensaje, $cabeceras)) {
        echo "Mensaje enviado con éxito.";
    } else {
        echo "Error al enviar el mensaje.";
    }
}
?>
