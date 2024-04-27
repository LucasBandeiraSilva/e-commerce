drop table funcionarios;

create table funcionarios (
        id bigint not null auto_increment,
        cpf varchar(255) not null,
        email varchar(255) not null unique,
        nome varchar(100) not null,
        senha varchar(255) not null,
        ativo boolean not null,
        role varchar(255) not null,
        primary key (id)
    )