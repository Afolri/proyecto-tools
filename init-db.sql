--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

-- Started on 2025-03-29 11:49:18

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
-- TOC entry 232 (class 1259 OID 16661)
-- Name: almacenamientos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.almacenamientos (
    numero_almacenamiento bigint NOT NULL,
    marca character varying,
    capacidad character varying,
    tipo character varying
);


ALTER TABLE public.almacenamientos OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 16801)
-- Name: almacenamientos_numero_almacenamiento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.almacenamientos ALTER COLUMN numero_almacenamiento ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.almacenamientos_numero_almacenamiento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 248 (class 1259 OID 32838)
-- Name: comentario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comentario (
    numero_comentario bigint NOT NULL,
    contenido character varying NOT NULL,
    numero_agente bigint NOT NULL,
    numero_ticket bigint NOT NULL
);


ALTER TABLE public.comentario OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 16793)
-- Name: interno; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.interno (
    numero_interno bigint NOT NULL,
    nombre_area character varying,
    numero_responsable bigint
);


ALTER TABLE public.interno OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 16792)
-- Name: interno_numero_interno_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.interno ALTER COLUMN numero_interno ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.interno_numero_interno_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 221 (class 1259 OID 16492)
-- Name: marcas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.marcas (
    id_marca integer NOT NULL,
    nombre_marca character varying(255)
);


ALTER TABLE public.marcas OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16491)
-- Name: marcas_id_marca_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.marcas ALTER COLUMN id_marca ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.marcas_id_marca_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 234 (class 1259 OID 16753)
-- Name: pago; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pago (
    id_pago integer NOT NULL,
    id_entidad integer,
    monto numeric(10,2)
);


ALTER TABLE public.pago OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16514)
-- Name: procesador; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.procesador (
    id_procesador integer NOT NULL,
    fabricante character varying(255),
    modelo character varying,
    modela character varying(255)
);


ALTER TABLE public.procesador OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16513)
-- Name: procesador_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.procesador_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.procesador_id_seq OWNER TO postgres;

--
-- TOC entry 5021 (class 0 OID 0)
-- Dependencies: 222
-- Name: procesador_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.procesador_id_seq OWNED BY public.procesador.id_procesador;


--
-- TOC entry 219 (class 1259 OID 16467)
-- Name: productos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.productos (
    id bigint NOT NULL,
    noserie character varying(255),
    estado_producto character varying(255),
    id_marca integer,
    id_procesador integer,
    ram character varying(255),
    fecha_configuracion date,
    usuario character varying(255),
    numero_responsable bigint,
    numero_sistema bigint,
    numero_almacenamiento bigint,
    so smallint,
    version_so integer
);


ALTER TABLE public.productos OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16466)
-- Name: productos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.productos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.productos_id_seq OWNER TO postgres;

--
-- TOC entry 5022 (class 0 OID 0)
-- Dependencies: 218
-- Name: productos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.productos_id_seq OWNED BY public.productos.id;


--
-- TOC entry 252 (class 1259 OID 32953)
-- Name: productos_id_seq1; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.productos ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.productos_id_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 235 (class 1259 OID 16763)
-- Name: responsable; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.responsable (
    numero_responsable bigint NOT NULL,
    tipo_responsable character varying
);


ALTER TABLE public.responsable OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 16800)
-- Name: responsable_numero_responsable_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.responsable ALTER COLUMN numero_responsable ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.responsable_numero_responsable_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 233 (class 1259 OID 16668)
-- Name: sistemas_operativos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sistemas_operativos (
    numero_sistema bigint NOT NULL,
    sistema_operativo character varying,
    version_sistema character varying
);


ALTER TABLE public.sistemas_operativos OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 16810)
-- Name: trabajador; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.trabajador (
    numero_trabajador bigint NOT NULL,
    nombre_trabajador character varying,
    numero_responsable bigint
);


