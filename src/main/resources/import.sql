INSERT INTO users (email,password,user_type) VALUES ("ioannis@ioannou.gr","$2a$12$fVEaqcCA4qMRVxAXaiT/ueSXh.7BAtXDO6n0btcdiQzgus02JR7F6","users");
INSERT INTO users (email,password,user_type) VALUES ("georgios@georgiou.gr","$2a$12$fVEaqcCA4qMRVxAXaiT/ueSXh.7BAtXDO6n0btcdiQzgus02JR7F6","users");
INSERT INTO users (email,password,user_type) VALUES ("akis@akis.gr","$2a$12$fVEaqcCA4qMRVxAXaiT/ueSXh.7BAtXDO6n0btcdiQzgus02JR7F6","users");
INSERT INTO users (email,password,user_type) VALUES ("akis@gmail.com","$2a$12$fVEaqcCA4qMRVxAXaiT/ueSXh.7BAtXDO6n0btcdiQzgus02JR7F6","admin");
INSERT INTO users (email,password,user_type) VALUES ("user@carrepair.com","$2y$12$MpbHG3QF4nNhzlUL.jVmq.fhJSYFDgaEntOYyxhK1Ho/Y8jeRbhyK","admin");

INSERT INTO members (address,firstname,lastname,vat,user_id) VALUES ("Karpenisiou 3","loannis","Ioannou","123234345",1);
INSERT INTO members (address,firstname,lastname,vat,user_id) VALUES ("Kavalas 12","Georgios","Georgiou","123345789",2);
INSERT INTO members (address,firstname,lastname,vat,user_id) VALUES ("Athinas 8","Akis","Dimopoulos","456852397",3);
INSERT INTO members (address,firstname,lastname,vat,user_id) VALUES ("Kabalas 10","Kostas","Poulakakis","222312312",4);

INSERT INTO vehicles (brand,color,model,plate,year,user_id) VALUES ("BMW","Red","Model1","ABC-1234","2017-01-01",1);
INSERT INTO vehicles (brand,color,model,plate,year,user_id) VALUES ("Toyota","Blue","Model1","KEF-1234","2012-01-01",2);
INSERT INTO vehicles (brand,color,model,plate,year,user_id) VALUES ("Nissan","Black","Model1","BHI-1234","1999-01-01",3);
INSERT INTO vehicles (brand,color,model,plate,year,user_id) VALUES ("Scoda","White","Model1","AKL-1234","2000-01-01",4);
INSERT INTO vehicles (brand,color,model,plate,year,user_id) VALUES ("BMW","Red","Model1","ABC-4321","2002-01-01",4);
INSERT INTO vehicles (brand,color,model,plate,year,user_id) VALUES ("Toyota","Blue","Model1","HHH-4321","2014-01-01",3);
INSERT INTO vehicles (brand,color,model,plate,year,user_id) VALUES ("Nissan","Black","Model1","XHI-4321","2014-01-01",2);
INSERT INTO vehicles (brand,color,model,plate,year,user_id) VALUES ("Scoda","White","Model1","OPI-4321","2014-01-01",1);

INSERT INTO repairs (repair_date,cost,description,status,type,vehicle_id) VALUES ("2017-10-27 11:00:00","100","This is a description1","0",false,1);
INSERT INTO repairs (repair_date,cost,description,status,type,vehicle_id) VALUES ("2017-10-27 12:00:00","100","This is a description2","1",true,2);
INSERT INTO repairs (repair_date,cost,description,status,type,vehicle_id) VALUES ("2017-10-27 13:00:00","100","This is a description3","2",false,3);
INSERT INTO repairs (repair_date,cost,description,status,type,vehicle_id) VALUES ("2017-10-27 10:00:00","100","This is a description4","0",true,4);
INSERT INTO repairs (repair_date,cost,description,status,type,vehicle_id) VALUES ("2017-10-27 09:00:00","100","This is a description1","0",false,1);
INSERT INTO repairs (repair_date,cost,description,status,type,vehicle_id) VALUES ("2017-10-27 08:00:00","100","This is a description2","1",true,2);
INSERT INTO repairs (repair_date,cost,description,status,type,vehicle_id) VALUES ("2017-10-27 07:00:00","100","This is a description3","2",false,3);
INSERT INTO repairs (repair_date,cost,description,status,type,vehicle_id) VALUES ("2017-10-27 15:30:00","100","This is a description4","0",true,4);
INSERT INTO repairs (repair_date,cost,description,status,type,vehicle_id) VALUES ("2017-10-27 15:45:00","100","This is a description1","0",false,1);
INSERT INTO repairs (repair_date,cost,description,status,type,vehicle_id) VALUES ("2017-10-27 15:15:00","100","This is a description2","1",true,2);
INSERT INTO repairs (repair_date,cost,description,status,type,vehicle_id) VALUES ("2017-10-27 20:15:00","100","This is a description3","2",false,3);
INSERT INTO repairs (repair_date,cost,description,status,type,vehicle_id) VALUES ("2017-10-27 19:30:00","100","This is a description4","0",true,4);
INSERT INTO repairs (repair_date,cost,description,status,type,vehicle_id) VALUES ("2017-10-27 14:45:00","100","This is a description1","0",false,1);
INSERT INTO repairs (repair_date,cost,description,status,type,vehicle_id) VALUES ("2017-10-24 15:00:00","100","This is a description2","1",true,2);
INSERT INTO repairs (repair_date,cost,description,status,type,vehicle_id) VALUES ("2017-10-25 15:00:00","100","This is a description3","2",false,3);
INSERT INTO repairs (repair_date,cost,description,status,type,vehicle_id) VALUES ("2017-10-26 15:00:00","100","This is a description4","0",true,4);





