/*
nombre del programador: Edgar Josué Sinay Medina
Codigo Tecnico: IN5AV
Carne: 2020157
Fecha de creacion:
	28-04-2021
Modificaciones:
	28-04-2021
    29-04-2021
    05-05-2021
    06-05-2021
    07-05-2021
    08-05-2021
    12-05-2021
    13-05-2021
    22-05-2021
    02-06-2021
    03-06-2021
    09-06-2021
    17-06-2021
*/

Drop database if exists DBKinalMall2020157;
create database DBKinalMall2020157;

use DBKinalMall2020157;

create table Departamentos(
	codigoDepartamento int not null auto_increment,
	nombreDepartamento varchar(45) not null,
    primary key PK_codigoDepartamento (codigoDepartamento)
);

create table Cargos(
	codigoCargo int not null auto_increment,
    nombreCargo varchar(45) not null,
    primary key PK_codigoCargo (codigoCargo)
);

create table Horarios(
	codigoHorario int not null auto_increment,
    horaEntrada varchar(5) not null,
    horaSalida varchar (5) not null,
    lunes boolean,
    martes boolean,
    miercoles boolean,
    jueves boolean,
	viernes boolean,
    primary key PK_codigoHorario (codigoHorario)
);

create table TipoCliente(
	codigoTipoCliente int not null auto_increment,
    descripcion varchar(45) not null,
    primary key PK_codigoTipoCliente (codigoTipoCliente)
);

create table Administracion (
	codigoAdministracion int not null auto_increment,
    direccion varchar(45) not null,
    telefono varchar(8) not null,
    primary key PK_codigoAdministracion (codigoAdministracion)
);

create table Locales (
	codigoLocal int not null auto_increment,
    saldoFavor double(11,2) default 0.0,
    saldoContra double(11,2) default 0.0,
    mesesPendientes int default 0,
    disponibilidad boolean not null,
    valorLocal double(11,2) not null,
    valorAdministracion Double(11,2) not null,
    primary key PK_codigoLocal (codigoLocal)
);

create table Proveedores (
	codigoProveedor int not null auto_increment,
    NITProveedor varchar(45) not null,
    servicioPrestado varchar(45) not null,
    telefonoProveedor varchar (8) not null,
    direccionProveedor varchar (60) not null,
    saldoFavor double not null,
    saldoContra double not null,
    codigoAdministracion int not null,
    primary key PK_codigoProveedor (codigoProveedor),
    constraint FK_Proveedores_Administracion foreign key (codigoAdministracion)
		references Administracion (codigoAdministracion)
);

create table Empleados(
	codigoEmpleado int not null auto_increment,
    nombreEmpleado varchar(45) not null,
    apellidoEmpleado varchar(45) not null,
    correoElectronico varchar(45) not null,
    telefonoEmpleado varchar(8) not null,
    fechaContratacion date not null,
    sueldo double(11,2) not null,
    primary key PK_codigoEmpleado (codigoEmpleado),
	codigoDepartamento int not null,
    codigoCargo int not null,
    codigoHorario int not null,
    codigoAdministracion int not null,
    constraint FK_Empleados_Departamentos foreign key (codigoDepartamento)
		references Departamentos (codigoDepartamento),
    constraint FK_Empleados_Cargos foreign key (codigoCargo)
		references Cargos (codigoCargo),
    constraint FK_Empleados_Horarios foreign key (codigoHorario)
		references Horarios (codigoHorario),
    constraint FK_Empleados_Administracion foreign key (codigoAdministracion)
		references Administracion (codigoAdministracion)
);

create table Clientes(
	codigoCliente int not null auto_increment,
    nombresCliente varchar(45) not null,
    apellidosCliente varchar(45) not null,
    telefonoCliente varchar(8) not null,
    direccionCliente varchar(60) not null,
    email varchar (45) not null,
    codigoLocal int not null,
    codigoAdministracion int not null,
    codigoTipoCliente int not null, 
    primary key PK_codigocliente (codigoCliente),
    constraint FK_Clientes_Locales foreign key (codigoLocal)
		references Locales (codigoLocal),
    constraint FK_Clientes_Administracion foreign key (codigoAdministracion)
		references Administracion (codigoAdministracion),
    constraint FK_Clientes_TipoCliente foreign key (codigoTipoCliente)
		references TipoCliente (codigoTipoCliente)
);

create table CuentasPorCobrar(
	codigoCuentasPorCobrar int not null auto_increment,
    numeroFactura varchar (45) not null,
    anio int (4) not null,
    mes int (2) not null,
    valorNetoPago Double (11,2) not null,
    estadoPago varchar(45)not null,
    codigoAdministracion int not null,
    codigoCliente int not null,
    codigoLocal int not null,
    primary key PK_CodigoCuentasPorCobrar (codigoCuentasPorCobrar),
    constraint FK_CuentasPorCobrar_Administracion foreign key (codigoAdministracion)
		references Administracion (codigoAdministracion),
    constraint FK_CuentasPorCobrar_Clientes foreign key (codigoCliente)
		references Clientes (codigoCliente),
    constraint FK_CuentasPorCobrar_Locales foreign key (codigoLocal)
		references Locales (codigoLocal)
);

