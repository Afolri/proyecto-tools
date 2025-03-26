--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

-- Started on 2025-03-26 12:31:08

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
-- TOC entry 251 (class 1259 OID 32916)
-- Name: comentarios; Type: TABLE; Schema: soporte; Owner: postgres
--

CREATE TABLE soporte.comentarios (
    numero_comentario bigint NOT NULL,
    contenido character varying NOT NULL,
    numero_agente bigint NOT NULL,
    numero_ticket bigint NOT NULL
);


ALTER TABLE soporte.comentarios OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 24606)
-- Name: notificacion; Type: TABLE; Schema: soporte; Owner: postgres
--

CREATE TABLE soporte.notificacion (
    numero_notificacion bigint NOT NULL,
    estado_notificacion boolean,
    numero_ticket bigint,
    mensaje character varying(255)
);


ALTER TABLE soporte.notificacion OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 24605)
-- Name: notificacion_numero_notificacion_seq; Type: SEQUENCE; Schema: soporte; Owner: postgres
--

ALTER TABLE soporte.notificacion ALTER COLUMN numero_notificacion ADD GENERATED ALWAYS AS IDENTITY (
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
    rol smallint
);


ALTER TABLE soporte.usuario OWNER TO postgres;

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
-- TOC entry 4945 (class 0 OID 16570)
-- Dependencies: 225
-- Data for Name: agentes; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.agentes (numero_agente, nombres_agente, apellidos_agente, numero_usuario) FROM stdin;
1	Carlos	\N	10
\.


--
-- TOC entry 4947 (class 0 OID 16578)
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
\.


--
-- TOC entry 4960 (class 0 OID 32916)
-- Dependencies: 251
-- Data for Name: comentarios; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.comentarios (numero_comentario, contenido, numero_agente, numero_ticket) FROM stdin;
\.


--
-- TOC entry 4953 (class 0 OID 24606)
-- Dependencies: 243
-- Data for Name: notificacion; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.notificacion (numero_notificacion, estado_notificacion, numero_ticket, mensaje) FROM stdin;
152	t	381	Se ha creado un nuevo ticket
\.


--
-- TOC entry 4955 (class 0 OID 24619)
-- Dependencies: 245
-- Data for Name: producto_tipo; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.producto_tipo (numero_producto_tipo, codigo, numero_producto, numero_identificador) FROM stdin;
9	SDFSDF	49	2
10	SDFSDF	49	3
11	NS1234	50	2
12	SDFSDF	51	2
13	123123	49	2
14	234234WR	52	2
15	ABC	53	2
16	ABC	54	2
17	P9876	55	2
18	C9876	56	2
19	PAAA1	57	2
20	PAAA1	58	2
21	PAAA1	59	2
22	PAAA1	60	2
23	PAAA1	61	2
24	PAAA1	62	2
25	PAAA1	63	2
26	PAAA1	64	2
27	PAAA1	65	2
28	PAAA1	66	2
29	123123	67	2
30	NS123	49	2
31	SDFSDF	68	2
32	NSP2	69	2
33	NSP3	70	2
34	NSP3	71	2
35	NSP4	61	2
36	NSP5	72	2
37	NSP6	73	2
38	NSP7	74	2
39	NSP8	75	2
40	SDFSDF	76	2
41	NS10	77	2
42	PAAA1	73	2
43	SDF SDF	78	4
44	NSP1 	79	2
\.


--
-- TOC entry 4950 (class 0 OID 16603)
-- Dependencies: 230
-- Data for Name: productoticket; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.productoticket (numero_producto, numero_compra_cot) FROM stdin;
49	123123
50	12345
51	455666
52	234234
53	123
54	1233
55	6789990
56	76854
57	123123123
58	1
59	2
60	3
61	4
62	5
63	6
64	7
65	8
66	9
67	1234
68	2222
69	98765
70	98766
71	123123121
72	6785
73	23462034
74	2346203465
75	88888888
76	14032025
77	NC10
78	asdasd
79	NCP1
\.


--
-- TOC entry 4949 (class 0 OID 16586)
-- Dependencies: 229
-- Data for Name: tickets; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.tickets (numero_ticket, asunto, descripcion, numero_agente, numero_cliente, numero_producto, fecha, estado, hora) FROM stdin;
381	soporte	Ticket prueba	1	49	79	2025-03-25	ABIERTO	14:58:14
\.


--
-- TOC entry 4956 (class 0 OID 24626)
-- Dependencies: 246
-- Data for Name: tipo_identificador; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.tipo_identificador (numero_identificador, nombre_identificador) FROM stdin;
2	NUMERODESERIE
3	FACTURA
4	MODELOMARCA
\.


--
-- TOC entry 4958 (class 0 OID 32885)
-- Dependencies: 249
-- Data for Name: usuario; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.usuario (numero_usuario, correo, nombre_usuario, password, rol) FROM stdin;
10	carlos@sistemasescom.com	Carlos	$2a$10$5VaVxUP6RoCzauGyQRyipeJ1cxqXlT/hvA/sYHNUqQAjM3G8m/ZIO	1
14	adolfo@sistemasescom.com	adolfo	$2a$10$4ewCMpewIKIjLeRuKkoCbu5QgwLF3xYPNicgkmY584LVAGodGD.f6	0
\.


--
-- TOC entry 4966 (class 0 OID 0)
-- Dependencies: 224
-- Name: Agente_Numero_Agente_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte."Agente_Numero_Agente_seq"', 2, true);


--
-- TOC entry 4967 (class 0 OID 0)
-- Dependencies: 226
-- Name: Cliente_no_cliente_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte."Cliente_no_cliente_seq"', 49, true);


