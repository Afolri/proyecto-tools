--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

-- Started on 2025-05-22 12:09:28

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 24604)
-- Name: soporte; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA soporte;


ALTER SCHEMA soporte OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 225 (class 1259 OID 16570)
-- Name: agentes; Type: TABLE; Schema: soporte; Owner: postgres
--

CREATE TABLE soporte.agentes (
    numero_agente bigint NOT NULL,
    nombres_agente character varying(255) NOT NULL,
    apellidos_agente character varying(255),
    numero_usuario bigint
);


ALTER TABLE soporte.agentes OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16569)
-- Name: Agente_Numero_Agente_seq; Type: SEQUENCE; Schema: soporte; Owner: postgres
--

ALTER TABLE soporte.agentes ALTER COLUMN numero_agente ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME soporte."Agente_Numero_Agente_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 227 (class 1259 OID 16578)
-- Name: clientes; Type: TABLE; Schema: soporte; Owner: postgres
--

CREATE TABLE soporte.clientes (
    numero_cliente bigint NOT NULL,
    nombre_cliente character varying(255),
    correo character varying(255),
    telefono character varying(255)
);


ALTER TABLE soporte.clientes OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 16577)
-- Name: Cliente_no_cliente_seq; Type: SEQUENCE; Schema: soporte; Owner: postgres
--

ALTER TABLE soporte.clientes ALTER COLUMN numero_cliente ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME soporte."Cliente_no_cliente_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 229 (class 1259 OID 16586)
-- Name: tickets; Type: TABLE; Schema: soporte; Owner: postgres
--

CREATE TABLE soporte.tickets (
    numero_ticket bigint NOT NULL,
    asunto character varying(255),
    descripcion character varying(255),
    numero_agente bigint,
    numero_cliente bigint,
    numero_producto bigint,
    fecha date,
    estado character varying(255),
    hora time(6) without time zone
);


ALTER TABLE soporte.tickets OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 16585)
-- Name: Ticket_numero_ticket_seq; Type: SEQUENCE; Schema: soporte; Owner: postgres
--

ALTER TABLE soporte.tickets ALTER COLUMN numero_ticket ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME soporte."Ticket_numero_ticket_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 254 (class 1259 OID 32998)
-- Name: comentario_ticket; Type: TABLE; Schema: soporte; Owner: postgres
--

CREATE TABLE soporte.comentario_ticket (
    numero_comentario_ticket bigint NOT NULL,
    numero_comentario bigint NOT NULL,
    numero_ticket bigint NOT NULL,
    numero_usuario bigint NOT NULL
);


ALTER TABLE soporte.comentario_ticket OWNER TO postgres;

--
-- TOC entry 256 (class 1259 OID 33054)
-- Name: comentario_ticket_numero_comentario_ticket_seq; Type: SEQUENCE; Schema: soporte; Owner: postgres
--

ALTER TABLE soporte.comentario_ticket ALTER COLUMN numero_comentario_ticket ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME soporte.comentario_ticket_numero_comentario_ticket_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 251 (class 1259 OID 32916)
-- Name: comentarios; Type: TABLE; Schema: soporte; Owner: postgres
--

CREATE TABLE soporte.comentarios (
    numero_comentario bigint NOT NULL,
    contenido character varying(255)
);


ALTER TABLE soporte.comentarios OWNER TO postgres;

--
-- TOC entry 255 (class 1259 OID 33053)
-- Name: comentarios_numero_comentario_seq; Type: SEQUENCE; Schema: soporte; Owner: postgres
--

ALTER TABLE soporte.comentarios ALTER COLUMN numero_comentario ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME soporte.comentarios_numero_comentario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 243 (class 1259 OID 24606)
-- Name: notificaciones; Type: TABLE; Schema: soporte; Owner: postgres
--

CREATE TABLE soporte.notificaciones (
    numero_notificacion bigint NOT NULL,
    fecha date,
    hora time(6) without time zone,
    mensaje character varying(255)
);


