--Sunburst
INSERT INTO public.sunburst(id_root, title) VALUES (1, 'UdeS');
-- Création des noeud
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