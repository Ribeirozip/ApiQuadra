package com.br.blogging.reserva;

import com.br.blogging.cliente.ClienteModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservas")
public class ReservaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteModel cliente;

    @Column(name = "quadra_id", nullable = false)
    private Integer quadraId;

    @Column(name = "tempo_uso", nullable = false)
    private String tempoUso;

    @Column(name = "quantidade_jogador", nullable = false)
    private Integer quantidadeJogador;

    @Column(nullable = false)
    private Boolean status;
}
