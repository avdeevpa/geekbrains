declare
  v_tmp number;
begin
  begin
    execute immediate 'drop table admin.tasks';
  exception when others then
    null;
  end;
  begin
    execute immediate 'drop sequence admin.s_tasks';
  exception when others then
    null;
  end;

  execute immediate 'create table admin.tasks ( id number, caption varchar2(200), owner varchar2(200), assigned varchar2(200), description varchar2(2000),  status varchar2(20) )';
  execute immediate 'alter table admin.tasks add (primary key (id) enable validate)';

  execute immediate 'create sequence admin.s_tasks minvalue 1 start with 1 increment by 1 cache 20';
  execute immediate
  'create trigger admin.i_tasks_pk before insert or update on admin.tasks for each row ' ||
  'begin '||
  'if :new.id is null then '||
  '  :new.id := admin.s_tasks.nextval; '||
  'end if; '||
  'end; ';
end;