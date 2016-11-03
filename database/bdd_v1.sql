------------------------------------------------------------
--        Script Postgre 
------------------------------------------------------------



------------------------------------------------------------
-- Table: node
------------------------------------------------------------
CREATE TABLE public.node(
	id_node     SERIAL NOT NULL ,
	name        CHAR (50)   ,
	tag         CHAR (50)   ,
	id_sunburst INT   ,
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
	id_sunburst SERIAL NOT NULL ,
	title       CHAR (50)   ,
	id_user     INT   ,
	CONSTRAINT prk_constraint_sunburst PRIMARY KEY (id_sunburst)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: son
------------------------------------------------------------
CREATE TABLE public.son(
	id_son  SERIAL NOT NULL ,
	size    INT   ,
	id_node INT   ,
	CONSTRAINT prk_constraint_son PRIMARY KEY (id_son)
)WITHOUT OIDS;



ALTER TABLE public.node ADD CONSTRAINT FK_node_id_sunburst FOREIGN KEY (id_sunburst) REFERENCES public.sunburst(id_sunburst);
ALTER TABLE public.sunburst ADD CONSTRAINT FK_sunburst_id_user FOREIGN KEY (id_user) REFERENCES public.user(id_user);
ALTER TABLE public.son ADD CONSTRAINT FK_son_id_node FOREIGN KEY (id_node) REFERENCES public.node(id_node);