create table CuentasPorPagar(
	codigoCuentasPorPagar int not null auto_increment,
    numeroFactura varchar (45) not null,
    fechaLimitePago date not null,
    estadoPago varchar(45) not null,
    valorNetoPago double(10,2) not null,
    codigoAdministracion int not null, 
    codigoProveedor int not null,
    primary key PK_codigoCuentasPorPagar (codigoCuentasPorPagar),
    constraint FK_CuentasPorPagar_Administracion foreign key (codigoAdministracion)
		references Administracion (codigoAdministracion),
    constraint FK_CuentasPorPagar_Proveedores foreign key (codigoProveedor)
		references Proveedores (codigoProveedor)
);

-------------------------------
-- Procedimientos Departamentos
-- Agregar Departamentos
delimiter $$
	create procedure sp_AgregarDepartamentos(in ag_nombreDepartamento varchar(45))
		begin
			insert into Departamentos(nombreDepartamento)values(ag_nombreDepartamento);
		end$$
delimiter ;
call sp_AgregarDepartamentos('Internet');
select * from Departamentos;
-- Editar Departamentos
delimiter $$
	create procedure sp_EditarDepartamentos(in ed_codigoDepartamento int, in ed_nombreDepartamento varchar(45))
	begin
		update Departamentos
			set
			nombreDepartamento = ed_nombreDepartamento
				where codigoDepartamento = ed_codigoDepartamento;
	end$$
delimiter ;

-- call sp_EditarDepartamentos(1, 'Internet');

-- Eliminar Departamentos
delimiter $$
	create procedure sp_EliminarDepartamentos(in el_codigoDepartamento int)
	begin 
		delete from Departamentos where codigoDepartamento = el_codigoDepartamento;
	end$$
delimiter ;

-- Buscar Departamentos
delimiter $$
	create procedure sp_BuscarDepartamentos(in bu_codigoDepartamento int)
	begin	
		select
			D.codigoDepartamento,
            D.nombreDepartamento
				from Departamentos D
				where codigoDepartamento = bu_codigoDepartamento;
	end$$
delimiter ;

-- -------- Listar departamentos
delimiter $$
	create procedure sp_ListarDepartamentos()
	begin
		select
			D.codigoDepartamento,
            D.nombreDepartamento
				from Departamentos D;
	end$$
delimiter ;
call sp_ListarDepartamentos(); 
-- -------------------
-- Procedimiento cargo
-- Agregar Cargo

delimiter $$
	create procedure sp_AgregarCargos(in ag_nombreCargo varchar(45))
	begin 
		insert into Cargos(nombreCargo)values(ag_nombreCargo);
	end$$
delimiter ;

call sp_AgregarCargos("Gerente");

-- Editar Cargos
delimiter $$
	create procedure sp_EditarCargos(in ed_codigoCargo int, in ed_nombreCargo varchar(45))
    begin
		update Cargos
			set
				nombreCargo = ed_nombreCargo
                where codigoCargo = ed_codigoCargo;
	end$$
delimiter ;

-- Eliminar Cargos
delimiter $$
	create procedure sp_EliminarCargos (in el_codigoCargo int)
	begin
		delete from Cargos where codigoCargo = el_codigoCargo;
	end$$
delimiter ;

-- Buscar Cargos
delimiter $$
	create procedure sp_BuscarCargos(in bu_codigoCargo int)
    begin 
		Select
		C.codigoCargo,
        C.nombreCargo
        from Cargos C
			where codigoCargo = bu_codigoCargo;
	end$$
delimiter ;

-- Listar Cargos
delimiter $$
	create procedure sp_ListarCargos()
	begin
		select
			C.codigoCargo,
            C.nombreCargo
				from Cargos C;
	end$$
delimiter ;

-- ----------------------
-- Procedimiento Horarios
-- Agregar Horario
delimiter $$
	create procedure sp_AgregarHorarios(in ag_horaEntrada varchar(5), in ag_horaSalida varchar(5), in ag_lunes boolean, in ag_martes boolean,
		in ag_miercoles boolean, in ag_jueves boolean, in ag_viernes boolean)
	begin
		insert into Horarios(horaEntrada, horaSalida, lunes, martes, miercoles, jueves, viernes)values(ag_horaEntrada,
        ag_horaSalida, ag_lunes, ag_martes, ag_miercoles, ag_jueves, ag_viernes);
	end$$
delimiter ;
call sp_AgregarHorarios('4:00','5:00', false, true, true, true, false);

