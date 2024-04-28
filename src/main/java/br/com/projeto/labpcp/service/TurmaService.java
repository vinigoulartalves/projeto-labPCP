package br.com.projeto.labpcp.service;

import br.com.projeto.labpcp.datasource.entity.TurmaEntity;
import br.com.projeto.labpcp.datasource.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private TurmaRepository turmaRepository;

    public TurmaEntity salvar(TurmaEntity turma) {
        return turmaRepository.save(turma);
    }

    public List<TurmaEntity> buscarTodos() {
        return turmaRepository.findAll();
    }

    public TurmaEntity buscarPorId(Long id) {
        return turmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));
    }

    public void ExcluirPorId(Long id) {
        turmaRepository.deleteById(id);
    }

    public TurmaEntity alterar(TurmaEntity turma) {
        return turmaRepository.save(turma);
    }

}
