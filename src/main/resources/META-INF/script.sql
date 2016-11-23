insert User(email,password,active) values('prac@prac.com.br','jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=', true);
insert Role(name) values('ADMIN');
insert Role(name) values('USER');
insert User_Role(User_email,roles_name) values('prac@prac.com.br','ADMIN');    
insert Customer(companyName,street,number,complement,city,state,zipCode,companyRegistration,municipalRegistration,stateRegistration,user_email) values('Programa de Responsabilidade Ambiental Compartilhada','Avenida Pacaembu',1976,'','São Paulo','SP','01234-000', '08.883.961/0001-99','3.634.637-3','','prac@prac.com.br');