-- Editar Horario
delimiter $$
	create procedure sp_EditarHorarios(in ed_codigoHorario int, in ed_horaEntrada varchar(5), in ed_horaSalida varchar(5), in ed_lunes boolean,
		in ed_martes boolean, in ed_miercoles boolean, in ed_jueves boolean, in ed_viernes boolean)
	begin
		update Horarios
			set
				horaEntrada = ed_horaEntrada,
                horaSalida = ed_horaSalida,
                lunes = ed_lunes,
                martes = ed_martes,
                miercoles = ed_miercoles,
                jueves = ed_jueves,
                viernes = ed_viernes
					where codigoHorario = ed_codigoHorario;
	end$$
delimiter ;

-- Eliminar Horario
delimiter $$
	create procedure sp_EliminarHorarios(in el_codigoHorario int)
	begin
		delete from Horarios where codigoHorario = el_codigoHorario;
	end$$
delimiter ;

-- Buscar Horario
delimiter $$
create procedure sp_BuscarHorarios(in bu_codigoHorario int)
	begin 
		select 
			H.codigoHorario,
            H.horaEntrada,
            H.horaSalida,
            H.lunes,
            H.martes,
            H.miercoles,
            H.jueves,
            H.viernes
				from Horarios H
				where codigoHorario = bu_codigoHorario;
	end$$
delimiter ;

-- Listar Horario
delimiter $$
	create procedure sp_ListarHorarios()
	begin 
		select
			H.codigoHorario,
            H.horaEntrada,
            H.horaSalida,
            H.lunes,
            H.martes,
            H.miercoles,
            H.jueves,
            H.viernes
				from Horarios H;
    end$$
delimiter ;
call sp_ListarHorarios();
-- -------------------------
-- Procedimiento TipoCliente
-- Agregar TipoCliente
delimiter $$
	create procedure sp_AgregarTipoCliente(in ag_descripcion varchar(45))
	begin 
		insert into TipoCliente (descripcion)values(ag_descripcion);
	end $$
delimiter ;

call sp_AgregarTipoCliente('Frecuente');
call sp_AgregarTipoCliente('Nuevo');
call sp_AgregarTipoCliente('Activo');
call sp_AgregarTipoCliente('Poco Frecuente');
select * from TipoCliente;

-- Editar TipoCliente
delimiter $$
	create procedure sp_EditarTipoCliente(in ed_codigoTipoCliente int, in ed_descripcion varchar(45))
	begin
		update TipoCliente
			set
				descripcion = ed_descripcion
					where codigoTipoCliente = ed_codigoTipoCliente;
	end$$
delimiter ;

-- Eliminar TipoCliente
delimiter $$
	create procedure sp_EliminarTipoCliente(in el_codigoTipoCliente int)
	begin
		delete from TipoCliente where codigoTipoCliente = el_codigoTipoCliente;
	end$$
delimiter ;

-- Buscar TipoCliente
delimiter $$
	create procedure sp_BuscarTipoCliente(in bu_codigoTipoCliente int)
	begin
		select 
			T.codigoTipoCliente,
            T.descripcion
            from TipoCliente T
				where codigoTipoCliente = bu_codigoTipoCliente;
    end$$
delimiter ;

-- Listar TipoCliente
delimiter $$
	create procedure sp_ListarTipoCliente()
	begin
		select 
			T.codigoTipoCliente,
            T.descripcion
            from tipoCliente T;
    end$$
delimiter ;

call sp_ListarTipoCliente();
-- -----------------------------
-- Procedimientos Administración 
-- Agregar Administración

delimiter $$ 
	create procedure sp_AgregarAdministracion(in ag_direccion varchar(45), in ag_telefono varchar(8))
    begin
		insert into Administracion(direccion, telefono)
			values (ag_direccion, ag_telefono);
end $$
delimiter ;
	call sp_AgregarAdministracion('3 av, zona 1', '12345678');
    call sp_AgregarAdministracion('5 av, zona 10', '44558899');
    call sp_AgregarAdministracion('Antigua Guatemala', '34345566');
    select * from Administracion;
    
-- Editar Administracion
delimiter $$
	create procedure sp_EditarAdministracion(in ed_codigoAdministracion int,in ed_direccion varchar(45), in ed_telefono varchar(8))
	begin
		update Administracion
			set 
			direccion = ed_direccion,
			telefono = ed_telefono
				where codigoAdministracion = ed_codigoAdministracion;
    end $$
delimiter ;

-- Eliminar Administracion
delimiter $$
	create procedure sp_EliminarAdministracion(in el_codigoAdministracion int)
    begin
		delete from Administracion where codigoAdministracion = el_codigoAdministracion;
	end$$
delimiter ;

-- Buscar Administracion
delimiter $$
	create procedure sp_BuscarAdministracion( in bu_codigoAdministracion int)
	begin
		select 
			A.codigoAdministracion,
            A.direccion,
            A.telefono
				from Administracion A	
					where codigoAdministracion = bu_codigoAdministracion;
	end$$
delimiter ;

