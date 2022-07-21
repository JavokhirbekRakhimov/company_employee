create table  if not exists test( id serial primary key ,
                                     name varchar not null ,
                                     email varchar not null unique,
                                     phone varchar not null unique check ( length(phone) <18 or length(phone)>7));