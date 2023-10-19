insert into truck(id, model, year_built, vin, active)
values(1000, 'Ford F150', '2022', 'ZXCS14785247HGDF', 1);
insert into truck(id, model, year_built, vin, active)
values(1001, 'Ford F150', '2020', 'FJTHS14785247HGDF', 1);
insert into truck(id, model, year_built, vin, active)
values(1002, 'RAM 150', '2022',   'ZX4FGTEF85247HGDF', 1);
insert into truck(id, model, year_built, vin, active)
values(1003, 'RAM 150', '2020',    'GDF455896354FKJN', 1);
insert into truck(id, model, year_built, vin, active)
values(1004, 'NISSAN TITAN', '2022', 'Z44GHTNEO247HGDF', 1);
insert into truck(id, model, year_built, vin, active)
values(1005, 'NISSAN TITAN', '2020', 'KLFUYEH875247HGDF', 1);
insert into truck(id, model, year_built, vin, active)
values(1006, 'TOYOTA TUNDRA', '2022', 'H78FKKTNS14247HDF', 1);
insert into truck(id, model, year_built, vin, active)
values(1007, 'TOYOTA TUNDRA', '2020', 'KKELT7348ZXCS147HG', 1);

insert into status(id, description, operated)
values(2000, 'Loading at warehouse', 1);
insert into status(id, description, operated)
values(2001, 'Outbound for deliveries', 1);
insert into status(id, description, operated)
values(2002, 'Returning to warehouse', 1);
insert into status(id, description, operated)
values(2003, 'Maintenance', 0);
insert into status(id, description, operated)
values(2004, 'Idle in the warehouse', 1);

insert into employee(id, name, driver_licence, category_id, active)
values(3000, 'Bob', 'DL0001', 4000, 1);
insert into employee(id, name, driver_licence, category_id, active)
values(3001, 'Paul', 'DL0002', 4000, 1);
insert into employee(id, name, driver_licence, category_id, active)
values(3002, 'Steven', 'DL0003', 4000, 1);
insert into employee(id, name, driver_licence, category_id, active)
values(3003, 'Greg', 'DL0004', 4000, 1);
insert into employee(id, name, driver_licence, category_id, active)
values(3004, 'David', 'DL0005', 4000, 1);
insert into employee(id, name, driver_licence, category_id, active)
values(3005, 'Marie', '', 4001, 1);
insert into employee(id, name, driver_licence, category_id, active)
values(3006, 'Susan', '', 4001, 1);

insert into user_category(id, name, active)
values(4000, 'Driver', 1);
insert into user_category(id, name, active)
values(4001, 'Operator', 1);

insert into truck_status(id, truck_id, status_id, status_update_time, is_last_status)
values(5000, 1000, 2000, CURRENT_TIMESTAMP(), 1);
insert into truck_status(id, truck_id, status_id, status_update_time, is_last_status)
values(5001, 1001, 2002, CURRENT_TIMESTAMP(), 1);
insert into truck_status(id, truck_id, status_id, status_update_time, is_last_status)
values(5002, 1002, 2003, CURRENT_TIMESTAMP(), 1);
insert into truck_status(id, truck_id, status_id, status_update_time, is_last_status)
values(5003, 1003, 2004, CURRENT_TIMESTAMP(), 1);
insert into truck_status(id, truck_id, status_id, status_update_time, is_last_status)
values(5004, 1004, 2002, CURRENT_TIMESTAMP(), 1);
insert into truck_status(id, truck_id, status_id, status_update_time, is_last_status)
values(5005, 1005, 2000, CURRENT_TIMESTAMP(), 1);
insert into truck_status(id, truck_id, status_id, status_update_time, is_last_status)
values(5006, 1006, 2001, CURRENT_TIMESTAMP(), 1);

insert into driver_assignment(id, driver_id, truck_id, assignment_date_time, active)
values(6000, 3000, 1000, CURRENT_TIMESTAMP(), 1);
insert into driver_assignment(id, driver_id, truck_id, assignment_date_time, active)
values(6001, 3001, 1001, CURRENT_TIMESTAMP(), 1);
insert into driver_assignment(id, driver_id, truck_id, assignment_date_time, active)
values(6002, 3002, 1003, CURRENT_TIMESTAMP(), 1);
insert into driver_assignment(id, driver_id, truck_id, assignment_date_time, active)
values(6003, 3003, 1004, CURRENT_TIMESTAMP(), 1);
insert into driver_assignment(id, driver_id, truck_id, assignment_date_time, active)
values(6004, 3004, 1005, CURRENT_TIMESTAMP(), 1);



