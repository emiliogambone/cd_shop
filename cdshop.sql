--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.7
-- Dumped by pg_dump version 9.5.7

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: CD; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "CD" (
    title text NOT NULL,
    price double precision NOT NULL,
    date date NOT NULL,
    band text NOT NULL,
    description text NOT NULL,
    genre text NOT NULL,
    "numberSongs" integer DEFAULT 1 NOT NULL,
    cd_id integer NOT NULL,
    "titleSongs" text NOT NULL,
    photo text,
    musicians integer NOT NULL
);


ALTER TABLE "CD" OWNER TO postgres;

--
-- Name: CD_cd_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "CD_cd_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "CD_cd_id_seq" OWNER TO postgres;

--
-- Name: CD_cd_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "CD_cd_id_seq" OWNED BY "CD".cd_id;


--
-- Name: Musicista; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "Musicista" (
    nome text NOT NULL,
    genere text NOT NULL,
    anno_nascita integer,
    musicita_id integer NOT NULL,
    strumenti text NOT NULL
);


ALTER TABLE "Musicista" OWNER TO postgres;

--
-- Name: User; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "User" (
    "CF" character(16) NOT NULL,
    username text NOT NULL,
    password text NOT NULL,
    nome text NOT NULL,
    cognome text NOT NULL,
    "città" text NOT NULL,
    telefono character(10) NOT NULL,
    cellulare character(10)
);


ALTER TABLE "User" OWNER TO postgres;

--
-- Name: Vendita; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "Vendita" (
    prezzo_tot double precision NOT NULL,
    data_ora timestamp with time zone DEFAULT now() NOT NULL,
    mod_pagamento text NOT NULL,
    mod_consegna text NOT NULL,
    ip text NOT NULL,
    cliente text NOT NULL,
    vendita_id integer NOT NULL,
    lista_prodotti text NOT NULL
);


ALTER TABLE "Vendita" OWNER TO postgres;

--
-- Name: Vendita_vendita_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Vendita_vendita_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Vendita_vendita_id_seq" OWNER TO postgres;

--
-- Name: Vendita_vendita_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Vendita_vendita_id_seq" OWNED BY "Vendita".vendita_id;


--
-- Name: cd_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "CD" ALTER COLUMN cd_id SET DEFAULT nextval('"CD_cd_id_seq"'::regclass);


--
-- Name: vendita_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Vendita" ALTER COLUMN vendita_id SET DEFAULT nextval('"Vendita_vendita_id_seq"'::regclass);


--
-- Data for Name: CD; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "CD" (title, price, date, band, description, genre, "numberSongs", cd_id, "titleSongs", photo, musicians) FROM stdin;
Rivers	19.9899999999999984	2015-01-01	Mods	bla bla bla...	Rock	5	1	Blues, Mans, Over the Mountains	\N	1
Jack and the Ripper	10.9900000000000002	2014-05-01	Molly Backs	bla bla bla ...	Funk	6	2	Sky, None, Mark	\N	2
\.


--
-- Name: CD_cd_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"CD_cd_id_seq"', 5, true);


--
-- Data for Name: Musicista; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "Musicista" (nome, genere, anno_nascita, musicita_id, strumenti) FROM stdin;
Ricky Morse	Rock	1989	1	chitarra
Jhon Mayer	Blues	1954	2	batteria
Jhosep Muls	Indie	1949	3	Sax
Mill Ritcher	Folk	1969	4	Chitarra
\.


--
-- Data for Name: User; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "User" ("CF", username, password, nome, cognome, "città", telefono, cellulare) FROM stdin;
MTTRFD78D45E153P	giuly	lldpc5	giulia	brunelli	padova	0470512114	3259874111
ALBGZZ87S45X123S	albi	5555	alberto	guzzi	Verona	0441254778	\N
GFDERT45A78Q456P	thomas	1111	Thomas	Berti	Verona	0448975612	\N
\.


--
-- Data for Name: Vendita; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "Vendita" (prezzo_tot, data_ora, mod_pagamento, mod_consegna, ip, cliente, vendita_id, lista_prodotti) FROM stdin;
19.9899999999999984	2017-06-03 16:02:35.329708+02	Bancomat	Courier	190.53.68.1	albi	8	, 1
30.9799999999999969	2017-06-03 16:04:36.056171+02	Paypal	Priority mail	190.53.68.1	albi	9	1,2
30.9799999999999969	2017-06-04 10:51:28.736269+02	Bancomat	Courier	190.53.68.1	albi	10	1,2
30.9799999999999969	2017-06-06 09:49:50.287749+02	Bancomat	Priority mail	190.53.68.1	albi	11	1,2
\.


--
-- Name: Vendita_vendita_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Vendita_vendita_id_seq"', 11, true);


--
-- Name: CD_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "CD"
    ADD CONSTRAINT "CD_pkey" PRIMARY KEY (cd_id);


--
-- Name: Musicista_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Musicista"
    ADD CONSTRAINT "Musicista_pkey" PRIMARY KEY (musicita_id);


--
-- Name: User_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "User"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (username);


--
-- Name: Vendita_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Vendita"
    ADD CONSTRAINT "Vendita_pkey" PRIMARY KEY (vendita_id);


--
-- Name: CD_musicisti_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "CD"
    ADD CONSTRAINT "CD_musicisti_fkey" FOREIGN KEY (musicians) REFERENCES "Musicista"(musicita_id);


--
-- Name: Vendita_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Vendita"
    ADD CONSTRAINT "Vendita_cliente_fkey" FOREIGN KEY (cliente) REFERENCES "User"(username);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