ALTER TABLE public.trabajador OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 16809)
-- Name: trabajador_numero_trabajador_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.trabajador ALTER COLUMN numero_trabajador ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.trabajador_numero_trabajador_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


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
    contenido character varying(255) NOT NULL,
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
-- TOC entry 4995 (class 0 OID 16661)
-- Dependencies: 232
-- Data for Name: almacenamientos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.almacenamientos (numero_almacenamiento, marca, capacidad, tipo) FROM stdin;
\.


--
-- TOC entry 5011 (class 0 OID 32838)
-- Dependencies: 248
-- Data for Name: comentario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comentario (numero_comentario, contenido, numero_agente, numero_ticket) FROM stdin;
\.


--
-- TOC entry 5000 (class 0 OID 16793)
-- Dependencies: 237
-- Data for Name: interno; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.interno (numero_interno, nombre_area, numero_responsable) FROM stdin;
\.


--
-- TOC entry 4984 (class 0 OID 16492)
-- Dependencies: 221
-- Data for Name: marcas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.marcas (id_marca, nombre_marca) FROM stdin;
1	HP
2	Lenovo
\.


--
-- TOC entry 4997 (class 0 OID 16753)
-- Dependencies: 234
-- Data for Name: pago; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pago (id_pago, id_entidad, monto) FROM stdin;
\.


--
-- TOC entry 4986 (class 0 OID 16514)
-- Dependencies: 223
-- Data for Name: procesador; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.procesador (id_procesador, fabricante, modelo, modela) FROM stdin;
1	INTEL	Core i3-1215U	\N
\.


--
-- TOC entry 4982 (class 0 OID 16467)
-- Dependencies: 219
-- Data for Name: productos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.productos (id, noserie, estado_producto, id_marca, id_procesador, ram, fecha_configuracion, usuario, numero_responsable, numero_sistema, numero_almacenamiento, so, version_so) FROM stdin;
54	5CG35233S0\t	nuevo	1	1	8	2025-01-30	user21	\N	\N	\N	\N	\N
55	5CG3523302	Nuevo	1	1	8	2025-02-10	User22	\N	\N	\N	\N	\N
\.


--
-- TOC entry 4998 (class 0 OID 16763)
-- Dependencies: 235
-- Data for Name: responsable; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.responsable (numero_responsable, tipo_responsable) FROM stdin;
\.


--
-- TOC entry 4996 (class 0 OID 16668)
-- Dependencies: 233
-- Data for Name: sistemas_operativos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sistemas_operativos (numero_sistema, sistema_operativo, version_sistema) FROM stdin;
\.


--
-- TOC entry 5004 (class 0 OID 16810)
-- Dependencies: 241
-- Data for Name: trabajador; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.trabajador (numero_trabajador, nombre_trabajador, numero_responsable) FROM stdin;
\.


--
-- TOC entry 4988 (class 0 OID 16570)
-- Dependencies: 225
-- Data for Name: agentes; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.agentes (numero_agente, nombres_agente, apellidos_agente, numero_usuario) FROM stdin;
1	Carlos	\N	10
\.


--
-- TOC entry 4990 (class 0 OID 16578)
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
-- TOC entry 5014 (class 0 OID 32916)
-- Dependencies: 251
-- Data for Name: comentarios; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.comentarios (numero_comentario, contenido, numero_agente, numero_ticket) FROM stdin;
\.


--
-- TOC entry 5006 (class 0 OID 24606)
-- Dependencies: 243
-- Data for Name: notificacion; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.notificacion (numero_notificacion, estado_notificacion, numero_ticket, mensaje) FROM stdin;
152	t	381	Se ha creado un nuevo ticket
\.


--
-- TOC entry 5008 (class 0 OID 24619)
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
-- TOC entry 4993 (class 0 OID 16603)
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
-- TOC entry 4992 (class 0 OID 16586)
-- Dependencies: 229
-- Data for Name: tickets; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.tickets (numero_ticket, asunto, descripcion, numero_agente, numero_cliente, numero_producto, fecha, estado, hora) FROM stdin;
381	soporte	Ticket prueba	1	49	79	2025-03-25	ABIERTO	14:58:14
\.


