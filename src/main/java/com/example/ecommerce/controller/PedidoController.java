package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Pedido;
import com.example.ecommerce.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedido/")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> save(@Valid @RequestBody Pedido pedido){
        Pedido novoPedido = pedidoService.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@Valid @RequestBody Pedido pedido, @PathVariable Long id){
        Pedido pedidoAtualizado = pedidoService.update(pedido, id);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll(){
        List<Pedido> pedidos = pedidoService.findAll();
        if(pedidos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id){
        return pedidoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        pedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