ALTER TABLE soporte.notificaciones OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 24605)
-- Name: notificacion_numero_notificacion_seq; Type: SEQUENCE; Schema: soporte; Owner: postgres
--

ALTER TABLE soporte.notificaciones ALTER COLUMN numero_notificacion ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME soporte.notificacion_numero_notificacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 245 (class 1259 OID 24619)
-- Name: producto_tipo; Type: TABLE; Schema: soporte; Owner: postgres
--

CREATE TABLE soporte.producto_tipo (
    numero_producto_tipo bigint NOT NULL,
    codigo character varying(255),
    numero_producto bigint,
    numero_identificador bigint
);


ALTER TABLE soporte.producto_tipo OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 24618)
-- Name: producto_tipo_numero_producto_tipo_seq; Type: SEQUENCE; Schema: soporte; Owner: postgres
--

ALTER TABLE soporte.producto_tipo ALTER COLUMN numero_producto_tipo ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME soporte.producto_tipo_numero_producto_tipo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 230 (class 1259 OID 16603)
-- Name: productoticket; Type: TABLE; Schema: soporte; Owner: postgres
--

CREATE TABLE soporte.productoticket (
    numero_producto bigint NOT NULL,
    numero_compra_cot character varying(255)
);


ALTER TABLE soporte.productoticket OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 16660)
-- Name: productoticket_numero_producto_seq; Type: SEQUENCE; Schema: soporte; Owner: postgres
--

ALTER TABLE soporte.productoticket ALTER COLUMN numero_producto ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME soporte.productoticket_numero_producto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 246 (class 1259 OID 24626)
-- Name: tipo_identificador; Type: TABLE; Schema: soporte; Owner: postgres
--

CREATE TABLE soporte.tipo_identificador (
    numero_identificador bigint NOT NULL,
    nombre_identificador character varying(255)
);


ALTER TABLE soporte.tipo_identificador OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 24643)
-- Name: tipo_identificador_numero_identificador_seq; Type: SEQUENCE; Schema: soporte; Owner: postgres
--

ALTER TABLE soporte.tipo_identificador ALTER COLUMN numero_identificador ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME soporte.tipo_identificador_numero_identificador_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 249 (class 1259 OID 32885)
-- Name: usuario; Type: TABLE; Schema: soporte; Owner: postgres
--

CREATE TABLE soporte.usuario (
    numero_usuario bigint NOT NULL,
    correo character varying(255) NOT NULL,
    nombre_usuario character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    rol smallint,
    numero_agente bigint
);


ALTER TABLE soporte.usuario OWNER TO postgres;

--
-- TOC entry 257 (class 1259 OID 33116)
-- Name: usuario_notificacion; Type: TABLE; Schema: soporte; Owner: postgres
--

CREATE TABLE soporte.usuario_notificacion (
    numero_usuario_notificacion bigint NOT NULL,
    visto boolean,
    numero_usuario bigint NOT NULL,
    numero_notificacion bigint NOT NULL
);


ALTER TABLE soporte.usuario_notificacion OWNER TO postgres;

--
-- TOC entry 258 (class 1259 OID 33140)
-- Name: usuario_notificacion_numero_usuario_notificacion_seq; Type: SEQUENCE; Schema: soporte; Owner: postgres
--

ALTER TABLE soporte.usuario_notificacion ALTER COLUMN numero_usuario_notificacion ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME soporte.usuario_notificacion_numero_usuario_notificacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 250 (class 1259 OID 32897)
-- Name: usuario_numero_usuario_seq; Type: SEQUENCE; Schema: soporte; Owner: postgres
--

ALTER TABLE soporte.usuario ALTER COLUMN numero_usuario ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME soporte.usuario_numero_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 4968 (class 0 OID 16570)
-- Dependencies: 225
-- Data for Name: agentes; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.agentes (numero_agente, nombres_agente, apellidos_agente, numero_usuario) FROM stdin;
1	Carlos	\N	10
\.


