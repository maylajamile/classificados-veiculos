package fila.mensagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fila.mensagem.model.Veiculo;

public interface VeiculosRepositorio extends JpaRepository<Veiculo, Long> {

}