-- Listar Administracion
delimiter $$
	create procedure sp_ListarAdministracion ()
		begin 
			select
            A.codigoAdministracion,
            A.direccion,
			A.telefono
				from administracion A;
		end $$
delimiter ;
call sp_ListarAdministracion();

-- ---------------------
-- Procedimiento Locales
-- Agregar Locales

delimiter $$
	create procedure sp_AgregarLocales(in ag_disponibilidad boolean, in ag_valorLocal double(11,2), in ag_valorAdministracion double(11,2))
	begin
		insert into Locales(disponibilidad, valorLocal, valorAdministracion)
			values (ag_disponibilidad,ag_valorLocal, ag_valorAdministracion);
    end$$
delimiter ;
call sp_AgregarLocales(true , 500.00, 150.02);
call sp_AgregarLocales(false, 400.00, 200.00);
call sp_AgregarLocales(true, 1000.00, 1000.00);
call sp_AgregarLocales(false, 3000.00, 2000.00);
call sp_AgregarLocales(false, 2500.00, 1500.00);
call sp_AgregarLocales(true, 3000.00, 1200.00);
call sp_AgregarLocales(true, 2000.00, 2000.00);
call sp_AgregarLocales(true, 2000.00, 1250.00);
call sp_AgregarLocales(false, 200.00, 1230.00);
call sp_AgregarLocales(false, 2000.00, 2000.00);
 -- call sp_ListarLocales(); -- Edgar Josue Sinay Medina 2020157
-- -------------------------------------------
delimiter $$
	create procedure sp_ContarLocales()
		begin
			select count(codigoLocal) as Locales from Locales;
		end$$
delimiter ;

call sp_ContarLocales(); -- Edgar Sinay 2020157

-- ------------------------------------------
delimiter $$
	create procedure sp_ContarDisponibles()
	begin 
		select
			sum(disponibilidad = true) as localesDisponibles,
            sum(disponibilidad = false) as localesNoDisponibles from Locales;
	end$$
delimiter ;

call sp_ContarDisponibles(); -- Edgar Sinay 2020157

-- ------------------------------------------

select * from Locales;

-- Editar Locales
delimiter $$
	create procedure sp_EditarLocales(in ed_codigoLocal int, in ed_disponibilidad boolean, in ed_valorLocal double(11,2), in ed_valorAdministracion double(11,2))
	begin
		update Locales
			set
                disponibilidad = ed_disponibilidad,
                valorLocal = ed_valorLocal,
                valorAdministracion = ed_valorAdministracion
					where codigoLocal = ed_codigoLocal;
	end$$
delimiter ;

-- Eliminar Locales
delimiter $$
	create procedure sp_EliminarLocales(in el_codigoLocal int)
	begin
		delete from Locales where codigoLocal = el_codigoLocal;
	end$$
delimiter ;

-- Buscar Locales
delimiter $$
	create procedure sp_BuscarLocales(in bu_codigoLocal int)
	begin
		select 
			L.codigoLocal,
            L.saldoFavor,
            L.saldoContra,
            L.mesesPendientes,
            L.disponibilidad,
            L.valorLocal,
            L.valorAdministracion
            from Locales L
				where codigoLocal = bu_codigoLocal;
    end$$
delimiter ;
call sp_BuscarLocales(1);
-- Listar Locales
delimiter $$
	create procedure sp_ListarLocales()
	begin
		select 
			L.codigoLocal,
            L.saldoFavor,
            L.saldoContra,
            L.mesesPendientes,
            L.disponibilidad,
            L.valorLocal,
            L.valorAdministracion
				from Locales L;
    end$$
delimiter ;
call sp_ListarLocales();


-- ----------------------------------------
delimiter $$
	create procedure sp_CalcularLiquido()
		begin
			select
            codigoLocal, saldoFavor, saldoContra, mesesPendientes, disponibilidad, valorLocal, valorAdministracion,
			valorLocal*mesesPendientes as 'Meses Pendientes',
            saldoFavor-saldoContra as 'Liquido',
            mesesPendientes-saldoFavor as 'meses en saldo liquido'
            from Locales;
		end$$
delimiter ; 
call sp_CalcularLiquido(); -- Edgar Josue Sinay Medina 2020157
-- --------------------------------------------

----------------------------
-- Procedimiento Proveedores
-- Agregar Proveedores
delimiter $$
	create procedure sp_AgregarProveedores(in ag_NITProveedor varchar(45), in ag_servicioPrestado varchar(45),
		in ag_telefonoProveedor varchar(8), in ag_direccionProveedor varchar (60), in ag_saldoFavor double,
			in ag_saldoContra double, in ag_codigoAdministracion int)
	Begin 
		insert into Proveedores(NITProveedor, servicioPrestado, telefonoProveedor, direccionProveedor, saldoFavor,
			saldoContra, codigoAdministracion)
			values (ag_NITProveedor, ag_servicioPrestado, ag_telefonoProveedor, ag_direccionProveedor,
				ag_saldoFavor, ag_saldoContra, ag_codigoAdministracion);
    end$$
