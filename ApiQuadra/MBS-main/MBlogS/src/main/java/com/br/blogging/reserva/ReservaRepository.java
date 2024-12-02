package com.br.blogging.reserva;

import com.br.blogging.cliente.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<ReservaModel, Long> {
    List<ReservaModel> findByCliente(ClienteModel cliente);

    List<ReservaModel> findByStatus(Boolean status);
}
