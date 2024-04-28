package br.com.projeto.labpcp.service;


import br.com.projeto.labpcp.datasource.entity.AlunoEntity;
import br.com.projeto.labpcp.datasource.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;


    public AlunoEntity salvar(AlunoEntity aluno) {
        return alunoRepository.save(aluno);
    }

    public AlunoEntity buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));

    }

    public List<AlunoEntity> buscarTodos() {
        return alunoRepository.findAll();
    }

    public void excluirPorId(Long id) {
        alunoRepository.deleteById(id);
    }
}