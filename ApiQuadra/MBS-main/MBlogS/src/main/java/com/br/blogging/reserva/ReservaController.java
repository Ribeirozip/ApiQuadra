package com.br.blogging.reserva;

import com.br.blogging.cliente.ClienteModel;
import com.br.blogging.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
@CrossOrigin("*")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Criar uma nova reserva com um cliente fixo
    @PostMapping("/criar")
    public ResponseEntity<String> criarReserva(@RequestBody ReservaModel reserva) {
        try {
            // Cliente fixo para simplificar
            Optional<ClienteModel> clienteFixo = clienteRepository.findById(1L); // Cliente com ID = 1
            if (clienteFixo.isPresent()) {
                reserva.setCliente(clienteFixo.get());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente fixo não encontrado.");
            }

            // Salvar reserva
            ReservaModel novaReserva = reservaRepository.save(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body("Reserva criada com sucesso! ID: " + novaReserva.getIdReserva());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar reserva: " + e.getMessage());
        }
    }

    // Listar todas as reservas
    @GetMapping
    public ResponseEntity<List<ReservaModel>> listarReservas() {
        List<ReservaModel> reservas = reservaRepository.findAll();
        return ResponseEntity.ok(reservas);
    }

    // Deletar uma reserva
    @DeleteMapping("/{idReserva}")
    public ResponseEntity<String> deletarReserva(@PathVariable Long idReserva) {
        if (idReserva == null) {
            return ResponseEntity.badRequest().body("O ID da reserva é necessário para exclusão.");
        }

        Optional<ReservaModel> reservaExistente = reservaRepository.findById(idReserva);
        if (reservaExistente.isPresent()) {
            reservaRepository.deleteById(idReserva);
            return ResponseEntity.ok("Reserva deletada com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não encontrada.");
        }
    }
}
