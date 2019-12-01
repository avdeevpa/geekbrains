create table tasks (
    ID bigserial,
    CAPTION varchar(255),
    OWNER varchar(255),
    STATUS varchar(32),
    ASSIGNED varchar(255),
    DESCRIPTION varchar(255),
    primary key(id));

insert into TASKS(CAPTION, OWNER, STATUS, ASSIGNED, DESCRIPTION) values
('Task 1', 'Somebody', 'CREATED', 'Executor', 'Description 1'),
('Task 2', 'Somebody', 'CREATED', 'Executor', 'Description 2'),
('Task 3', 'Somebody', 'CREATED', 'Executor', 'Description 3');
