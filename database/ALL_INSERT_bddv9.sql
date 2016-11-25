--Sunburst
INSERT INTO public.sunburst(id_root, title) VALUES (1, 'UdeS');
-- Création des noeud
INSERT INTO public.node(id_node, name, tag, size) VALUES (0, 'UdeS root', 'root', 50);
INSERT INTO public.node(id_node, name, tag, size) VALUES (1, 'GEI771', 'security', 50);
INSERT INTO public.node(id_node, name, tag, size) VALUES (2, 'GEI772', 'security', 50);
INSERT INTO public.node(id_node, name, tag, size) VALUES (3, 'GEI773', 'security', 50);

--Création des sous noeud
INSERT INTO public.node(id_node, name, tag, size) VALUES (4, 'APP1 771', 'security 771', 50);
INSERT INTO public.node(id_node, name, tag, size) VALUES (5, 'APP2 771', 'security 771', 50);
INSERT INTO public.node(id_node, name, tag, size) VALUES (6, 'APP3 771', 'security 771', 50);

INSERT INTO public.node(id_node, name, tag, size) VALUES (7, 'APP1 772', 'security 772', 50);
INSERT INTO public.node(id_node, name, tag, size) VALUES (8, 'APP2 772', 'security 772', 50);
INSERT INTO public.node(id_node, name, tag, size) VALUES (9, 'APP3 772', 'security 772', 50);

INSERT INTO public.node(id_node, name, tag, size) VALUES (10, 'APP1 773', 'security 773', 50);
INSERT INTO public.node(id_node, name, tag, size) VALUES (11, 'APP2 773', 'security 773', 50);
INSERT INTO public.node(id_node, name, tag, size) VALUES (12, 'APP3 773', 'security 773', 50);

--Création des liaison entre les nodes (parent,enfant)
INSERT INTO public.composition(id_node, id_node_1) VALUES (0, 1);
INSERT INTO public.composition(id_node, id_node_1) VALUES (0, 2);
INSERT INTO public.composition(id_node, id_node_1) VALUES (0, 3);
INSERT INTO public.composition(id_node, id_node_1) VALUES (1, 4);
INSERT INTO public.composition(id_node, id_node_1) VALUES (1, 5);
INSERT INTO public.composition(id_node, id_node_1) VALUES (1, 6);
INSERT INTO public.composition(id_node, id_node_1) VALUES (2, 7);
INSERT INTO public.composition(id_node, id_node_1) VALUES (2, 8);
INSERT INTO public.composition(id_node, id_node_1) VALUES (2, 9);
INSERT INTO public.composition(id_node, id_node_1) VALUES (3, 10);
INSERT INTO public.composition(id_node, id_node_1) VALUES (3, 11);
INSERT INTO public.composition(id_node, id_node_1) VALUES (3, 12);


--Création des liaison entre les nodes et sunburst
INSERT INTO public.appartient(id_root, id_node) VALUES (1, 1);
INSERT INTO public.appartient(id_root, id_node) VALUES (1, 2);
INSERT INTO public.appartient(id_root, id_node) VALUES (1, 3);





--AUTRES POUR TEST
INSERT INTO public.places(place, is_in) VALUES (1000, 300);
INSERT INTO public.places(place, is_in) VALUES (300, 90);
INSERT INTO public.places(place, is_in) VALUES (200, 90);
INSERT INTO public.places(place, is_in) VALUES (100, 90);
INSERT INTO public.places(place, is_in) VALUES (90, 1);
INSERT INTO public.places(place, is_in) VALUES (80, 1);
INSERT INTO public.places(place, is_in) VALUES (70, 1);
INSERT INTO public.places(place, is_in) VALUES (60, 2);
INSERT INTO public.places(place, is_in) VALUES (50, 2);
INSERT INTO public.places(place, is_in) VALUES (40, 2);
INSERT INTO public.places(place, is_in) VALUES (30, 2);

--http://www.openscg.com/2014/08/hierarchical-queries-using-postgresql/
--http://bender.io/2013/09/22/returning-hierarchical-data-in-a-single-sql-query/

--Récursive 1
WITH RECURSIVE q AS (SELECT place, is_in
                       FROM places
                      WHERE place = 90
                      UNION ALL
                     SELECT m.place, m.is_in
                       FROM places m
                       JOIN q ON q.place = m.is_in)
        SELECT place, is_in FROM q




--Récursive 2
WITH RECURSIVE q AS (SELECT place, is_in
                                 FROM places
                                WHERE place = 90
                                UNION
                               SELECT m.place, m.is_in
                                 FROM places m
                                 JOIN q ON q.place = m.is_in)
                  SELECT place, is_in FROM q;




INSERT INTO public.node(id_node, name, tag, size) VALUES (10, 'v1', 'security 771', 50);
INSERT INTO public.node(id_node, name, tag, size) VALUES (20, 'v2', 'security 771', 50);
INSERT INTO public.node(id_node, name, tag, size) VALUES (30, 'v3', 'security 771', 50);
INSERT INTO public.node(id_node, name, tag, size) VALUES (100, 'part 1', 'security 771', 50);

INSERT INTO public.composition(id_node, id_node_1) VALUES (4, 10);
INSERT INTO public.composition(id_node, id_node_1) VALUES (4, 20);
INSERT INTO public.composition(id_node, id_node_1) VALUES (4, 30);
INSERT INTO public.composition(id_node, id_node_1) VALUES (20, 100);



WITH RECURSIVE q AS (SELECT id_node_1, id_node
                                 FROM composition
                                WHERE id_node_1 = 4
                                UNION
                               SELECT m.id_node_1, m.id_node
                                 FROM composition m
                                 JOIN q ON q.id_node_1 = m.id_node)
                  SELECT id_node_1, id_node FROM q;



--Recursive query (introduced in 8.4 returns fully qualified name)
WITH RECURSIVE composition AS
(SELECT id_node_1, id_node, CAST(id_node As varchar(1000)) As id_node_fullname
FROM composition
WHERE id_node_1 = 4
UNION ALL
SELECT si.id_node_1,si.id_node,
	CAST(sp.id_node_fullname || '->' || si.id_node As varchar(1000)) As si_item_fullname
FROM composition As si
	INNER JOIN composition AS sp
	ON (si.id_node = sp.id_node_1)
)
SELECT id_node_1, si_item_fullname
FROM composition
ORDER BY id_node_1;

