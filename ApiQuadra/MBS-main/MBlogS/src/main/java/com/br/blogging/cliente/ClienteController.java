package com.br.blogging.cliente;

import com.br.blogging.reserva.ReservaModel;
import com.br.blogging.reserva.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("*")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrarCliente(@RequestBody ClienteModel cliente) {
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            return ResponseEntity.badRequest().body("E-mail já cadastrado.");
        }
        clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<ClienteModel>> listarClientes() {
        List<ClienteModel> clientes = clienteRepository.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{idCliente}/reservas")
    public ResponseEntity<List<ReservaModel>> listarReservasPorCliente(@PathVariable Long idCliente) {
        Optional<ClienteModel> cliente = clienteRepository.findById(idCliente);
        if (cliente.isPresent()) {
            List<ReservaModel> reservas = reservaRepository.findByCliente(cliente.get());
            return ResponseEntity.ok(reservas);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody ClienteModel cliente) {
        ClienteModel clienteExistente = clienteRepository.findByEmailAndSenha(cliente.getEmail(), cliente.getSenha());
        if (clienteExistente != null) {
            return ResponseEntity.ok("Login realizado com sucesso. ID do Cliente: " + clienteExistente.getIdCliente());
        } else {
            return ResponseEntity.badRequest().body("E-mail ou senha inválidos.");
        }
    }

    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarCliente(@RequestBody ClienteModel cliente) {
        if (cliente.getIdCliente() == null) {
            return ResponseEntity.badRequest().body("O ID do cliente é necessário para atualização.");
        }
        Optional<ClienteModel> clienteExistente = clienteRepository.findById(cliente.getIdCliente());
        if (clienteExistente.isPresent()) {
            clienteRepository.save(cliente);
            return ResponseEntity.ok("Cliente atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<String> deletarCliente(@PathVariable Long idCliente) {
        Optional<ClienteModel> cliente = clienteRepository.findById(idCliente);
        if (cliente.isPresent()) {
            clienteRepository.deleteById(idCliente);
            return ResponseEntity.ok("Cliente deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
    }
}
