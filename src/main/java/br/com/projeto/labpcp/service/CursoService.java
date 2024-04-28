package br.com.projeto.labpcp.service;

import br.com.projeto.labpcp.datasource.entity.CursoEntity;
import br.com.projeto.labpcp.datasource.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;


    public CursoEntity salvarCurso(CursoEntity curso) {
        return cursoRepository.save(curso);
    }

    public CursoEntity buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));

    }

    public List<CursoEntity> buscarTodos() {
        return cursoRepository.findAll();
    }

    public void excluirPorId(Long id) {
        cursoRepository.deleteById(id);
    }
}