--
-- TOC entry 4968 (class 0 OID 0)
-- Dependencies: 228
-- Name: Ticket_numero_ticket_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte."Ticket_numero_ticket_seq"', 381, true);


--
-- TOC entry 4969 (class 0 OID 0)
-- Dependencies: 242
-- Name: notificacion_numero_notificacion_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte.notificacion_numero_notificacion_seq', 152, true);


--
-- TOC entry 4970 (class 0 OID 0)
-- Dependencies: 244
-- Name: producto_tipo_numero_producto_tipo_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte.producto_tipo_numero_producto_tipo_seq', 44, true);


--
-- TOC entry 4971 (class 0 OID 0)
-- Dependencies: 231
-- Name: productoticket_numero_producto_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte.productoticket_numero_producto_seq', 79, true);


--
-- TOC entry 4972 (class 0 OID 0)
-- Dependencies: 247
-- Name: tipo_identificador_numero_identificador_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte.tipo_identificador_numero_identificador_seq', 4, true);


--
-- TOC entry 4973 (class 0 OID 0)
-- Dependencies: 250
-- Name: usuario_numero_usuario_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte.usuario_numero_usuario_seq', 14, true);


--
-- TOC entry 4773 (class 2606 OID 16576)
-- Name: agentes Agente_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.agentes
    ADD CONSTRAINT "Agente_pkey" PRIMARY KEY (numero_agente);


--
-- TOC entry 4775 (class 2606 OID 16582)
-- Name: clientes Cliente_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.clientes
    ADD CONSTRAINT "Cliente_pkey" PRIMARY KEY (numero_cliente);


--
-- TOC entry 4777 (class 2606 OID 16592)
-- Name: tickets Ticket_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.tickets
    ADD CONSTRAINT "Ticket_pkey" PRIMARY KEY (numero_ticket);


--
-- TOC entry 4789 (class 2606 OID 32922)
-- Name: comentarios comentarios_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.comentarios
    ADD CONSTRAINT comentarios_pkey PRIMARY KEY (numero_comentario);


--
-- TOC entry 4781 (class 2606 OID 24610)
-- Name: notificacion notificacion_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.notificacion
    ADD CONSTRAINT notificacion_pkey PRIMARY KEY (numero_notificacion);


--
-- TOC entry 4783 (class 2606 OID 24625)
-- Name: producto_tipo producto_tipo_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.producto_tipo
    ADD CONSTRAINT producto_tipo_pkey PRIMARY KEY (numero_producto_tipo);


--
-- TOC entry 4779 (class 2606 OID 16609)
-- Name: productoticket productoticket_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.productoticket
    ADD CONSTRAINT productoticket_pkey PRIMARY KEY (numero_producto);


--
-- TOC entry 4785 (class 2606 OID 24632)
-- Name: tipo_identificador tipo_identificador_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.tipo_identificador
    ADD CONSTRAINT tipo_identificador_pkey PRIMARY KEY (numero_identificador);


--
-- TOC entry 4787 (class 2606 OID 32891)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (numero_usuario);


--
-- TOC entry 4791 (class 2606 OID 16593)
-- Name: tickets numero_agente; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.tickets
    ADD CONSTRAINT numero_agente FOREIGN KEY (numero_agente) REFERENCES soporte.agentes(numero_agente);


--
-- TOC entry 4797 (class 2606 OID 32923)
-- Name: comentarios numero_agente; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.comentarios
    ADD CONSTRAINT numero_agente FOREIGN KEY (numero_agente) REFERENCES soporte.agentes(numero_agente);


--
-- TOC entry 4792 (class 2606 OID 16598)
-- Name: tickets numero_cliente; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.tickets
    ADD CONSTRAINT numero_cliente FOREIGN KEY (numero_cliente) REFERENCES soporte.clientes(numero_cliente) NOT VALID;


--
-- TOC entry 4795 (class 2606 OID 32825)
-- Name: producto_tipo numero_identificador; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.producto_tipo
    ADD CONSTRAINT numero_identificador FOREIGN KEY (numero_identificador) REFERENCES soporte.tipo_identificador(numero_identificador) NOT VALID;


--
-- TOC entry 4793 (class 2606 OID 24593)
-- Name: tickets numero_producto; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.tickets
    ADD CONSTRAINT numero_producto FOREIGN KEY (numero_producto) REFERENCES soporte.productoticket(numero_producto) NOT VALID;


--
-- TOC entry 4796 (class 2606 OID 32820)
-- Name: producto_tipo numero_producto; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.producto_tipo
    ADD CONSTRAINT numero_producto FOREIGN KEY (numero_producto) REFERENCES soporte.productoticket(numero_producto) NOT VALID;


--
-- TOC entry 4794 (class 2606 OID 24611)
-- Name: notificacion numero_ticket; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.notificacion
    ADD CONSTRAINT numero_ticket FOREIGN KEY (numero_ticket) REFERENCES soporte.tickets(numero_ticket);


--
-- TOC entry 4798 (class 2606 OID 32928)
-- Name: comentarios numero_ticket; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.comentarios
    ADD CONSTRAINT numero_ticket FOREIGN KEY (numero_ticket) REFERENCES soporte.tickets(numero_ticket);


--
-- TOC entry 4790 (class 2606 OID 32892)
-- Name: agentes numero_usuario; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.agentes
    ADD CONSTRAINT numero_usuario FOREIGN KEY (numero_usuario) REFERENCES soporte.usuario(numero_usuario) NOT VALID;


-- Completed on 2025-03-26 12:31:08

--
-- PostgreSQL database dump complete
--

