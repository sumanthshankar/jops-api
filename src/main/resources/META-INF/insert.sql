insert into roles(role_id, role) values(1, 'ROLE_ADMIN');
insert into roles(role_id, role) values(2, 'ROLE_USER');

insert into users(user_id, active, email_id, first_name, last_name, password, user_role_id) 
values(1, true, 'test1@test1.com', 'Test1 First', 'Test1 Last', '$2a$10$VuBLEOY9d9GLU4TPwqjtQef0MQID5BnH93Qcf8X3h5So0myMjSqdO', 1);

insert into users(user_id, active, email_id, first_name, last_name, password, user_role_id) 
values(2, true, 'test2@test2.com', 'Test2 First', 'Test2 Last', '$2a$10$4UEfetRZxT/mylx8KHpWSeX5UEsT9fRC2iyURyX0PQRhsxGvcbwaG', 2);

insert into users_roles(user_id, role_id) values(1, 1);
insert into users_roles(user_id, role_id) values(2, 2);