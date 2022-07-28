-- Recreate the schema
DROP SCHEMA if exists public CASCADE;
CREATE SCHEMA if not exists public;

-- Restore default permissions
GRANT ALL ON SCHEMA  public TO postgres;
GRANT ALL ON SCHEMA public TO public;

  /* create users table */
  CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

  CREATE EXTENSION if not exists pgcrypto;

  create table if not exists users( id uuid DEFAULT uuid_generate_v4() primary key,
                                    fist_name varchar not null ,
                                    last_name varchar not null ,
                                    user_name varchar not null unique ,
                                    password text not null ,
                                    active boolean default true,
                                    email varchar not null unique ,
                                    verification_email boolean default false,
                                    phone varchar not null unique check ( length(phone) <18 or length(phone)>7));
/* create company table */
create table  if not exists company( id serial primary key ,
                                    name varchar not null ,
                                    email varchar not null unique,
                                    phone varchar not null unique check ( length(phone) <18 or length(phone)>7));

/* create role table */
  create table  if not exists role( id smallserial primary key ,
                                       name varchar not null unique ,
                                       rank smallint );
/* for create table */
  drop type if exists role_type;
  create type role_type as (id smallint,name varchar,rank smallint);

/* create user_organization_roles table */
  create table if not exists user_organization_roles ( user_id uuid not null ,
                                                      company_id int not null ,
                                                      role_id smallint not null,
                                                      constraint fk_user foreign key(user_id) references users(id),
                                                      constraint fk_company foreign key(company_id) references company(id) on delete cascade ,
                                                      constraint fk_role foreign key(role_id) references role(id) on delete cascade
                                                                      );

/* default user*/
insert into role(name, rank) values ('ADMIN',1);
insert into company(name, email, phone) VALUES ('Google','javohirbekrakhimov@gmail.com','+998997834961');
insert into users(fist_name, last_name, user_name, password, email, phone) values ('Javokhirbek','Rakhimov','Jokha',crypt('@Jokha',gen_salt('bf')),'javohirbekrakhimov@gmail.com','+998997834961');
insert into user_organization_roles(user_id, company_id, role_id) values ((select id from users where user_name='Jokha'),(select id from company where email='javohirbekrakhimov@gmail.com'),(select id from role where name='ADMIN'));





