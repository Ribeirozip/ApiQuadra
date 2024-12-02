package com.br.blogging.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
    ClienteModel findByEmail(String email);

    ClienteModel findByEmailAndSenha(String email, String senha);

    boolean existsByEmail(String email);

}
