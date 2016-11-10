------------------------------------------------------------
--        Script Postgre 
------------------------------------------------------------



------------------------------------------------------------
-- Table: node
------------------------------------------------------------
CREATE TABLE public.node(
	id_node SERIAL NOT NULL ,
	name    CHAR (50)   ,
	tag     CHAR (50)   ,
	size    INT   ,
	CONSTRAINT prk_constraint_node PRIMARY KEY (id_node)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: user
------------------------------------------------------------
CREATE TABLE public.user(
	id_user  SERIAL NOT NULL ,
	login    CHAR (50)  NOT NULL UNIQUE,
	password CHAR (50)   ,
	CONSTRAINT prk_constraint_user PRIMARY KEY (id_user)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: sunburst
------------------------------------------------------------
CREATE TABLE public.sunburst(
	id_root SERIAL NOT NULL ,
	title   CHAR (50)   ,
	CONSTRAINT prk_constraint_sunburst PRIMARY KEY (id_root)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: ACCEDER
------------------------------------------------------------
CREATE TABLE public.ACCEDER(
	id_user INT  NOT NULL ,
	id_root INT  NOT NULL ,
	CONSTRAINT prk_constraint_ACCEDER PRIMARY KEY (id_user,id_root)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: COMPOSITION
------------------------------------------------------------
CREATE TABLE public.COMPOSITION(
	id_node   INT  NOT NULL ,
	id_node_1 INT  NOT NULL ,
	CONSTRAINT prk_constraint_COMPOSITION PRIMARY KEY (id_node,id_node_1)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: APPARTIENT
------------------------------------------------------------
CREATE TABLE public.APPARTIENT(
	id_root INT  NOT NULL ,
	id_node INT  NOT NULL ,
	CONSTRAINT prk_constraint_APPARTIENT PRIMARY KEY (id_root,id_node)
)WITHOUT OIDS;



ALTER TABLE public.ACCEDER ADD CONSTRAINT FK_ACCEDER_id_user FOREIGN KEY (id_user) REFERENCES public.user(id_user);
ALTER TABLE public.ACCEDER ADD CONSTRAINT FK_ACCEDER_id_root FOREIGN KEY (id_root) REFERENCES public.sunburst(id_root);
ALTER TABLE public.COMPOSITION ADD CONSTRAINT FK_COMPOSITION_id_node FOREIGN KEY (id_node) REFERENCES public.node(id_node);
ALTER TABLE public.COMPOSITION ADD CONSTRAINT FK_COMPOSITION_id_node_1 FOREIGN KEY (id_node_1) REFERENCES public.node(id_node);
ALTER TABLE public.APPARTIENT ADD CONSTRAINT FK_APPARTIENT_id_root FOREIGN KEY (id_root) REFERENCES public.sunburst(id_root);
ALTER TABLE public.APPARTIENT ADD CONSTRAINT FK_APPARTIENT_id_node FOREIGN KEY (id_node) REFERENCES public.node(id_node);
