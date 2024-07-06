drop table annotations

create table if not exists tasks
(
    id      SERIAL unique primary key ,
    title    varchar(250),
    category varchar(250) not null,
    priority varchar(250) not null,
    situation varchar(250) not null,
    description varchar(250),
    limit_date DATE,
    owner integer not null
    );

insert into tasks (title, category, priority, situation, description, limit_date, owner) values ('tarefa teste', 'Planejamento', 'MEDIA', 'PENDENTE', 'Testar a criacao de tarefas', TO_TIMESTAMP(1678829350918 / 1000),  1 );
insert into tasks (title, category, priority, situation, description, limit_date, owner) values ('tarefa teste', 'Planejamento', 'ALTA', 'INICIADA', 'Testar a criacao de tarefas', TO_TIMESTAMP(1678829350918 / 1000),  1 );


create table if not exists annotations (id SERIAL unique primary key, task_id integer not null, annotation varchar(1000));

insert into annotations (task_id, annotation) values (3 , 'anotacao para teste');
insert into annotations (task_id, annotation) values (3 , 'Segunda anotacao para teste');

create table if not exists categorys (
                                         id SERIAL unique primary key,
                                         name varchar(250),
    description varchar(250),
    default_priority varchar(250)
    );

insert into categorys (name , description, default_priority) values ('Analise' , 'analise de viabilidade e estimativa de tempo', '2' );
insert into categorys (name , description, default_priority) values ('Implementacao' , 'Implemenntacao de tarefa ja estimada', '3' );

create table if not exists users (id SERIAL unique primary key, username varchar(250) not null, password varchar(250) not null, role varchar (250) not null);

insert into users (username , password, role) values (0,'admin@exemplo.com', 'senhaForte123', 'ROLE_ADMIN');
insert into users (username , password, role) values (1,'user@exemplo.com', 'senhaFraca123', 'ROLE_USER');