insert into Turma (nome) values ('A1 - Manha');
insert into Turma (nome) values ('A2 - Tarde');
insert into Turma (nome) values ('A3 - Noite');
insert into Turma (nome) values ('B1 - Manha');
insert into Turma (nome) values ('B2 - Tarde');
insert into Turma (nome) values ('B3 - Noite');
insert into Turma (nome) values ('C1 - Manha');
insert into Turma (nome) values ('C2 - Tarde');
 
insert into Disciplina (nome, credito) values ('Matematica', 5);
insert into Disciplina (nome, credito) values ('Portugues', 5);
insert into Disciplina (nome, credito) values ('Ingles', 3);
insert into Disciplina (nome, credito) values ('Geografia', 0);
insert into Disciplina (nome, credito) values ('Economia', 2);

insert into Pessoa (nome, cpf, pessoa_sexo, pessoa_tipo) values ('Giovana Letícia da Conceição', '46880576877', 'F', 'B');
insert into Pessoa (nome, cpf, pessoa_sexo, pessoa_tipo) values ('Isaac Noah Miguel da Mota', '15217406909', 'M', 'S');
insert into Pessoa (nome, cpf, pessoa_sexo, pessoa_tipo) values ('Victor Daniel Santos', '06315893686', 'M', 'B');
insert into Pessoa (nome, cpf, pessoa_sexo, pessoa_tipo) values ('Yasmin Isis Nunes', '18751535190', 'F', 'S');
insert into Pessoa (nome, cpf, pessoa_sexo, pessoa_tipo) values ('Rita Maya Jéssica Santos', '28803990410', 'O', 'B');

--insert into Aluno (ALUNO_TIPO_BOLSA,  ALUNOVLMENSALIDADE, ALUNOID) values (1, 1000, 1);
insert into Aluno (pessoa_id, turma_id, disciplina_id, bolsa, mensalidade) values (1, 1, 1, 90, 100);

insert into Aluno (pessoa_id, turma_id, disciplina_id, bolsa, mensalidade) values (2, 2, 1, 50, 1000);
insert into Aluno (pessoa_id, turma_id, disciplina_id, bolsa, mensalidade) values (2, 2, 2, 50, 1000);

insert into Aluno (pessoa_id, turma_id, disciplina_id, bolsa, mensalidade) values (3, 3, 1, 50, 900);