--
-- TOC entry 4970 (class 0 OID 16578)
-- Dependencies: 227
-- Data for Name: clientes; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.clientes (numero_cliente, nombre_cliente, correo, telefono) FROM stdin;
22	prueba	rosalesrizzoadolfoangel108@gmail.com	1231231212
23	\N	\N	\N
24	\N	\N	\N
25	\N	\N	\N
26	\N	\N	\N
27	\N	\N	\N
28	\N	\N	\N
29	\N	\N	\N
30	\N	\N	\N
21	Adolfo	\N	2711520697
31	Adolfo	\N	2711520697
32	Adolfo	\N	2711520697
34	fdgd	ngffgh@gmail.com	1231231231
33	Ya no se llama prueba	prueba@prueba.com	1231231212
35	Adolfo	adolfo@hotmail.com	1231231231
36	Adolfo Angel Rosales Rizzo	adolfoangel@gmail.com	2711520697
37	prueba	asdfasdff@gmail.com	1231231212
38	Adolfo	adolfo@gmail.com	2711520697
39	prueba	prueba@gmail.com	1231231212
40	prueba	usuario2@gmail.com	1231231212
41	Juan Carlos Bodoque	juancarlosbodoque@gmail.com	2711067895
42	adolfo 	pedestaldeltiempo@hotmail.com	2717152318
43	Tulio Trivino	tuliotrivino@gmail.com	2711528976
44	Elmo	elmo@sistemasescom.com	2347894567
45	roxas	roxas@sistemasescom.com	2347894567
46	Axel	axel@sistemasescom.com	2347894567
47	prueba	correoprueba@gmail.com	2711520697
48	Adolfo Angel Rosales Rizzo	arizzo@sistemasescom.com	2717152318
49	prueba	correo_prueba@gmail.com	2711231212
50	Prueba dos	prueba2@prueba.com	1231231212
51	Prueba Synology	pruebasynology@prueba.com	2717152318
52	Prueba Tres	prueba@pruebatres.com	2717152318
53	Socket	socket@prueba.com	2717152318
54	Adolfo Angel Rosales Rizzo	rosalesrrizadolfoangel108@gmail.com	2711520697
55	Prueba Notis	notis@prueba.com	3333333333
56	Adolfo Angel Rosales Rizzo	adolfo@sistemasescom.com	2711520697
57	Adolfo	adolfo@sistemaseescom.com	2711520697
58	Adolfo Angel Rosales Rizzo 	rosalesrizzoadolfo108@gmail.com	2711520697
59	Adolfo Angel	rosalesrizzoadolfo@sistemasescom.com	2711520697
60	Mi Nombre Es Prueba	correodeprueba@prueba.com	2711520697
61	Adolfo	ejemplo@ejemplo.com	3333332222
62	Adolfo prueba	prueba@sistemasescom.com	2711520697
\.



--
-- TOC entry 4994 (class 0 OID 0)
-- Dependencies: 224
-- Name: Agente_Numero_Agente_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte."Agente_Numero_Agente_seq"', 2, true);


--
-- TOC entry 4995 (class 0 OID 0)
-- Dependencies: 226
-- Name: Cliente_no_cliente_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte."Cliente_no_cliente_seq"', 62, true);


--
-- TOC entry 4996 (class 0 OID 0)
-- Dependencies: 228
-- Name: Ticket_numero_ticket_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte."Ticket_numero_ticket_seq"', 417, true);


--
-- TOC entry 4997 (class 0 OID 0)
-- Dependencies: 256
-- Name: comentario_ticket_numero_comentario_ticket_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte.comentario_ticket_numero_comentario_ticket_seq', 323, true);


--
-- TOC entry 4998 (class 0 OID 0)
-- Dependencies: 255
-- Name: comentarios_numero_comentario_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte.comentarios_numero_comentario_seq', 339, true);


