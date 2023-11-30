package com.projetos.CestAlunos.application.controllers;

import com.projetos.CestAlunos.domain.entity.Curso;
import com.projetos.CestAlunos.domain.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<Curso> criarCurso(@RequestBody Curso curso) {
        Curso novoCurso = cursoService.salvar(curso);
        return new ResponseEntity<>(novoCurso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        curso.setId(id);
        Curso cursoAtualizado = cursoService.atualizar(curso);
        return ResponseEntity.ok(cursoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable Long id) {
        cursoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obterCursoPorId(@PathVariable Long id) {
        Curso curso = cursoService.buscarPorId(id);
        return curso != null ? ResponseEntity.ok(curso) : ResponseEntity.notFound().build();
    }


    @GetMapping
    public ResponseEntity<List<Curso>> listarTodosCursos() {
        List<Curso> todosCursos = cursoService.buscarTodos();
        return ResponseEntity.ok(todosCursos);
    }

    // Adicione aqui métodos para endpoints adicionais conforme o fluxo do projeto

}
