insert into tab_conta_corrente values ('1111111111',100);
insert into tab_conta_corrente values ('2222222222',200);
insert into tab_conta_corrente values ('3333333333',300);
insert into tab_conta_corrente values ('4444444444',400);
insert into tab_conta_corrente values ('5555555555',500);
commit;
insert into tab_operacao_financeira (data_agendamento,data_execucao,observacao,saldo_instantaneo, tipo_operacao, valor_operacao, conta_corrente) 
values(now(), now(), 'Saldo Inicial', 100,'DEPOSITO',100,'1111111111');
insert into tab_operacao_financeira (data_agendamento,data_execucao,observacao,saldo_instantaneo, tipo_operacao, valor_operacao, conta_corrente) 
values(now(), now(), 'Saldo Inicial', 200,'DEPOSITO',200,'2222222222');
insert into tab_operacao_financeira (data_agendamento,data_execucao,observacao,saldo_instantaneo, tipo_operacao, valor_operacao, conta_corrente) 
values(now(), now(), 'Saldo Inicial', 300,'DEPOSITO',300,'3333333333');
insert into tab_operacao_financeira (data_agendamento,data_execucao,observacao,saldo_instantaneo, tipo_operacao, valor_operacao, conta_corrente) 
values(now(), now(), 'Saldo Inicial', 400,'DEPOSITO',400,'4444444444');
insert into tab_operacao_financeira (data_agendamento,data_execucao,observacao,saldo_instantaneo, tipo_operacao, valor_operacao, conta_corrente) 
values(now(), now(), 'Saldo Inicial', 500,'DEPOSITO',500,'5555555555');
commit;