delimiter ;
call sp_AgregarProveedores('123456789', 'Completo', '12312311', 'Zona 21', 500.00, 100.00, 1);
call sp_AgregarProveedores('987654321', 'Incompleto', '12345678', 'Zona 21', 200.00, 400.00, 1);
call sp_AgregarProveedores('123456781', 'Completo', '12312322', 'Zona 21', 100.00, 400.00, 1);
call sp_AgregarProveedores('987654323', 'Incompleto', '2211221', 'Zona 21', 500.00, 100.00, 2);
call sp_AgregarProveedores('12344321', 'Completo', '33443311', 'Zona 21', 500.00, 300.00, 2);
call sp_AgregarProveedores('987654545', 'Incompleto', '1212121', 'Zona 21', 4000.00, 200.00, 2);
call sp_AgregarProveedores('4567890123', 'Completo', '5124771', 'Zona 21', 250.00, 100.00, 3);
call sp_AgregarProveedores('2314567844', 'Incompleto', '61231243', 'Zona 21', 223.00, 111.00, 3);
call sp_AgregarProveedores('2223334455', 'Incompleto', '11234551', 'Zona 21', 2321.00, 2323.00, 2);
call sp_AgregarProveedores('1233356678', 'Completo', '356543', 'Zona 21', 2343.00, 2323.00, 2);

-- ----------------------------------------------------
-- Edgar Josue Sinay Medina - 2020157
-- Actividad #3
-- Liquido de todos los proveedores
delimiter $$
	create procedure sp_CalcularLiquido2()
		begin
			select
            P.codigoProveedor, P.NITProveedor, P.servicioPrestado, P.telefonoProveedor,
				P.direccionProveedor, P.saldoFavor, P.saldoContra,(P.saldoFavor-P.saldoContra) as 'Resultado Liquido'
            from Proveedores P;
		end$$
delimiter ; 
call sp_CalcularLiquido2();


-- ----------------------------------------------------
-- Editar Proveedores
delimiter $$
	create procedure sp_EditarProveedores(in ed_codigoProveedor int,in ed_NITProveedor varchar (45), in ed_servicioPrestado varchar (45), 
		in ed_telefonoProveedor varchar (8), in ed_direccionProveedor varchar(60), in ed_saldoFavor double, in ed_saldoContra double)
    begin
		update proveedores
			set
				NITProveedor = ed_NITProveedor,
				servicioPrestado = ed_servicioPrestado,
				telefonoProveedor = ed_telefonoProveedor,
				direccionProveedor = ed_direccionProveedor,
				saldoFavor = ed_saldoFavor,
				saldoContra = ed_saldoContra
					where codigoProveedor = ed_codigoProveedor;
    end$$
delimiter ;

-- Eliminar Proveedores
delimiter $$
	create procedure sp_EliminarProveedores(in el_codigoProveedor int)
    begin 
		delete from proveedores where codigoProveedor = el_codigoProveedor;
	end$$
delimiter ;

-- Buscar Proveedores
delimiter $$
	create procedure sp_BuscarProveedores(in bu_codigoProveedor int)
    begin
		select
			P.codigoProveedor,
			P.NITProveedor,
            P.servicioPrestado,
            P.telefonoProveedor,
            p.direccionProveedor,
            P.saldoFavor,
            P.saldoContra,
            P.codigoAdministracion
				from Proveedores P
					where codigoProveedor = bu_codigoProveedor;
	end$$
delimiter ;

call sp_BuscarProveedores(1);
-- Listar Proveedores
delimiter $$
	create procedure sp_ListarProveedores()
    begin
		select
			P.codigoProveedor,
			P.NITProveedor,
            P.servicioPrestado,
            P.telefonoProveedor,
            P.direccionProveedor,
            P.saldoFavor,
            P.saldocontra, 
            P.codigoAdministracion
				from Proveedores P;
    end$$
delimiter ;
call sp_ListarProveedores();

-- ----------------------------
-- Procedimiento Empleados
-- Agregar Empleados
delimiter $$
	create procedure sp_AgregarEmpleados(in ag_nombreEmpleado varchar(45), in ag_apellidoEmpleado varchar(45), in ag_correoElectronico varchar(45),
		in ag_telefonoEmpleado varchar(8), in ag_fechaContratacion date, in ag_sueldo double(11,2), in ag_codigoDepartamento int,
			in ag_codigoCargo int, in ag_codigoHorario int, in ag_codigoAdministracion int)
	begin 
		insert into Empleados(nombreEmpleado, apellidoEmpleado, correoElectronico, telefonoEmpleado, fechaContratacion,
			sueldo, codigoDepartamento, codigoCargo, codigoHorario, codigoAdministracion)
			values(ag_nombreEmpleado, ag_apellidoEmpleado, ag_correoElectronico, ag_telefonoEmpleado, ag_fechaContratacion,
				ag_sueldo, ag_codigoDepartamento, ag_codigoCargo, ag_codigoHorario, ag_codigoAdministracion);
    end$$
