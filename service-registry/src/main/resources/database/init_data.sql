--
-- PostgreSQL database dump
--

-- Dumped from database version 11.1
-- Dumped by pg_dump version 11.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: card; Type: TABLE; Schema: public; Owner: david
--

CREATE TABLE public.card (
    id integer NOT NULL,
    title character varying,
    url character varying,
    power integer NOT NULL,
    intelligence integer NOT NULL,
    reflex integer NOT NULL
);


--
-- Name: card_id_seq; Type: SEQUENCE; Schema: public; Owner: david
--

CREATE SEQUENCE public.card_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: card_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: david
--

ALTER SEQUENCE public.card_id_seq OWNED BY public.card.id;


--
-- Name: card id; Type: DEFAULT; Schema: public; Owner: david
--

ALTER TABLE ONLY public.card ALTER COLUMN id SET DEFAULT nextval('public.card_id_seq'::regclass);


--
-- Data for Name: card; Type: TABLE DATA; Schema: public; Owner: david
--

COPY public.card (id, title, url, power, intelligence, reflex) FROM stdin;
1	Master Crok	001	8	7	9
2	Bond Crok	002	5	6	7
3	Devil Crok	003	6	6	6
4	Samurai Crok	004	6	4	8
5	Angel Crok	005	3	4	7
6	Captain Crok	006	5	6	3
7	Executor Crok	007	7	4	3
8	Jungle Crok	008	6	3	5
9	Sensei Crok	009	2	9	3
10	Sumo Crok	010	8	5	1
11	Pancrator Crok	011	9	2	3
12	Army Crok	012	3	4	4
13	Boy Crok	013	1	4	6
14	Cave Crok	014	7	1	3
15	Funny Crok	015	1	6	4
16	Gladiator Crok	016	5	1	5
17	Indian Crok	017	3	5	3
18	Karate Crok	018	2	3	6
19	Police Crok	019	4	3	4
20	Priest Crok	020	2	7	2
21	Sheriff Crok	021	3	3	5
\.


--
-- Name: card_id_seq; Type: SEQUENCE SET; Schema: public; Owner: david
--

SELECT pg_catalog.setval('public.card_id_seq', 22, true);


--
-- Name: card card_pk; Type: CONSTRAINT; Schema: public; Owner: david
--

ALTER TABLE ONLY public.card
    ADD CONSTRAINT card_pk PRIMARY KEY (id);


--
-- Name: card_id_uindex; Type: INDEX; Schema: public; Owner: david
--

CREATE UNIQUE INDEX card_id_uindex ON public.card USING btree (id);


--
-- PostgreSQL database dump complete
--
