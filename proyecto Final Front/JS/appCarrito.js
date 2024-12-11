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
  if (!confirmButton) {
      console.error("El botón de Confirmar Compra no se encuentra en la página.");
      return;
  }

  confirmButton.addEventListener("click", async () => {
      try {
          console.log("Entró en el evento click");

          const notasAdicionales = document.getElementById("Info") ? document.getElementById("Info").value : "";

          if (!carrito || carrito.length === 0) {
              alert("El carrito está vacío. Agrega productos antes de confirmar la compra.");
              return;
          }

          const menuIdsConCantidades = carrito.reduce((obj, item) => {
            obj[item.id] = item.cantidad;
            return obj;
        }, {});
        
          const pedido = {
              notas: notasAdicionales || "Pedido sin notas", // Valor predeterminado para notas
              menuIdsConCantidades: menuIdsConCantidades
          };

        //   const idCliente = 4; // ID de cliente estático
        // carrito.js

        // Recuperar la información del cliente desde sessionStorage
        const idCliente = sessionStorage.getItem("id_cliente");

        // Verificar si los datos están disponibles
        if (!idCliente.ok) {
            console.log("No se encontró la información del cliente");
        }


          // Enviar los datos al servidor
          const response = await fetch(`http://localhost:8080/api/pedidos/clientes/${idCliente}`, {
              method: "POST",
              headers: {
                  "Content-Type": "application/json"
              },
              body: JSON.stringify(pedido)
          });

          if (!response.ok) {
              const errorText = await response.text();
              console.log("Error en la respuesta del servidor:", errorText);
              throw new Error("Error al enviar el pedido.");
          }

          const pedidoCreado = await response.json();
          console.log("Pedido confirmado:", pedidoCreado);

          // Guardar datos en sessionStorage
          sessionStorage.setItem("id_pedido", pedidoCreado.id_pedido);
          sessionStorage.setItem("total_pedido", carrito.reduce((acc, item) => acc + item.precio * item.cantidad, 0));

          alert("Compra realizada con éxito!");
          carrito.length = 0; // Vaciar el carrito después de la compra
          actualizarCarrito();

          console.log("Respuesta del servidor (pedido creado):", pedidoCreado);

          // Redirigir a factura.html
          window.location.href = `factura.html?idPedido=${pedidoCreado.id_pedido}`;
      } catch (error) {
          console.error("Error al enviar el pedido:", error);
          alert("Hubo un problema al procesar tu compra.");
      }
  });
});
