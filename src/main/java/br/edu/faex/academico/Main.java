package br.edu.faex.academico;

import br.edu.faex.academico.controller.AlunoController;
import br.edu.faex.academico.model.Aluno;
import br.edu.faex.academico.repository.AlunoRepository;
import br.edu.faex.academico.service.AlunoService;
import br.edu.faex.academico.controller.CursoController;
import br.edu.faex.academico.controller.ProfessorController;
import br.edu.faex.academico.model.Curso;
import br.edu.faex.academico.model.Professor;
import br.edu.faex.academico.repository.CursoRepository;
import br.edu.faex.academico.repository.ProfessorRepository;
import br.edu.faex.academico.service.CursoService;
import br.edu.faex.academico.service.ProfessorService;

import java.util.List;

public class Main {
    static void main() {
        // =====================================
        // ALUNOS
        // =====================================
        Aluno aluno1 = new Aluno("Aleandro Lima", "aleandro.lima2@faex.edu.br");
        Aluno aluno2 = new Aluno("Maria Lima", "maria.lima2@faex.edu.br");
        Aluno aluno3 = new Aluno("José Lima", "jose.lima2@faex.edu.br");

        AlunoRepository alunoRepository = new AlunoRepository();
        AlunoService alunoService = new AlunoService(alunoRepository);
        AlunoController alunoController = new AlunoController(alunoService);

        // Cadastro de Alunos BD
        alunoController.cadastrar(aluno1);
        alunoController.cadastrar(aluno2);
        alunoController.cadastrar(aluno3);

        // Listar Alunos BD
        System.out.println("\n===== LISTA DE ALUNOS =====");
        List<Aluno> alunos = alunoController.listar();

        for (Aluno aluno : alunos) {
            System.out.println("ID: " + aluno.getId());
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("E-mail: " + aluno.getEmail());
            System.out.println("Ativo: " + aluno.isAtivo());
            System.out.println("--------------------------");
        }

        Aluno alunoBuscado = alunoController.buscarPorId(1L);
        if (alunoBuscado != null) {
            System.out.println("Aluno encontrado!");
            System.out.println("ID: " + alunoBuscado.getId());
            System.out.println("Nome: " + alunoBuscado.getNome());
            System.out.println("E-mail: " + alunoBuscado.getEmail());
        } else {
            System.out.println("Aluno não encontrado!");
        }

        // =====================================
        // CURSOS
        // =====================================
        CursoRepository cursoRepository = new CursoRepository();
        CursoService cursoService = new CursoService(cursoRepository);
        CursoController cursoController = new CursoController(cursoService);

        // CADASTRAR CURSOS
        Curso curso1 = new Curso("Análise e Desenvolvimento de Sistemas", "Presencial");
        Curso curso2 = new Curso("Sistemas de Informação", "EAD");
        cursoController.cadastrar(curso1);
        cursoController.cadastrar(curso2);
        List<Curso> cursoss = cursoController.listar();

        // LISTAR CURSOS
        System.out.println("\n===== LISTA DE CURSOS =====");
        List<Curso> cursos = cursoController.listar();
        for (Curso curso : cursos) {
            System.out.println("ID: " + curso.getId());
            System.out.println("Nome: " + curso.getNome());
            System.out.println("Modalidade: " + curso.getModalidade());
            System.out.println("--------------------------");
        }

        // EXCLUIR CURSO
        if (!cursos.isEmpty()) {
            Long idCurso = cursos.get(0).getId();
            cursoController.excluir(idCurso);
            System.out.println("\nCurso excluído com sucesso!");
        }

        // =====================================
        // PROFESSORES
        // =====================================
        ProfessorRepository professorRepository = new ProfessorRepository();
        ProfessorService professorService = new ProfessorService(professorRepository);
        ProfessorController professorController = new ProfessorController(professorService);

        // CADASTRAR PROFESSORES
        Professor professor1 = new Professor("Carlos Oliveira", "carlos.oliveira@faex.edu.br");
        Professor professor2 = new Professor("Ana Souza", "ana.souza@faex.edu.br");
        professorController.cadastrar(professor1);
        professorController.cadastrar(professor2);

        // LISTAR PROFESSORES
        System.out.println("\n===== LISTA DE PROFESSORES =====");
        List<Professor> professores = professorController.listar();
        for (Professor professor : professores) {
            System.out.println("ID: " + professor.getId());
            System.out.println("Nome: " + professor.getNome());
            System.out.println("E-mail: " + professor.getEmail());
            System.out.println("--------------------------");
        }

        // EXCLUIR PROFESSOR
        if (!professores.isEmpty()) {
            Long idProfessor = professores.get(0).getId();
            professorController.excluir(idProfessor);
            System.out.println("\nProfessor excluído com sucesso!");
        }
    }
}