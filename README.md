Método de uso:

- Executar no cmd / powershell: docker run -d  --name projetotlabpcp  -e POSTGRES_PASSWORD=minhaSenha  -e POSTGRES_USER=meuUsuario  -e POSTGRES_DB=projetolabpcp_db  -p 1442:5432  postgres:latest
- Executar o projeto-labPCP;
- Executar as querys no PGAdmin após acrescentar o banco de dados na visualização:

INSERT INTO PAPEL (id, nome) ON CONFLICT (id) DO NOTHING;
VALUES (1, 'ADMIN'),
       (2, 'PROFESSOR'),
       (3, 'PEDAGOGICO'),
       (4, 'RECRUTADOR'),
       (5, 'ALUNO');

INSERT INTO USUARIO (id, login, senha, id_papel) ON CONFLICT (id) DO NOTHING;
VALUES (1, 'ADMIN', '$2a$12$aDfmCFK8eDZfJZTQ5fqkkuqRpxFtLQUPst68Td9MpHQcM4vidq9PS', 1);


- Os endpoint estão no arquivo "LabPCP.postman_collection.json" dentro da raiz do projeto;
- OBS: O primeiro usuário criado vai dar erro pela questão id unique, mas os proximos serão adicionados corretamente ao banco.
