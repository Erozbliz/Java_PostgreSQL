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
	id_sunburst SERIAL NOT NULL ,
	title       CHAR (50)   ,
	CONSTRAINT prk_constraint_sunburst PRIMARY KEY (id_sunburst)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: node_close_loop
------------------------------------------------------------
CREATE TABLE public.node_close_loop(
	id_node     SERIAL NOT NULL ,
	name        CHAR (50)   ,
	tag         CHAR (50)   ,
	size        INT   ,
	id_sunburst INT  NOT NULL ,
	CONSTRAINT prk_constraint_node_close_loop PRIMARY KEY (id_node,id_sunburst)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: user_close_loop
------------------------------------------------------------
CREATE TABLE public.user_close_loop(
	id_user  SERIAL NOT NULL ,
	login    CHAR (50)  NOT NULL UNIQUE,
	password CHAR (50)   ,
	CONSTRAINT prk_constraint_user_close_loop PRIMARY KEY (id_user)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: sunburst_close_loop
------------------------------------------------------------
CREATE TABLE public.sunburst_close_loop(
	id_sunburst SERIAL NOT NULL ,
	title       CHAR (50)   ,
	CONSTRAINT prk_constraint_sunburst_close_loop PRIMARY KEY (id_sunburst)
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
-- Table: POSSEDE
------------------------------------------------------------
CREATE TABLE public.POSSEDE(
	id_sunburst INT  NOT NULL ,
	id_node     INT  NOT NULL ,
	CONSTRAINT prk_constraint_POSSEDE PRIMARY KEY (id_sunburst,id_node)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: COMPOSITION
------------------------------------------------------------
CREATE TABLE public.COMPOSITION(
	id_node                         INT  NOT NULL ,
	id_sunburst                     INT  NOT NULL ,
	id_node_node_close_loop         INT  NOT NULL ,
	id_sunburst_sunburst_close_loop INT  NOT NULL ,
	CONSTRAINT prk_constraint_COMPOSITION PRIMARY KEY (id_node,id_sunburst,id_node_node_close_loop,id_sunburst_sunburst_close_loop)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: ACCEDER_CLOSE_LOOP
------------------------------------------------------------
CREATE TABLE public.ACCEDER_CLOSE_LOOP(
	id_user     INT  NOT NULL ,
	id_sunburst INT  NOT NULL ,
	CONSTRAINT prk_constraint_ACCEDER_CLOSE_LOOP PRIMARY KEY (id_user,id_sunburst)
)WITHOUT OIDS;



ALTER TABLE public.node_close_loop ADD CONSTRAINT FK_node_close_loop_id_sunburst FOREIGN KEY (id_sunburst) REFERENCES public.sunburst_close_loop(id_sunburst);
ALTER TABLE public.ACCEDER ADD CONSTRAINT FK_ACCEDER_id_user FOREIGN KEY (id_user) REFERENCES public.user(id_user);
ALTER TABLE public.ACCEDER ADD CONSTRAINT FK_ACCEDER_id_sunburst FOREIGN KEY (id_sunburst) REFERENCES public.sunburst(id_sunburst);
ALTER TABLE public.POSSEDE ADD CONSTRAINT FK_POSSEDE_id_sunburst FOREIGN KEY (id_sunburst) REFERENCES public.sunburst(id_sunburst);
ALTER TABLE public.POSSEDE ADD CONSTRAINT FK_POSSEDE_id_node FOREIGN KEY (id_node) REFERENCES public.node(id_node);
ALTER TABLE public.COMPOSITION ADD CONSTRAINT FK_COMPOSITION_id_node FOREIGN KEY (id_node) REFERENCES public.node_close_loop(id_node);
ALTER TABLE public.COMPOSITION ADD CONSTRAINT FK_COMPOSITION_id_sunburst FOREIGN KEY (id_sunburst) REFERENCES public.sunburst_close_loop(id_sunburst);
ALTER TABLE public.COMPOSITION ADD CONSTRAINT FK_COMPOSITION_id_node_node_close_loop FOREIGN KEY (id_node_node_close_loop) REFERENCES public.node_close_loop(id_node);
ALTER TABLE public.COMPOSITION ADD CONSTRAINT FK_COMPOSITION_id_sunburst_sunburst_close_loop FOREIGN KEY (id_sunburst_sunburst_close_loop) REFERENCES public.sunburst_close_loop(id_sunburst);
ALTER TABLE public.ACCEDER_CLOSE_LOOP ADD CONSTRAINT FK_ACCEDER_CLOSE_LOOP_id_user FOREIGN KEY (id_user) REFERENCES public.user_close_loop(id_user);
ALTER TABLE public.ACCEDER_CLOSE_LOOP ADD CONSTRAINT FK_ACCEDER_CLOSE_LOOP_id_sunburst FOREIGN KEY (id_sunburst) REFERENCES public.sunburst_close_loop(id_sunburst);
