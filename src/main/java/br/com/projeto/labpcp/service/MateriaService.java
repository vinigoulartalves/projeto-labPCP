package br.com.projeto.labpcp.service;

import br.com.projeto.labpcp.datasource.entity.MateriaEntity;
import br.com.projeto.labpcp.datasource.repository.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MateriaService {

    private final MateriaRepository materiaRepository;

    public MateriaEntity salvar(MateriaEntity materia) {
        return materiaRepository.save(materia);
    }

    public List<MateriaEntity> buscarTodos() {
        return materiaRepository.findAll();
    }

    public MateriaEntity buscarPorId(Long id) {
        return materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));
    }

    public void excluirPorId(Long id) {
        materiaRepository.deleteById(id);
    }

    public MateriaEntity alterar(MateriaEntity materia) {
        return materiaRepository.save(materia);
    }
}
