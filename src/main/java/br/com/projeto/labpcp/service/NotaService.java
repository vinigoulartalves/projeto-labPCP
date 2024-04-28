package br.com.projeto.labpcp.service;

import br.com.projeto.labpcp.datasource.entity.NotaEntity;
import br.com.projeto.labpcp.datasource.repository.NotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotaService {

    private final NotaRepository notaRepository;

    public List<NotaEntity> buscarTodos() {
        return notaRepository.findAll();
    }

    public NotaEntity buscarPorId(Long id) {
        return notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota não encontrada com o ID: " + id));
    }

    public NotaEntity salvar(NotaEntity nota) {
        return notaRepository.save(nota);
    }

    public void excluir(Long id) {
        notaRepository.deleteById(id);
    }
}
