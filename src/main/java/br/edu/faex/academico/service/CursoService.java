
package br.edu.faex.academico.service;

import br.edu.faex.academico.model.Curso;
import br.edu.faex.academico.repository.CursoRepository;

import java.util.List;

public class CursoService {

    private CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Curso curso) {

        if (curso == null) {
            System.out.println("Curso inválido.");
            return;
        }

        if (curso.getNome() == null ||
                curso.getNome().isBlank()) {
            System.out.println("O nome do curso é obrigatório.");
            return;
        }

        if (curso.getModalidade() == null ||
                curso.getModalidade().isBlank()) {
            System.out.println("A modalidade do curso é obrigatória.");
            return;
        }

        repository.salvar(curso);
    }

    public List<Curso> listar() {
        return repository.listar();
    }

    public Curso buscarPorId(Long id) {

        if (id == null || id <= 0) {
            System.out.println("ID inválido.");
            return null;
        }

        Curso curso = repository.buscarPorId(id);

        if (curso == null) {
            System.out.println("Curso não encontrado.");
            return null;
        }

        return curso;
    }

    public void excluir(Long id) {

        if (id == null || id <= 0) {
            System.out.println("ID inválido.");
            return;
        }

        Curso curso = repository.buscarPorId(id);

        if (curso == null) {
            System.out.println("Curso não encontrado.");
            return;
        }

        repository.excluir(id);
    }

    public void atualizar(Curso cursoEditado) {

        if (cursoEditado == null ||
                cursoEditado.getId() == null ||
                cursoEditado.getId() <= 0) {
            System.out.println("Curso inválido.");
            return;
        }

        Curso curso = repository.buscarPorId(cursoEditado.getId());

        if (curso == null) {
            System.out.println("Curso não encontrado.");
            return;
        }

        if (cursoEditado.getNome() == null ||
                cursoEditado.getNome().isBlank()) {
            System.out.println("O nome do curso é obrigatório.");
            return;
        }

        if (cursoEditado.getModalidade() == null ||
                cursoEditado.getModalidade().isBlank()) {
            System.out.println("A modalidade do curso é obrigatória.");
            return;
        }

        repository.atualizar(cursoEditado);

        System.out.println("Curso atualizado com sucesso.");
    }
}