delimiter ;
call sp_AgregarEmpleados('Edgar', 'Sinay', 'esinay1234@gmail.com', '123123', '2021-03-05', 400, 1,1,1,1);
select * from Empleados;
-- Editar Empleados
delimiter $$
	create procedure sp_EditarEmpleados(in ed_codigoEmpleado int, in ed_nombreEmpleado varchar(45), in ed_apellidoEmpleado varchar(45), in ed_correoElectronico varchar(45),
		in ed_telefonoEmpleado varchar(8), in ed_fechaContratacion date, in ed_sueldo double(11,2))
	begin 
		update Empleados
			set
				nombreEmpleado = ed_nombreEmpleado,
                apellidoEmpleado = ed_apellidoEmpleado,
                correoElectronico = ed_correoElectronico,
                telefonoEmpleado = ed_telefonoEmpleado,
                fechaContratacion = ed_fechaContratacion,
                sueldo = ed_sueldo
					where codigoEmpleado = ed_codigoEmpleado;
    end$$
delimiter ;

-- Eliminar Empleados
delimiter $$
	create procedure sp_EliminarEmpleados(in el_codigoEmpleado int)
	begin
		delete from Empleados where codigoEmpleado = el_codigoEmpleado;
	end$$
delimiter ;

-- Buscar Empleados 
delimiter $$
	create procedure sp_BuscarEmpleados(in bu_codigoEmpleado int)
	begin
		select 
			E.codigoEmpleado,
            E.nombreEmpleado,
            E.apellidoEmpleado,
            E.correoElectronico,
            E.telefonoEmpleado,
            E.fechaContratacion,
            E.sueldo,
            E.codigoDepartamento,
            E.codigoCargo,
            E.codigoHorario,
            E.codigoAdministracion
				from Empleados E
					where codigoEmpleado = bu_codigoEmpleado;
	end$$
delimiter ;

-- Listar Empleados 
delimiter $$
	create procedure sp_ListarEmpleados()
	begin
		select 
			E.codigoEmpleado,
            E.nombreEmpleado,
            E.apellidoEmpleado,
            E.correoElectronico,
            E.telefonoEmpleado,
            E.fechaContratacion,
            E.sueldo,
            E.codigoDepartamento,
            E.codigoCargo,
            E.codigoHorario,
            E.codigoAdministracion
				from Empleados E;
	end$$
delimiter ;

-- --------------------
-- Procedimientos Clientes
-- Agregar Clientes
delimiter $$
	create procedure sp_AgregarClientes(in ag_nombresCliente varchar (45), in ag_apellidosCliente varchar (45), ag_telefonoCliente varchar (8), 
		in ag_direccionCliente varchar (60), in ag_email varchar (45), in ag_codigoLocal int, in ag_codigoAdministracion int, in ag_codigoTipoCliente int)
	begin
		insert into Clientes(nombresCliente, apellidosCliente, telefonoCliente, direccionCliente, email, codigoLocal, codigoAdministracion, codigoTipoCliente)
			values (ag_nombresCliente, ag_apellidosCliente,ag_telefonoCliente, ag_direccionCliente, ag_email, ag_codigoLocal,
				ag_codigoAdministracion,ag_codigoTipoCliente);
	end$$
delimiter ;

call sp_AgregarClientes('Edgar', 'Sinay', '47161122', 'zona 18', 'esinay@gmail.com', 1, 1, 1);
call sp_AgregarClientes('David', 'Medina', '12344321', 'zona 2', 'dmedina@gmail.com', 1, 1, 2);
call sp_AgregarClientes('Fredy', 'Milian', '12344321', 'zona 4', 'fmilian@gmail.com', 1, 1, 2);

-- Editar Clientes
delimiter $$
	create procedure sp_EditarClientes(in ed_codigoCliente int, in ed_nombresCliente varchar (45), in ed_apellidosCliente varchar (45), ed_telefonoCliente varchar (8), 
		in ed_direccionCliente varchar (60), in ed_email varchar (45))
	begin
		update Clientes
			set 
				nombresCliente = ed_nombresCliente,
                apellidosCliente = ed_apellidosCliente,
                telefonoCliente = ed_telefonoCliente,
                direccionCliente = ed_direccionCliente,
                email = ed_email
					where codigoCliente = ed_codigoCliente;
    end$$
delimiter ;

-- Eliminar Clientes
delimiter $$
create procedure sp_EliminarClientes(in el_codigoCliente int)
	begin
		delete from Clientes where codigoCliente = el_codigoCliente;
	end$$
delimiter ;

