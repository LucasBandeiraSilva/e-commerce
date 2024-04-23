create table funcionarios (
        id bigint not null auto_increment,
        cpf varchar(255),
        email varchar(255) check (email<=150),
        nome varchar(255) check (nome<=100),
        senha varchar(255) check ((senha<=50) and (senha>=6)),
        ativo boolean not null,
        role varchar(255) check (role in ('ADMIN','ESTOQUISTA')),
        primary key (id)
    )