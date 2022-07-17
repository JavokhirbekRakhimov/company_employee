/*   1  */
create or replace function find_all_roles()
    returns json
    language plpgsql
as
$$
declare
    message    varchar;
    success    boolean;
    content    json;
    httpStatus smallint;
BEGIN
    content = (select json_agg(result) from (select * from role order by id) as result);
    message = 'Ok';
    success = true;
    httpStatus = 200;
    return jsonb_build_object('message', message, 'success', success, 'content', content, 'httpStatus', httpStatus);
end;
$$;

/*  2  */

create or replace function find_by_id_role(in_id smallint)
    returns json
    language plpgsql
as
$$
declare
    message    varchar;
    success    boolean;
    content    json;
    httpStatus smallint;
BEGIN
    success = (select exists(select * from role where id = in_id));
    if success = false then
        message = 'Role not found ';
        httpStatus = 404;
        content = '{}';
        return jsonb_build_object('message', message, 'success', success, 'content', content, 'httpStatus', httpStatus);
    else
        content = (select json_build_object('id', r.id, 'name', r.name, 'rank', r.rank)
                   from role r
                   where id = in_id
                   limit 1);
        message = 'Ok';
        success = true;
        httpStatus = 200;
        return jsonb_build_object('message', message, 'success', success, 'content', content, 'httpStatus', httpStatus);
    end if;
end;
$$;

/*  create role */
create or replace function create_role(in_json text)
    returns json
    language plpgsql
as
$$
declare
    message    varchar;
    success    boolean;
    content    json;
    httpStatus smallint;
    /* aditional */
    in_name    varchar;
    in_id      smallint;

begin
    in_name = (select name
               from
                   json_populate_record(Null::role_type, in_json::json)
               limit 1);

    success = (select exists(select id from role where name ilike in_name));

    if success then
        message = 'This name already exist';
        content = '{}';
        success = false;
        httpStatus = 409;
        return jsonb_build_object('message', message, 'success', success, 'content', content, 'httpStatus', httpStatus);
    else
        insert into role(name, rank)
        select name, rank
        from json_populate_record(Null::role_type, in_json::json)
        returning id into in_id;
        content = (select row_to_json(r) from role as r where id = in_id);
        message = 'Save';
        httpStatus = 200;
        success = true;
        return jsonb_build_object('message', message, 'success', success, 'content', content, 'httpStatus', httpStatus);
    end if;

end;
$$;