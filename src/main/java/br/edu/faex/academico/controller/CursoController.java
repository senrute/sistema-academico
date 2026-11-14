
package br.edu.faex.academico.controller;

import br.edu.faex.academico.model.Curso;
import br.edu.faex.academico.service.CursoService;

import java.util.List;

public class CursoController {

    private CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    public void cadastrar(Curso curso) {
        service.cadastrar(curso);
    }

    public List<Curso> listar() {
        return service.listar();
    }

    public Curso buscarPorId(Long id) {
        return service.buscarPorId(id);
    }

    public void excluir(Long id) {
        service.excluir(id);
    }

    public void atualizar(Curso cursoEditado) {
        service.atualizar(cursoEditado);
    }
}
