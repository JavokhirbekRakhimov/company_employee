/*  1 */
create or replace function user_find_by_userName_and_password (in_user_name varchar,in_password varchar)
    returns json
    language plpgsql
as
$$
declare
    message varchar;
    success boolean;
    content json;
    httpStatus smallint;
begin
    success=(select case when count(u.id)>0 then true else false end from users u where active=true and  user_name=in_user_name and password=crypt(in_password,password) );
    if success=false then
        message='User not found';
        content='{}';
        httpStatus=404;
        return jsonb_build_object('message',message,'success',success,'content',content,'httpStatus',httpStatus);
    else
        message='Ok';
        httpStatus=200;
        content=(select json_build_object('id',users.id,'first_name',fist_name,'last_name',last_name,'user_name',user_name,'email',users.email,'phone',users.phone,
                                          'companies',(select json_agg(sub) from (select c.id,c.name,c.email,c.phone,json_agg(r) as roles from company c join user_organization_roles uor on c.id = uor.company_id join role r on r.id = uor.role_id where user_id=users.id group by c.id) as sub))
                 from users
                 where user_name=in_user_name and  password=crypt(in_password,password) group by users.id);
        return jsonb_build_object('message',message,'success',success,'content',content,'httpStatus',httpStatus);
    end if;
end;
$$;

/*   2  */
create or replace function user_find_by_userName (in_user_name varchar)
    returns json
    language plpgsql
as
$$
declare
    message varchar;
    success boolean;
    content json;
    httpStatus smallint;
begin
    success=(select case when count(u.id)>0 then true else false end from users u where active=true and  user_name=in_user_name );
    if success=false then
        message='User not found';
        content='{}';
        httpStatus=404;
        return jsonb_build_object('message',message,'success',success,'content',content,'httpStatus',httpStatus);
    else
        message='Ok';
        httpStatus=200;
        content=(select json_build_object('id',users.id,'first_name',fist_name,'last_name',last_name,'user_name',user_name,'email',users.email,'phone',users.phone,'verification_email',verification_email,'active',active,
                                          'companies',(select json_agg(sub) from (select c.id,c.name,c.email,c.phone,json_agg(r) as roles from company c join user_organization_roles uor on c.id = uor.company_id join role r on r.id = uor.role_id where user_id=users.id group by c.id) as sub))
                 from users
                 where users.active=true and user_name=in_user_name  group by users.id);
        return jsonb_build_object('message',message,'success',success,'content',content,'httpStatus',httpStatus);
    end if;
end;
$$;