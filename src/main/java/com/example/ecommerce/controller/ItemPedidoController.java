package com.example.ecommerce.controller;

import com.example.ecommerce.entity.ItemPedido;
import com.example.ecommerce.service.ItemPedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/itempedido/")
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    public ItemPedidoController(ItemPedidoService itemPedidoService){
        this.itemPedidoService = itemPedidoService;
    }

    @PostMapping
    public ResponseEntity<ItemPedido> save(@Valid @RequestBody ItemPedido itemPedido){
        ItemPedido novoItemPedido = itemPedidoService.save(itemPedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoItemPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@Valid @RequestBody ItemPedido itemPedido, @PathVariable Long id){
        ItemPedido itemPedidoAtualizado = itemPedidoService.update(itemPedido, id);
        return ResponseEntity.ok(itemPedidoAtualizado);
    }

    @GetMapping
    public ResponseEntity<List<ItemPedido>> findAll(){
        List<ItemPedido> itemPedidos = itemPedidoService.findAll();
        if (itemPedidos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(itemPedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> findById(@PathVariable Long id){
        return itemPedidoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        itemPedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
