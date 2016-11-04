--Root
INSERT INTO public.root(id_root, title) VALUES (1, 'UdeS');
-- Création des noeud
INSERT INTO public.node(id_node, name, tag, size, id_root) VALUES (1, 'GEI771', 'security', 50, 1);
INSERT INTO public.node(id_node, name, tag, size, id_root) VALUES (2, 'GEI772', 'security', 50, 1);
INSERT INTO public.node(id_node, name, tag, size, id_root) VALUES (3, 'GEI773', 'security', 50, 1);

--Création des sous noeud
INSERT INTO public.node(id_node, name, tag, size, id_root) VALUES (4, 'APP1 771', 'security 771', 50, 1);
INSERT INTO public.node(id_node, name, tag, size, id_root) VALUES (5, 'APP2 771', 'security 771', 50, 1);
INSERT INTO public.node(id_node, name, tag, size, id_root) VALUES (6, 'APP3 771', 'security 771', 50, 1);

INSERT INTO public.node(id_node, name, tag, size, id_root) VALUES (7, 'APP1 772', 'security 772', 50, 1);
INSERT INTO public.node(id_node, name, tag, size, id_root) VALUES (8, 'APP2 772', 'security 772', 50, 1);
INSERT INTO public.node(id_node, name, tag, size, id_root) VALUES (9, 'APP3 772', 'security 772', 50, 1);

INSERT INTO public.node(id_node, name, tag, size, id_root) VALUES (10, 'APP1 773', 'security 773', 50, 1);
INSERT INTO public.node(id_node, name, tag, size, id_root) VALUES (11, 'APP2 773', 'security 773', 50, 1);
INSERT INTO public.node(id_node, name, tag, size, id_root) VALUES (12, 'APP3 773', 'security 773', 50, 1);

--Création des liaison
INSERT INTO public.composition(niveau_un, id_node, id_root, id_node_1, id_root_2) VALUES (TRUE, 1, 1, 1, 1);
INSERT INTO public.composition(niveau_un, id_node, id_root, id_node_1, id_root_2) VALUES (TRUE, 1, 1, 2, 1);
INSERT INTO public.composition(niveau_un, id_node, id_root, id_node_1, id_root_2) VALUES (TRUE, 1, 1, 3, 1);
INSERT INTO public.composition(niveau_un, id_node, id_root, id_node_1, id_root_2) VALUES (FALSE, 1, 1, 4, 1);
INSERT INTO public.composition(niveau_un, id_node, id_root, id_node_1, id_root_2) VALUES (FALSE, 1, 1, 5, 1);
INSERT INTO public.composition(niveau_un, id_node, id_root, id_node_1, id_root_2) VALUES (FALSE, 1, 1, 6, 1);
INSERT INTO public.composition(niveau_un, id_node, id_root, id_node_1, id_root_2) VALUES (FALSE, 2, 1, 7, 1);
INSERT INTO public.composition(niveau_un, id_node, id_root, id_node_1, id_root_2) VALUES (FALSE, 2, 1, 8, 1);
INSERT INTO public.composition(niveau_un, id_node, id_root, id_node_1, id_root_2) VALUES (FALSE, 2, 1, 9, 1);
INSERT INTO public.composition(niveau_un, id_node, id_root, id_node_1, id_root_2) VALUES (FALSE, 3, 1, 10, 1);
INSERT INTO public.composition(niveau_un, id_node, id_root, id_node_1, id_root_2) VALUES (FALSE, 3, 1, 11, 1);
INSERT INTO public.composition(niveau_un, id_node, id_root, id_node_1, id_root_2) VALUES (FALSE, 3, 1, 12, 1);