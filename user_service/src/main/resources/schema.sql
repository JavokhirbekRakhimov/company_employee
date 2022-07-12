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




