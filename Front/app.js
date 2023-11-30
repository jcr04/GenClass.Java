document.addEventListener('DOMContentLoaded', function () {
    const apiUrlAlunos = 'http://localhost:8081/api/alunos';
    const apiUrlProfessores = 'http://localhost:8081/api/professores';
    const apiUrlAvaliacoes = 'http://localhost:8081/api/avaliacoes';

    // Função para listar alunos
    function listarAlunos() {
        fetch(apiUrlAlunos)
            .then(response => response.json())
            .then(data => {
                const lista = document.getElementById('listaAlunos');
                lista.innerHTML = '';
                data.forEach(aluno => {
                    lista.innerHTML += `<li>${aluno.nome} - ${aluno.matricula} - ${aluno.curso}</li>`;
                });
            })
            .catch(error => console.error('Erro ao listar alunos:', error));
    }

    // Função para salvar aluno
    function salvarAluno(aluno) {
        fetch(apiUrlAlunos, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(aluno)
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error('Erro ao salvar o aluno');
            })
            .then(data => {
                console.log('Aluno salvo:', data);
                listarAlunos(); // Atualiza a lista após salvar
            })
            .catch(error => console.error('Erro ao salvar aluno:', error));
    }

    // Evento de submit do formulário de alunos
    const formAluno = document.getElementById('alunoForm');
    formAluno.addEventListener('submit', function (event) {
        event.preventDefault();

        const aluno = {
            nome: document.getElementById('nomeAluno').value,
            matricula: document.getElementById('matricula').value,
            curso: document.getElementById('curso').value
        };

        salvarAluno(aluno);
    });

    function listarProfessores() {
        fetch(apiUrlProfessores)
            .then(response => response.json())
            .then(data => {
                const lista = document.getElementById('listaProfessores');
                lista.innerHTML = '';
                data.forEach(professor => {
                    lista.innerHTML += `<li>${professor.nome} - ${professor.matricula} - ${professor.departamento}</li>`;
                });
            })
            .catch(error => console.error('Erro ao listar professores:', error));
    }

    function salvarProfessor(professor) {
        fetch(apiUrlProfessores, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(professor)
        })
            .then(response => response.ok ? response.json() : Promise.reject(response))
            .then(data => console.log('Professor salvo:', data))
            .catch(error => console.error('Erro ao salvar professor:', error));
    }


    // Evento de submit do formulário de professores
    const formProfessor = document.getElementById('professorForm');
    formProfessor.addEventListener('submit', function (event) {
        event.preventDefault();

        const professor = {
            nome: document.getElementById('nomeProfessor').value,
            matricula: document.getElementById('matriculaProfessor').value,
            departamento: document.getElementById('departamento').value
        };

        salvarProfessor(professor);
    });

    // Função para listar avaliações
    function listarAvaliacoes() {
        fetch(apiUrlAvaliacoes)
            .then(response => response.json())
            .then(data => {
                const lista = document.getElementById('listaAvaliacao');
                lista.innerHTML = '';
                data.forEach(avaliacao => {
                    lista.innerHTML += `<li>Aluno: ${avaliacao.aluno.nome}, Professor: ${avaliacao.professor.nome}, Nota: ${avaliacao.nota}</li>`;
                });
            })
            .catch(error => console.error('Erro ao listar avaliações:', error));
    }

    // Função para atribuir nota
    function atribuirNota(avaliacao) {
        fetch(apiUrlAvaliacoes, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(avaliacao)
        })
            .then(response => response.ok ? response.json() : Promise.reject(response))
            .then(data => console.log('Avaliação salva:', data))
            .catch(error => console.error('Erro ao salvar avaliação:', error));
    }

    // Evento de submit do formulário de avaliações
    const formAvaliacao = document.getElementById('avaliacaoForm');
    formAvaliacao.addEventListener('submit', function (event) {
        event.preventDefault();

        const avaliacao = {
            nota: parseFloat(document.getElementById('nota').value),
            aluno: { id: parseInt(document.getElementById('idAluno').value) },
            professor: { id: parseInt(document.getElementById('idProfessor').value) }
        };

        atribuirNota(avaliacao);
    });


    // Inicializa a lista de alunos
    listarAlunos();
    listarProfessores();
    listarAvaliacoes()
});
