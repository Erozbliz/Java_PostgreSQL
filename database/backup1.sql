--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.1
-- Dumped by pg_dump version 9.6.1

-- Started on 2016-11-02 20:30:41

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2132 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 186 (class 1259 OID 16402)
-- Name: opus_children; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE opus_children (
    id integer,
    name text
);


ALTER TABLE opus_children OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 16394)
-- Name: opus_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE opus_user (
    id integer NOT NULL,
    name character varying(300)[]
);


ALTER TABLE opus_user OWNER TO postgres;

--
-- TOC entry 2125 (class 0 OID 16402)
-- Dependencies: 186
-- Data for Name: opus_children; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY opus_children (id, name) FROM stdin;
\N	\N
4	\N
102	zzzzz
108	zzzzz
108	zzzzz
108	zzzzz
108	zzzzz
108	zrrzz
\.


--
-- TOC entry 2124 (class 0 OID 16394)
-- Dependencies: 185
-- Data for Name: opus_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY opus_user (id, name) FROM stdin;
\.


--
-- TOC entry 2006 (class 2606 OID 16401)
-- Name: opus_user opus_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY opus_user
    ADD CONSTRAINT opus_user_pkey PRIMARY KEY (id);


-- Completed on 2016-11-02 20:30:41

--
-- PostgreSQL database dump complete
--

