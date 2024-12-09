document.addEventListener("DOMContentLoaded", async () => {
  const cardsContainer = document.getElementById("cards");
  const carrito = [];
  const templateCard = document.getElementById("template-card").content;
  const templateCarrito = document.getElementById("template-carrito").content;
  const itemsContainer = document.getElementById("items");
  const footer = document.getElementById("footer");

  async function cargarProductos() {
    try {
      const response = await fetch("http://localhost:8080/api/menu");
      if (!response.ok) throw new Error("Error al cargar los productos");
      return await response.json();
    } catch (error) {
      console.error("Error:", error);
      return [];
    }
  }

  const productos = await cargarProductos();

  // Renderizar tarjetas del menú
  productos.forEach(producto => {
    const clone = templateCard.cloneNode(true);
    clone.querySelector(".card-img-top").src = producto.imagenUrl;
    clone.querySelector(".card-title").textContent = producto.nombre;
    clone.querySelector(".card-price span").textContent = producto.precio.toFixed(2);
    clone.querySelector(".btn-add-to-cart").dataset.id = producto.id;
    cardsContainer.appendChild(clone);
  });

  // Agregar producto al carrito
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

  // Modificar cantidades en el carrito
  itemsContainer.addEventListener("click", e => {
    const id = parseInt(e.target.dataset.id);

    if (e.target.classList.contains("btn-info")) {
      const item = carrito.find(item => item.id === id);
      if (item) item.cantidad++;
    }

    if (e.target.classList.contains("btn-danger")) {
      const item = carrito.find(item => item.id === id);
      if (item) {
        item.cantidad--;
        if (item.cantidad <= 0) {
          const index = carrito.findIndex(item => item.id === id);
          carrito.splice(index, 1);
        }
      }
    }

    actualizarCarrito();
  });

  // Actualizar carrito en el DOM
  function actualizarCarrito() {
    itemsContainer.innerHTML = "";
    carrito.forEach(item => {
      const clone = templateCarrito.cloneNode(true);
      clone.querySelector("th").textContent = item.id;
      clone.querySelectorAll("td")[0].textContent = item.nombre; // Nombre del producto
      clone.querySelectorAll("td")[1].textContent = item.cantidad; // Cantidad
      clone.querySelectorAll("td span")[0].textContent = (item.precio * item.cantidad).toFixed(2); // Precio total

      clone.querySelector(".btn-info").dataset.id = item.id;
      clone.querySelector(".btn-danger").dataset.id = item.id;

      itemsContainer.appendChild(clone);
    });

    footer.innerHTML = carrito.length
      ? `<th scope="row" colspan="5">Total: $${carrito.reduce((acc, item) => acc + item.precio * item.cantidad, 0).toFixed(2)}</th>`
      : `<th scope="row" colspan="5">Carrito vacío - comience a comprar!</th>`;
  }

  // Evento para el botón "Confirmar Compra"
  const confirmButton = document.getElementById("compra"); 
  console.log("Botón encontrado, agregando evento.");
  confirmButton.addEventListener("click", async () => {
    console.log("entró en el evento click");
    const notasAdicionales = document.getElementById("Info") ? document.getElementById("Info").value : "";

    // Crear el objeto de productos con id_menu y cantidad
    const menuIdsConCantidades = {};
    carrito.forEach(item => {
      menuIdsConCantidades[item.id] = item.cantidad;
    });

      const pedido = {
        notas: notasAdicionales,
        menuIdsConCantidades: menuIdsConCantidades
      };

      // ID del cliente
      const idCliente = 4; // ID de cliente estático como mencionaste

      console.log("Carrito antes de enviar:", carrito);

      // Enviar los datos al servidor
      try {
        const response = await fetch(`http://localhost:8080/api/pedidos/clientes/${idCliente}`, {
          method: "POST",  // Enviar como POST para crear un nuevo pedido
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(pedido)
        });

        if (!response.ok) {
          const errorText = await response.text();
          console.log("Error en la respuesta del servidor:", errorText);
          throw new Error("Error al enviar el pedido");
        }

        const result = await response.json();
        console.log("Pedido confirmado:", result);

        // Redirigir o mostrar mensaje de éxito
        alert("Compra realizada con éxito!");
        carrito.length = 0;  // Vaciar el carrito después de la compra
        actualizarCarrito();  // Actualizar la vista del carrito

        // Redirigir a la página de factura
        window.location.href = "factura.html";

        // Guardar el id_cliente en localStorage o sesión para usarlo en factura.html
        localStorage.setItem("idCliente", idCliente);

      } catch (error) {
        console.error("Error al enviar el pedido:", error);
        alert("Hubo un problema al procesar tu compra.");
      }
    });
  })