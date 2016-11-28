insert User(email,password,active) values('prac@prac.com.br','jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=', true);
insert Role(name) values('ADMIN');
insert User_Role(User_email,roles_name) values('prac@prac.com.br','ADMIN');    
insert Customer(companyName, companyRegistration, municipalRegistration, stateRegistration, street, number, complement,  city, state,  zipCode, contact, email, jobTitle,  telephoneNumber, cellPhoneNumber, active) values('Smart Cube IT - Soluções em Tecnologia', '24.152.817/0001-43', '123123213', '321321312', 'Rua Vergueiro', 6264, 'Casa 2', 'São Paulo', 'SP', '04272-100', 'Fernando Furtado', 'feh.wilinando@gmail.com', 'Desenvolvedor Java','1124232419', '11960458507', true);
INSERT INTO Certified VALUES (1,0,'123231231',NULL,'Guarulhos',123123,'2016-11-28',123,1);