--
-- TOC entry 5009 (class 0 OID 24626)
-- Dependencies: 246
-- Data for Name: tipo_identificador; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.tipo_identificador (numero_identificador, nombre_identificador) FROM stdin;
2	NUMERODESERIE
3	FACTURA
4	MODELOMARCA
\.


--
-- TOC entry 5012 (class 0 OID 32885)
-- Dependencies: 249
-- Data for Name: usuario; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.usuario (numero_usuario, correo, nombre_usuario, password, rol) FROM stdin;
10	carlos@sistemasescom.com	Carlos	$2a$10$5VaVxUP6RoCzauGyQRyipeJ1cxqXlT/hvA/sYHNUqQAjM3G8m/ZIO	1
14	adolfo@sistemasescom.com	adolfo	$2a$10$4ewCMpewIKIjLeRuKkoCbu5QgwLF3xYPNicgkmY584LVAGodGD.f6	0
\.


--
-- TOC entry 5023 (class 0 OID 0)
-- Dependencies: 239
-- Name: almacenamientos_numero_almacenamiento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.almacenamientos_numero_almacenamiento_seq', 1, false);


--
-- TOC entry 5024 (class 0 OID 0)
-- Dependencies: 236
-- Name: interno_numero_interno_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.interno_numero_interno_seq', 1, false);


--
-- TOC entry 5025 (class 0 OID 0)
-- Dependencies: 220
-- Name: marcas_id_marca_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.marcas_id_marca_seq', 2, true);


--
-- TOC entry 5026 (class 0 OID 0)
-- Dependencies: 222
-- Name: procesador_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.procesador_id_seq', 1, true);


--
-- TOC entry 5027 (class 0 OID 0)
-- Dependencies: 218
-- Name: productos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.productos_id_seq', 56, true);


--
-- TOC entry 5028 (class 0 OID 0)
-- Dependencies: 252
-- Name: productos_id_seq1; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.productos_id_seq1', 1, false);


--
-- TOC entry 5029 (class 0 OID 0)
-- Dependencies: 238
-- Name: responsable_numero_responsable_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.responsable_numero_responsable_seq', 1, false);


--
-- TOC entry 5030 (class 0 OID 0)
-- Dependencies: 240
-- Name: trabajador_numero_trabajador_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.trabajador_numero_trabajador_seq', 1, false);


--
-- TOC entry 5031 (class 0 OID 0)
-- Dependencies: 224
-- Name: Agente_Numero_Agente_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte."Agente_Numero_Agente_seq"', 2, true);


--
-- TOC entry 5032 (class 0 OID 0)
-- Dependencies: 226
-- Name: Cliente_no_cliente_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte."Cliente_no_cliente_seq"', 49, true);


--
-- TOC entry 5033 (class 0 OID 0)
-- Dependencies: 228
-- Name: Ticket_numero_ticket_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte."Ticket_numero_ticket_seq"', 381, true);


--
-- TOC entry 5034 (class 0 OID 0)
-- Dependencies: 242
-- Name: notificacion_numero_notificacion_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte.notificacion_numero_notificacion_seq', 152, true);


--
-- TOC entry 5035 (class 0 OID 0)
-- Dependencies: 244
-- Name: producto_tipo_numero_producto_tipo_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte.producto_tipo_numero_producto_tipo_seq', 44, true);


--
-- TOC entry 5036 (class 0 OID 0)
-- Dependencies: 231
-- Name: productoticket_numero_producto_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte.productoticket_numero_producto_seq', 79, true);


--
-- TOC entry 5037 (class 0 OID 0)
-- Dependencies: 247
-- Name: tipo_identificador_numero_identificador_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte.tipo_identificador_numero_identificador_seq', 4, true);


--
-- TOC entry 5038 (class 0 OID 0)
-- Dependencies: 250
-- Name: usuario_numero_usuario_seq; Type: SEQUENCE SET; Schema: soporte; Owner: postgres
--

SELECT pg_catalog.setval('soporte.usuario_numero_usuario_seq', 14, true);


