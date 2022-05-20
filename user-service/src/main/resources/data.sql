insert into users (id,email,full_name,password,phone_number,username)values (1,'email@gmail.com','Jam','123','+998936279955','ali')
insert into roles(id,name)values (1,'ROLE_USER')
insert into roles_users(roles_id,users_id) values(1,1)