--
-- TOC entry 4999 (class 0 OID 0)
-- Dependencies: 242
-- Name: notificacion_numero_notificacion_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte.notificacion_numero_notificacion_seq', 344, true);


--
-- TOC entry 5000 (class 0 OID 0)
-- Dependencies: 244
-- Name: producto_tipo_numero_producto_tipo_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte.producto_tipo_numero_producto_tipo_seq', 91, true);


--
-- TOC entry 5001 (class 0 OID 0)
-- Dependencies: 231
-- Name: productoticket_numero_producto_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte.productoticket_numero_producto_seq', 103, true);


--
-- TOC entry 5002 (class 0 OID 0)
-- Dependencies: 247
-- Name: tipo_identificador_numero_identificador_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte.tipo_identificador_numero_identificador_seq', 10, true);


--
-- TOC entry 5003 (class 0 OID 0)
-- Dependencies: 258
-- Name: usuario_notificacion_numero_usuario_notificacion_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte.usuario_notificacion_numero_usuario_notificacion_seq', 4, true);


--
-- TOC entry 5004 (class 0 OID 0)
-- Dependencies: 250
-- Name: usuario_numero_usuario_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte.usuario_numero_usuario_seq', 15, true);


--
-- TOC entry 4786 (class 2606 OID 16576)
-- Name: agentes Agente_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.agentes
    ADD CONSTRAINT "Agente_pkey" PRIMARY KEY (numero_agente);


--
-- TOC entry 4788 (class 2606 OID 16582)
-- Name: clientes Cliente_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.clientes
    ADD CONSTRAINT "Cliente_pkey" PRIMARY KEY (numero_cliente);


--
-- TOC entry 4790 (class 2606 OID 16592)
-- Name: tickets Ticket_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.tickets
    ADD CONSTRAINT "Ticket_pkey" PRIMARY KEY (numero_ticket);


--
-- TOC entry 4806 (class 2606 OID 33002)
-- Name: comentario_ticket comentario_ticket_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.comentario_ticket
    ADD CONSTRAINT comentario_ticket_pkey PRIMARY KEY (numero_comentario_ticket);


--
-- TOC entry 4804 (class 2606 OID 32922)
-- Name: comentarios comentarios_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.comentarios
    ADD CONSTRAINT comentarios_pkey PRIMARY KEY (numero_comentario);


--
-- TOC entry 4794 (class 2606 OID 24610)
-- Name: notificaciones notificacion_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.notificaciones
    ADD CONSTRAINT notificacion_pkey PRIMARY KEY (numero_notificacion);


--
-- TOC entry 4796 (class 2606 OID 24625)
-- Name: producto_tipo producto_tipo_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.producto_tipo
    ADD CONSTRAINT producto_tipo_pkey PRIMARY KEY (numero_producto_tipo);


--
-- TOC entry 4792 (class 2606 OID 16609)
-- Name: productoticket productoticket_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.productoticket
    ADD CONSTRAINT productoticket_pkey PRIMARY KEY (numero_producto);


--
-- TOC entry 4798 (class 2606 OID 24632)
-- Name: tipo_identificador tipo_identificador_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.tipo_identificador
    ADD CONSTRAINT tipo_identificador_pkey PRIMARY KEY (numero_identificador);


--
-- TOC entry 4800 (class 2606 OID 33087)
-- Name: usuario ukewvf9hnihfna9ttfatnrcg38m; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.usuario
    ADD CONSTRAINT ukewvf9hnihfna9ttfatnrcg38m UNIQUE (numero_agente);


--
-- TOC entry 4808 (class 2606 OID 33120)
-- Name: usuario_notificacion usuario_notificacion_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.usuario_notificacion
    ADD CONSTRAINT usuario_notificacion_pkey PRIMARY KEY (numero_usuario_notificacion);


--
-- TOC entry 4802 (class 2606 OID 32891)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (numero_usuario);