-- Buscar Clientes
delimiter $$
	create procedure sp_BuscarClientes(in bu_codigoCliente int)
	begin 
		select 
		C.codigoCliente,
        C.nombresCliente,
        C.apellidosCliente,
        C.telefonoCliente,
        C.direccionCliente,
        C.email,
        C.codigoLocal,
        C.codigoAdministracion,
        C.codigoTipoCliente
			from Clientes C
				where codigoCliente = bu_codigoCliente;
	end$$
delimiter ; 

-- Listar Clientes
delimiter $$
	create procedure sp_ListarClientes()
	begin 
		select 
		C.codigoCliente,
        C.nombresCliente,
        C.apellidosCliente,
        C.telefonoCliente,
        C.direccionCliente,
        C.email,
        C.codigoLocal,
        C.codigoAdministracion,
        C.codigoTipoCliente
			from Clientes C;
	end$$
delimiter ; 
call sp_ListarClientes();
-- --------------------
-- Procedimiento Cuentas por Cobrar
-- Agregar CuentasPorCobrar
delimiter $$
	create procedure sp_AgregarCuentasPorCobrar(in ag_numeroFactura varchar(45), in ag_anio int(4), in ag_mes int(2),
		in ag_valorNetoPago double(11,2), in ag_estadoPago varchar(45), in ag_codigoAdministracion int, in ag_codigoCliente int,
			in ag_codigoLocal int)
	begin 
		insert into CuentasPorCobrar(numeroFactura, anio, mes, valorNetoPago, estadoPago, codigoAdministracion, codigoCliente,
			codigoLocal)
			values (ag_numeroFactura, ag_anio, ag_mes, ag_valorNetoPago, ag_estadoPago, ag_codigoAdministracion, ag_codigoCliente,
				ag_codigoLocal);
	end$$
delimiter ;
call sp_AgregarCuentasPorCobrar('1',2021, 05, 200.00, 'Cancelado', 1,1,1);
call sp_AgregarCuentasPorCobrar('2', 2020, 03, 230.00, 'Cancelado', 1,2,1);
call sp_AgregarCuentasPorCobrar('2', 2020, 03, 230.00, 'Pendiente', 3,2,1);
call sp_AgregarCuentasPorCobrar('2', 2020, 03, 230.00, 'Pendiente', 2,2,1);
call sp_AgregarCuentasPorCobrar('2', 2020, 03, 230.00, 'Pendiente', 3,3,3);
call sp_AgregarCuentasPorCobrar('2', 2020, 03, 230.00, 'Pendiente', 3,1,2);
select * from CuentasPorCobrar;

-- Editar CuentasPorCobrar
delimiter $$
	create procedure sp_EditarCuentasPorCobrar(in ed_codigoCuentasPorCobrar int, in ed_numeroFactura varchar(45), in ed_anio int(4), in ed_mes int(2),
		in ed_valorNetoPago double(11,2), in ed_estadoPago varchar(45))
	begin 
		update CuentasPorCobrar
			set
				numeroFactura = ed_numeroFactura,
                anio = ed_anio,
                mes = ed_mes,
                valorNetoPago = ed_valorNetoPago,
                estadoPago = ed_estadoPago
					where codigoCuentasPorCobrar = ed_codigoCuentasPorCobrar;
    end$$
delimiter ;

-- Eliminar CuentasPorCobrar
delimiter $$
	create procedure sp_EliminarCuentasPorCobrar(in el_codigoCuentasPorCobrar int)
	begin 
		delete from CuentasPorCobrar where codigoCuentasPorCobrar = el_codigoCuentasPorCobrar;
    end$$
delimiter ;

-- Buscar CuentasPorCobrar
delimiter $$
	create procedure sp_BuscarCuentasPorCobrar(in bu_codigoCuentasPorCobrar int)
	begin
		select 
        C.codigoCuentasPorCobrar,
        C.numeroFactura,
        C.anio,
        C.mes,
        C.valorNetoPago,
        C.estadoPago,
        C.codigoAdministracion, 
        C.codigoCliente,
        C.codigoLocal
			from CuentasPorCobrar C
				where codigoCuentasPorCobrar = bu_codigoCuentasPorCobrar;
	end$$
delimiter ;

-- Listar CuentasPorCobrar
delimiter $$
	create procedure sp_ListarCuentasPorCobrar()
	begin
		select 
        C.codigoCuentasPorCobrar,
        C.numeroFactura,
        C.anio,
        C.mes,
        C.valorNetoPago,
        C.estadoPago,
        C.codigoAdministracion, 
        C.codigoCliente,
        C.codigoLocal
			from CuentasPorCobrar C;
	end$$
delimiter ;
select * from CuentasPorCobrar;
-- -------------------------------
-- Procedimiento Cuentas por pagar
-- Agregar CuentasPorPagar

