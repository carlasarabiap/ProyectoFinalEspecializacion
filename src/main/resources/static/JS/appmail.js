import emailjs from '@emailjs/browser';

const btn = document.getElementById("button");

document.getElementById("form").addEventListener("submit", function (event) {
    event.preventDefault();

    btn.value = "Sending...";

    const serviceID = "default_service";
    const templateID = "template_0pvdl9j";

   //  const serviceID = 'default_service';
   // const templateID = 'template_91hla59';

    emailjs.sendForm(serviceID, templateID, this)
        .then(
        () => {
            btn.value = "send Email";
            alert("sent!");
        },
        (err) => {
            btn.value = "send Email";
            alert(JSON.stringify(err));
        } 
    );

});




