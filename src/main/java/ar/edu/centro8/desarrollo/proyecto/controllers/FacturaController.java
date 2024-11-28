package ar.edu.centro8.desarrollo.proyecto.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.desarrollo.proyecto.models.Cliente;
import ar.edu.centro8.desarrollo.proyecto.models.Factura;
import ar.edu.centro8.desarrollo.proyecto.models.Pedido;
import ar.edu.centro8.desarrollo.proyecto.services.FacturaService;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService faServi;
    
    @GetMapping
    public List<Factura> getAllFacturas() {
        return faServi.obtenerFacturas();
    }

    @GetMapping("/{id}")
    public Factura getFacturaById(@PathVariable Long id) {
        return faServi.traerFactura(id);
    }
    
    @PostMapping("pedidos/{id_pedido}")

    public ResponseEntity<?> crearFactura(@PathVariable Long id_pedido, @RequestBody Factura facturaRequest) {
        try {
            // Buscar el pedido asociado
            Pedido pedido = faServi.obtenerPedidoPorId(id_pedido);

            // Configurar los datos de la factura
            Factura nuevaFactura = new Factura();
            nuevaFactura.setTotal(facturaRequest.getTotal()); // Asignar el total
            nuevaFactura.setEstado(facturaRequest.getEstado()); // Asignar el estado
            nuevaFactura.setPedido(pedido); // Asociar el pedido

            // Guardar la factura
            Factura facturaCreada = faServi.guardarFactura(nuevaFactura);

            // Construir la respuesta con la información del cliente y la factura creada
            Pedido pedidoAsociado = facturaCreada.getPedido();
            Cliente clienteAsociado = pedidoAsociado.getCliente();

            Map<String, Object> response = new HashMap<>();
            response.put("facturaId", facturaCreada.getId());
            response.put("fecha", facturaCreada.getFecha());
            response.put("total", facturaCreada.getTotal());
            response.put("estado", facturaCreada.getEstado());
            response.put("cliente", clienteAsociado);
            response.put("id_pedido", pedidoAsociado.getId());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void updateFactura(@PathVariable Long id, @RequestBody Factura factura) {
        faServi.editarFactura(id, factura.getTotal(), factura.getEstado());
    }
    
    @DeleteMapping("/{id}")
    public void deleteFactura(@PathVariable Long id) {
        faServi.eliminarFactura(id);
    }
}