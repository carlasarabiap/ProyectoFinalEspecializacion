document.addEventListener("DOMContentLoaded", () => {
    const productos = [
        { id: 1, titulo: "Albóndigas con Puré", precio: 500, img: "imagenes/albondigas_pure.jpeg" },
        { id: 2, titulo: "Canelones", precio: 450, img: "imagenes/canelones.jpeg" },
        { id: 3, titulo: "Pastel de Papas", precio: 600, img: "imagenes/pastel_de_papa.jpeg" },
        { id: 4, titulo: "Empanadas", precio: 350, img: "imagenes/empanadas.jpeg" },
        { id: 5, titulo: "Tortillas", precio: 300, img: "imagenes/tortilla.jpeg" }
    ];

    const cardsContainer = document.getElementById("cards");
    const carrito = [];
    const templateCard = document.getElementById("template-card").content;
    const templateCarrito = document.getElementById("template-carrito").content;
    const itemsContainer = document.getElementById("items");
    const footer = document.getElementById("footer");

    const subtotalElement = document.getElementById("subtotal");
    const envioElement = document.getElementById("envio");
    const totalElement = document.getElementById("total");
    const entregaSelect = document.getElementById("entrega");

    productos.forEach(producto => {
        const clone = templateCard.cloneNode(true);
        clone.querySelector(".card-img-top").src = producto.img;
        clone.querySelector(".card-title").textContent = producto.titulo;
        clone.querySelector(".card-price span").textContent = producto.precio;
        clone.querySelector(".btn-add-to-cart").dataset.id = producto.id;
        cardsContainer.appendChild(clone);
    });

    cardsContainer.addEventListener("click", e => {
        if (e.target.classList.contains("btn-add-to-cart")) {
            const id = parseInt(e.target.dataset.id);
            const producto = productos.find(p => p.id === id);
            const itemInCart = carrito.find(item => item.id === id);

            if (itemInCart) {
                itemInCart.cantidad++;
            } else {
                carrito.push({ ...producto, cantidad: 1 });
            }

            actualizarCarrito();
        }
    });

    entregaSelect.addEventListener("change", actualizarCarrito);

    function actualizarCarrito() {
        itemsContainer.innerHTML = "";
        carrito.forEach(item => {
            const clone = templateCarrito.cloneNode(true);
            clone.querySelector("th").textContent = item.id;
            clone.querySelectorAll("td")[0].textContent = item.titulo;
            clone.querySelectorAll("td")[1].textContent = item.cantidad;
            clone.querySelectorAll("td span")[0].textContent = item.precio * item.cantidad;
            itemsContainer.appendChild(clone);
        });

        const subtotal = carrito.reduce((acc, item) => acc + item.precio * item.cantidad, 0);
        const envio = parseInt(entregaSelect.value || 0);
        const total = subtotal + envio;

        if (carrito.length) {
            document.getElementById("total-row").style.display = "";
            document.getElementById("envio-row").style.display = envio > 0 ? "" : "none";
            document.getElementById("final-row").style.display = "";

            subtotalElement.textContent = subtotal;
            envioElement.textContent = envio;
            totalElement.textContent = total;

            footer.innerHTML = "";
        } else {
            document.getElementById("total-row").style.display = "none";
            document.getElementById("envio-row").style.display = "none";
            document.getElementById("final-row").style.display = "none";

            footer.innerHTML = `<th scope="row" colspan="5">Carrito vacío - comience a comprar!</th>`;
        }
    }
});
