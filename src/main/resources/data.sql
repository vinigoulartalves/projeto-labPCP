INSERT INTO PAPEL (id, nome)
VALUES (1, 'ADMIN'),
       (2, 'PROFESSOR'),
       (3, 'PEDAGOGICO'),
       (4, 'RECRUTADOR'),
       (5, 'ALUNO');

INSERT INTO USUARIO (id, login, senha, id_papel)
VALUES (1, 'LeonardoVieira', 'teste', 1);