delimiter $$
	create procedure sp_AgregarCuentasPorPagar(in ag_numeroFactura varchar(45), in ag_fechaLimitePago date, in ag_estadoPago varchar (45),
		in ag_valorNetoPago double (10,2), in ag_codigoAdministracion int, in ag_codigoProveedor int)
	begin
		insert into cuentasPorPagar(numeroFactura, fechaLimitePago, estadoPago, valorNetoPago, codigoAdministracion, codigoProveedor)
			values (ag_numeroFactura, ag_fechaLimitePago, ag_estadoPago, ag_valorNetoPago, ag_codigoAdministracion, ag_codigoProveedor);
    end$$
delimiter ;
call sp_AgregarCuentasPorPagar('100','2021-06-06', 'Cancelado', 20.00, 1, 1);

-- Editar CuentasPorPagar

delimiter $$
	create procedure sp_EditarCuentasPorPagar(in ed_codigoCuentasPorPagar int, in ed_numeroFactura varchar(45), in ed_fechaLimitePago date,
		in ed_estadoPago varchar (45), in ed_valorNetoPago double (10,2))
    begin
		update CuentasPorPagar
			set
				numeroFactura = ed_numeroFactura,
				fechaLimitePago= ed_fechaLimitePago,
				estadoPago = ed_estadoPago,
				valorNetoPago = ed_valorNetoPago
					where codigoCuentasPorPagar = ed_codigoCuentasPorPagar;
	end$$
delimiter ;

-- Eliminar CuentasPorPagar

delimiter $$
	create procedure sp_EliminarCuentasPorPagar(in el_codigoCuentasPorPagar int)
    begin
		delete from CuentasPorPagar where codigoCuentasPorPagar = el_codigoCuentasPorPagar;
	end$$
delimiter ;

-- Buscar CuentasPorPagar

delimiter $$
	create procedure sp_BuscarCuentasPorPagar(in bu_codigoCuentasPorPagar int)
	begin 
		select 
			C.codigoCuentasPorPagar,
            C.numeroFactura,
            C.fechaLimitePago,
            C.estadoPago,
            C.valorNetoPago,
            C.codigoAdministracion,
            C.codigoProveedor
				from CuentasPorPagar C
					where codigoCuentasPorPagar = bu_codigoCuentasPorPagar;
	end$$
delimiter ;

-- Listar CuentasPorPagar

delimiter $$
	create procedure sp_ListarCuentasPorPagar()
	begin
		select 
			C.codigoCuentasPorPagar,
            C.numeroFactura,
            C.fechaLimitePago,
            C.estadoPago,
            C.valorNetoPago,
            C.codigoAdministracion,
            C.codigoProveedor
				from CuentasPorPagar C;
	end$$
delimiter ;

call sp_ListarCuentasPorPagar();

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password By '123456';

call sp_ListarTipoCliente;
call sp_ListarClientes;

select * from TipoCliente TC inner join Clientes C on
	TC.codigoTipoCliente = C.codigoTipoCliente
		where TC.descripcion = 'Nuevo';


create table Usuarios(
	codigoUsuario int not null auto_increment,
    nombreUsuario varchar(100) not null,
    apellidoUsuario varchar(100) not null,
    usuarioLogin varchar(50) not null,
    contrasena varchar(50) not null,
    primary key Pk_codigoUsuario(codigoUsuario)
);

Delimiter $$
	Create procedure sp_AgregarUsuario(in nombreUsuario varchar(100),in apellidoUsuario varchar(100), in usuarioLogin varchar(50),
    in contrasena varchar (50))
	Begin 
		Insert into Usuarios(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena)
			values (nombreUsuario, apellidoUsuario, usuarioLogin, contrasena);
    
    End$$
Delimiter ;


Delimiter $$

Create procedure sp_ListarUsuarios()
	begin
		select
			U.codigoUsuario,
			U.nombreUsuario,
			U.apellidoUsuario,
            U.usuarioLogin,
            U.contrasena
            from Usuarios U;
    
    End$$
Delimiter ;
call sp_ListarUsuarios();
call sp_AgregarUsuario('Edgar', 'Sinay', 'esinay', '123456');
select * from Usuarios;

create table Login(
	usuarioMaster varchar(50) not null,
    passwordLogin varchar(50) not null,
    primary key PK_usuarioMaster (usuarioMaster)

);

-- 

select * from Departamentos D Inner join Empleados E
	on D.codigoDepartamento = E.codigoDepartamento where E.codigoEmpleado = 1;
    
    
    select * from Empleados E inner join Cargos C on E.codigoCargo = C.codigoCargo 
							inner join Departamentos DP on E.codigoDepartamento = DP.codigoDepartamento 
                            inner join Horarios H on E.codigoHorario = H.codigoHorario where codigoEmpleado = 1;
                            
                            
select *  from Clientes C
	inner join TipoCliente T inner join Locales L inner join CuentasPorCobrar CP
			on C.codigoTipoCliente = T.codigoTipoCliente and C.codigoLocal = L.codigoLocal and C.codigoCliente = CP.codigoCliente 
				where C.codigoCliente = 1;