package com.br.blogging.cliente;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
@EqualsAndHashCode(of = "idCliente")
public class ClienteModel {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_cliente")
        @JsonProperty("id_cliente")
        private Long idCliente;

        @Column(nullable = false)
        private String nome;

        @Column(unique = true, nullable = false)
        private String email;

        @Column(nullable = false)
        private String senha;

        public ClienteModel(Long idCliente) {
            this.idCliente = idCliente;
        }
    }