--
-- TOC entry 4798 (class 2606 OID 16667)
-- Name: almacenamientos almacenamientos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.almacenamientos
    ADD CONSTRAINT almacenamientos_pkey PRIMARY KEY (numero_almacenamiento);


--
-- TOC entry 4816 (class 2606 OID 32844)
-- Name: comentario comentario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT comentario_pkey PRIMARY KEY (numero_comentario);


--
-- TOC entry 4806 (class 2606 OID 16799)
-- Name: interno interno_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.interno
    ADD CONSTRAINT interno_pkey PRIMARY KEY (numero_interno);


--
-- TOC entry 4786 (class 2606 OID 16498)
-- Name: marcas marcas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marcas
    ADD CONSTRAINT marcas_pkey PRIMARY KEY (id_marca);


--
-- TOC entry 4802 (class 2606 OID 16757)
-- Name: pago pago_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pago
    ADD CONSTRAINT pago_pkey PRIMARY KEY (id_pago);


--
-- TOC entry 4788 (class 2606 OID 16519)
-- Name: procesador procesador_pkey1; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.procesador
    ADD CONSTRAINT procesador_pkey1 PRIMARY KEY (id_procesador);


--
-- TOC entry 4784 (class 2606 OID 32946)
-- Name: productos productos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productos
    ADD CONSTRAINT productos_pkey PRIMARY KEY (id);


--
-- TOC entry 4804 (class 2606 OID 16803)
-- Name: responsable responsable_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.responsable
    ADD CONSTRAINT responsable_pkey PRIMARY KEY (numero_responsable);


--
-- TOC entry 4800 (class 2606 OID 16674)
-- Name: sistemas_operativos sistemas_operativos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sistemas_operativos
    ADD CONSTRAINT sistemas_operativos_pkey PRIMARY KEY (numero_sistema);


--
-- TOC entry 4808 (class 2606 OID 16814)
-- Name: trabajador trabajador_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trabajador
    ADD CONSTRAINT trabajador_pkey PRIMARY KEY (numero_trabajador);


--
-- TOC entry 4790 (class 2606 OID 16576)
-- Name: agentes Agente_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.agentes
    ADD CONSTRAINT "Agente_pkey" PRIMARY KEY (numero_agente);


--
-- TOC entry 4792 (class 2606 OID 16582)
-- Name: clientes Cliente_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.clientes
    ADD CONSTRAINT "Cliente_pkey" PRIMARY KEY (numero_cliente);


--
-- TOC entry 4794 (class 2606 OID 16592)
-- Name: tickets Ticket_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.tickets
    ADD CONSTRAINT "Ticket_pkey" PRIMARY KEY (numero_ticket);


--
-- TOC entry 4820 (class 2606 OID 32922)
-- Name: comentarios comentarios_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.comentarios
    ADD CONSTRAINT comentarios_pkey PRIMARY KEY (numero_comentario);


--
-- TOC entry 4810 (class 2606 OID 24610)
-- Name: notificacion notificacion_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.notificacion
    ADD CONSTRAINT notificacion_pkey PRIMARY KEY (numero_notificacion);


--
-- TOC entry 4812 (class 2606 OID 24625)
-- Name: producto_tipo producto_tipo_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.producto_tipo
    ADD CONSTRAINT producto_tipo_pkey PRIMARY KEY (numero_producto_tipo);


--
-- TOC entry 4796 (class 2606 OID 16609)
-- Name: productoticket productoticket_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.productoticket
    ADD CONSTRAINT productoticket_pkey PRIMARY KEY (numero_producto);


--
-- TOC entry 4814 (class 2606 OID 24632)
-- Name: tipo_identificador tipo_identificador_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.tipo_identificador
    ADD CONSTRAINT tipo_identificador_pkey PRIMARY KEY (numero_identificador);


--
-- TOC entry 4818 (class 2606 OID 32891)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (numero_usuario);


--
-- TOC entry 4821 (class 2606 OID 16522)
-- Name: productos id_procesador; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productos
    ADD CONSTRAINT id_procesador FOREIGN KEY (id_procesador) REFERENCES public.procesador(id_procesador) NOT VALID;


