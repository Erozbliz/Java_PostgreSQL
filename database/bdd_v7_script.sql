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
	size        INT   ,
	id_sunburst INT  NOT NULL ,
	CONSTRAINT prk_constraint_node PRIMARY KEY (id_node,id_sunburst)
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
-- Table: root
------------------------------------------------------------
CREATE TABLE public.root(
	id_sunburst SERIAL NOT NULL ,
	title       CHAR (50)   ,
	CONSTRAINT prk_constraint_root PRIMARY KEY (id_sunburst)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: ACCEDER
------------------------------------------------------------
CREATE TABLE public.ACCEDER(
	id_user     INT  NOT NULL ,
	id_sunburst INT  NOT NULL ,
	CONSTRAINT prk_constraint_ACCEDER PRIMARY KEY (id_user,id_sunburst)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: COMPOSITION
------------------------------------------------------------
CREATE TABLE public.COMPOSITION(
	id_node          INT  NOT NULL ,
	id_sunburst      INT  NOT NULL ,
	id_node_1        INT  NOT NULL ,
	id_sunburst_root INT  NOT NULL ,
	CONSTRAINT prk_constraint_COMPOSITION PRIMARY KEY (id_node,id_sunburst,id_node_1,id_sunburst_root)
)WITHOUT OIDS;



ALTER TABLE public.node ADD CONSTRAINT FK_node_id_sunburst FOREIGN KEY (id_sunburst) REFERENCES public.root(id_sunburst);
ALTER TABLE public.ACCEDER ADD CONSTRAINT FK_ACCEDER_id_user FOREIGN KEY (id_user) REFERENCES public.user(id_user);
ALTER TABLE public.ACCEDER ADD CONSTRAINT FK_ACCEDER_id_sunburst FOREIGN KEY (id_sunburst) REFERENCES public.root(id_sunburst);
ALTER TABLE public.COMPOSITION ADD CONSTRAINT FK_COMPOSITION_id_node FOREIGN KEY (id_node) REFERENCES public.node(id_node);
ALTER TABLE public.COMPOSITION ADD CONSTRAINT FK_COMPOSITION_id_sunburst FOREIGN KEY (id_sunburst) REFERENCES public.root(id_sunburst);
ALTER TABLE public.COMPOSITION ADD CONSTRAINT FK_COMPOSITION_id_node_1 FOREIGN KEY (id_node_1) REFERENCES public.node(id_node);
ALTER TABLE public.COMPOSITION ADD CONSTRAINT FK_COMPOSITION_id_sunburst_root FOREIGN KEY (id_sunburst_root) REFERENCES public.root(id_sunburst);