--
-- TOC entry 4815 (class 2606 OID 33088)
-- Name: usuario fkjdrdbx14grcxhk81hdtcwytii; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.usuario
    ADD CONSTRAINT fkjdrdbx14grcxhk81hdtcwytii FOREIGN KEY (numero_agente) REFERENCES soporte.agentes(numero_agente);


--
-- TOC entry 4816 (class 2606 OID 33028)
-- Name: comentarios fkruvhvywv5lo63l0cspl6cfakg; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.comentarios
    ADD CONSTRAINT fkruvhvywv5lo63l0cspl6cfakg FOREIGN KEY (numero_comentario) REFERENCES soporte.comentarios(numero_comentario);


--
-- TOC entry 4810 (class 2606 OID 16593)
-- Name: tickets numero_agente; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.tickets
    ADD CONSTRAINT numero_agente FOREIGN KEY (numero_agente) REFERENCES soporte.agentes(numero_agente);


--
-- TOC entry 4811 (class 2606 OID 16598)
-- Name: tickets numero_cliente; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.tickets
    ADD CONSTRAINT numero_cliente FOREIGN KEY (numero_cliente) REFERENCES soporte.clientes(numero_cliente) NOT VALID;


--
-- TOC entry 4817 (class 2606 OID 33003)
-- Name: comentario_ticket numero_comentario; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.comentario_ticket
    ADD CONSTRAINT numero_comentario FOREIGN KEY (numero_comentario) REFERENCES soporte.comentarios(numero_comentario);


--
-- TOC entry 4813 (class 2606 OID 32825)
-- Name: producto_tipo numero_identificador; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.producto_tipo
    ADD CONSTRAINT numero_identificador FOREIGN KEY (numero_identificador) REFERENCES soporte.tipo_identificador(numero_identificador) NOT VALID;


--
-- TOC entry 4820 (class 2606 OID 33126)
-- Name: usuario_notificacion numero_notificacion; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.usuario_notificacion
    ADD CONSTRAINT numero_notificacion FOREIGN KEY (numero_notificacion) REFERENCES soporte.notificaciones(numero_notificacion);


--
-- TOC entry 4812 (class 2606 OID 24593)
-- Name: tickets numero_producto; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.tickets
    ADD CONSTRAINT numero_producto FOREIGN KEY (numero_producto) REFERENCES soporte.productoticket(numero_producto) NOT VALID;


--
-- TOC entry 4814 (class 2606 OID 32820)
-- Name: producto_tipo numero_producto; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.producto_tipo
    ADD CONSTRAINT numero_producto FOREIGN KEY (numero_producto) REFERENCES soporte.productoticket(numero_producto) NOT VALID;


--
-- TOC entry 4818 (class 2606 OID 33008)
-- Name: comentario_ticket numero_ticket; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.comentario_ticket
    ADD CONSTRAINT numero_ticket FOREIGN KEY (numero_ticket) REFERENCES soporte.tickets(numero_ticket);


--
-- TOC entry 4809 (class 2606 OID 32892)
-- Name: agentes numero_usuario; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.agentes
    ADD CONSTRAINT numero_usuario FOREIGN KEY (numero_usuario) REFERENCES soporte.usuario(numero_usuario) NOT VALID;


--
-- TOC entry 4819 (class 2606 OID 33093)
-- Name: comentario_ticket numero_usuario; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.comentario_ticket
    ADD CONSTRAINT numero_usuario FOREIGN KEY (numero_usuario) REFERENCES soporte.usuario(numero_usuario) NOT VALID;


--
-- TOC entry 4821 (class 2606 OID 33121)
-- Name: usuario_notificacion numero_usuario; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.usuario_notificacion
    ADD CONSTRAINT numero_usuario FOREIGN KEY (numero_usuario) REFERENCES soporte.usuario(numero_usuario);


-- Completed on 2025-05-22 12:09:28

--
-- PostgreSQL database dump complete
--