--
-- TOC entry 4832 (class 2606 OID 32845)
-- Name: comentario numero_agente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT numero_agente FOREIGN KEY (numero_agente) REFERENCES soporte.agentes(numero_agente);


--
-- TOC entry 4827 (class 2606 OID 16804)
-- Name: interno numero_responsable; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.interno
    ADD CONSTRAINT numero_responsable FOREIGN KEY (numero_responsable) REFERENCES public.responsable(numero_responsable) NOT VALID;


--
-- TOC entry 4828 (class 2606 OID 16817)
-- Name: trabajador numero_responsable; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trabajador
    ADD CONSTRAINT numero_responsable FOREIGN KEY (numero_responsable) REFERENCES public.responsable(numero_responsable) NOT VALID;


--
-- TOC entry 4833 (class 2606 OID 32850)
-- Name: comentario numero_ticket; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT numero_ticket FOREIGN KEY (numero_ticket) REFERENCES soporte.tickets(numero_ticket);


--
-- TOC entry 4822 (class 2606 OID 16499)
-- Name: productos productos_marca_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productos
    ADD CONSTRAINT productos_marca_fkey FOREIGN KEY (id_marca) REFERENCES public.marcas(id_marca) NOT VALID;


--
-- TOC entry 4824 (class 2606 OID 16593)
-- Name: tickets numero_agente; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.tickets
    ADD CONSTRAINT numero_agente FOREIGN KEY (numero_agente) REFERENCES soporte.agentes(numero_agente);


--
-- TOC entry 4834 (class 2606 OID 32923)
-- Name: comentarios numero_agente; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.comentarios
    ADD CONSTRAINT numero_agente FOREIGN KEY (numero_agente) REFERENCES soporte.agentes(numero_agente);


--
-- TOC entry 4825 (class 2606 OID 16598)
-- Name: tickets numero_cliente; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.tickets
    ADD CONSTRAINT numero_cliente FOREIGN KEY (numero_cliente) REFERENCES soporte.clientes(numero_cliente) NOT VALID;


--
-- TOC entry 4830 (class 2606 OID 32825)
-- Name: producto_tipo numero_identificador; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.producto_tipo
    ADD CONSTRAINT numero_identificador FOREIGN KEY (numero_identificador) REFERENCES soporte.tipo_identificador(numero_identificador) NOT VALID;


--
-- TOC entry 4826 (class 2606 OID 24593)
-- Name: tickets numero_producto; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.tickets
    ADD CONSTRAINT numero_producto FOREIGN KEY (numero_producto) REFERENCES soporte.productoticket(numero_producto) NOT VALID;


--
-- TOC entry 4831 (class 2606 OID 32820)
-- Name: producto_tipo numero_producto; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.producto_tipo
    ADD CONSTRAINT numero_producto FOREIGN KEY (numero_producto) REFERENCES soporte.productoticket(numero_producto) NOT VALID;


--
-- TOC entry 4829 (class 2606 OID 24611)
-- Name: notificacion numero_ticket; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.notificacion
    ADD CONSTRAINT numero_ticket FOREIGN KEY (numero_ticket) REFERENCES soporte.tickets(numero_ticket);


--
-- TOC entry 4835 (class 2606 OID 32928)
-- Name: comentarios numero_ticket; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.comentarios
    ADD CONSTRAINT numero_ticket FOREIGN KEY (numero_ticket) REFERENCES soporte.tickets(numero_ticket);


--
-- TOC entry 4823 (class 2606 OID 32892)
-- Name: agentes numero_usuario; Type: FK CONSTRAINT; Schema: soporte; Owner: postgres
--

ALTER TABLE ONLY soporte.agentes
    ADD CONSTRAINT numero_usuario FOREIGN KEY (numero_usuario) REFERENCES soporte.usuario(numero_usuario) NOT VALID;


-- Completed on 2025-03-29 11:49:18

--
-- PostgreSQL database dump complete
--

