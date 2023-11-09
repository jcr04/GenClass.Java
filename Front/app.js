document.addEventListener('DOMContentLoaded', function() {
    const apiUrl = 'http://localhost:8081/api/alunos';

    // Função para listar alunos
    function listarAlunos() {
        fetch(apiUrl)
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
        fetch(apiUrl, {
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

    // Evento de submit do formulário
    const form = document.getElementById('alunoForm');
    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const aluno = {
            nome: document.getElementById('nome').value,
            matricula: document.getElementById('matricula').value,
            curso: document.getElementById('curso').value
        };

        salvarAluno(aluno);
    });

    // Inicializa a lista de alunos
    listarAlunos();
});
