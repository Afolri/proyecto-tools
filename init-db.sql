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
-- TOC entry 4984 (class 0 OID 32998)
-- Dependencies: 254
-- Data for Name: comentario_ticket; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.comentario_ticket (numero_comentario_ticket, numero_comentario, numero_ticket, numero_usuario) FROM stdin;
227	243	405	14
228	244	405	14
229	245	405	14
230	246	405	14
231	247	405	14
232	248	405	14
233	249	405	14
234	250	405	14
235	251	405	14
236	252	405	14
237	253	404	14
238	254	405	14
239	255	405	10
240	256	404	10
241	257	404	14
242	258	404	10
243	259	404	14
244	260	405	10
245	261	405	14
246	262	405	14
247	263	405	14
248	264	405	14
249	265	405	14
250	266	405	14
251	267	405	14
252	268	405	14
253	269	405	14
254	270	405	14
255	271	405	14
256	272	405	14
257	273	405	10
258	274	405	14
259	275	405	14
260	276	405	14
261	277	405	14
262	278	405	10
263	279	405	14
264	280	405	14
265	281	405	14
266	282	405	14
267	283	405	10
268	284	405	14
269	285	405	14
270	286	405	14
271	287	404	14
272	288	404	14
273	289	404	14
274	290	405	14
275	291	405	14
276	292	399	14
277	293	404	14
278	294	403	14
279	295	405	14
280	296	405	14
281	297	405	14
282	298	405	14
283	299	405	14
284	300	405	14
285	301	405	14
286	302	405	14
287	303	405	14
288	304	405	14
289	305	405	14
290	306	405	14
291	307	405	14
292	308	405	14
293	309	407	14
294	310	407	14
295	311	407	14
296	312	407	14
297	313	407	14
298	314	407	14
299	315	407	14
300	316	407	14
301	317	407	14
302	318	407	14
303	319	407	14
304	320	407	14
305	321	407	14
306	322	407	14
307	323	407	14
308	324	407	14
309	325	407	14
310	326	407	14
311	327	407	14
312	328	407	14
313	329	407	14
314	330	407	14
315	331	407	14
316	332	407	14
317	333	407	14
318	334	407	14
319	335	406	14
320	336	407	14
321	337	417	14
322	338	417	10
323	339	417	10
\.


--
-- TOC entry 4983 (class 0 OID 32916)
-- Dependencies: 251
-- Data for Name: comentarios; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.comentarios (numero_comentario, contenido) FROM stdin;
243	comentario
244	s
245	s
246	s
247	sdf
248	test
249	dfg
250	dfg
251	sdf
252	123
253	dfg
254	s
255	as
256	soy el AGENTE
257	soy el ADMIN
258	hola
259	mensaje bidireccional
260	dfg
261	sdf
262	sdf
263	sdf
264	12
265	12
266	sdf
267	sd
268	12
269	14
270	12
271	111
272	esto es una prueba
273	a
274	s
275	s
276	1
277	2
278	2
279	21
280	21
281	21
282	21
283	2
284	este es un comentario con una longitud diferente a los otros mensajes
285	bb
286	|12
287	test
288	2
289	33
290	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
291	c
292	s
293	e
294	prueba
295	prueba
296	hola
297	sdf
298	1
299	1
300	2
301	2
302	f
303	ejecutado 4 veces
304	test
305	test
306	prueba
307	prueba2
308	prueba4
309	1
310	1
311	1
312	1
313	2
314	2
315	1
316	2
317	23
318	123
319	adolfo@siste
320	usuario1
321	1
322	r
323	prueba
324	es
325	dd
326	ddd
327	ddd
328	ddddd
329	2
330	prueba
331	prueba1
332	adolfo@sistemasescom.com
333	😍😍
334	🍞
335	test
336	1
337	comentario1
338	hola
339	enterado
\.


--
-- TOC entry 4976 (class 0 OID 24606)
-- Dependencies: 243
-- Data for Name: notificaciones; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.notificaciones (numero_notificacion, fecha, hora, mensaje) FROM stdin;
341	2025-05-17	09:50:06	Se ha creado un nuevo ticket
342	2025-05-19	09:53:43	Se ha creado un nuevo ticket
343	2025-05-19	12:36:06	Se ha creado un nuevo ticket
344	2025-05-19	14:53:36	Se ha creado un nuevo ticket
\.


--
-- TOC entry 4978 (class 0 OID 24619)
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
44	NSP1 	79	2
45	NSP2 	80	2
51	NSP1	78	2
52	NSP1 	78	3
53	NSP3	83	2
54	NSP1	81	2
55	NSP1	84	2
56	NSPS2	85	2
57	NSP10	86	2
58	NSPSD	87	2
59	NUMEROPRUEBA	88	2
60	NUMEROPRUEBA	89	2
61	NUMEROPRUEBA	90	2
62	NSP1	91	2
63	NSP1	92	2
64	NSP1	93	2
66	ABC	79	3
68	NSPSD	94	2
69	NSP2	81	2
70	NSP2 	81	3
72	NSP2 HIKVISION	81	4
73	NSPCN1	95	2
74	ASDF	96	2
75	NSPCN10	95	2
76	NSP1	79	2
77	NSP2	80	2
78	NS1	97	2
79	NS2	98	2
80	NS2	99	2
81	NS31	100	2
89	NSE1	101	2
90	NSTEST	102	2
91	NSE2	103	2
\.


--
-- TOC entry 4973 (class 0 OID 16603)
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
80	NCP2
81	NCPS1
82	NCPL1
83	NCP3
84	sdf
85	NCPS2
86	NCPS10
87	NCPSD
88	PRUEBAAPERTURA
89	PRUEBAAPERTURA2
90	PRUEBAAPERTURA3
91	NCPS20
92	NCPS200
93	NCPS20090
94	NCPSD34234
95	NCPCN1
96	asdf
97	NC1
98	NC2
99	NC3
100	NC33
101	NCE1
102	NCTEST
103	NCE2
\.


--
-- TOC entry 4972 (class 0 OID 16586)
-- Dependencies: 229
-- Data for Name: tickets; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.tickets (numero_ticket, asunto, descripcion, numero_agente, numero_cliente, numero_producto, fecha, estado, hora) FROM stdin;
398	soporte	asdfasdf	1	37	92	2025-04-14	CERRADO	12:10:48
393	soporte	Esto es una prueba de notificación por medio de socket\n	1	54	87	2025-04-14	ABIERTO	09:09:24
403	soporte	esto es una prueba para ver los detalles del ticket	1	33	94	2025-04-14	CERRADO	15:19:10
394	soporte	Esta es una prueba para saber si carga correctamente la notificación y puede ser abierta\n	1	33	88	2025-04-14	ABIERTO	09:14:40
412	soporte	es importante	1	22	99	2025-05-14	NUEVO	15:53:35
396	soporte	Esta es una prueba para saber si carga correctamente la notificación y puede ser abierta\n	1	33	90	2025-04-14	ABIERTO	11:22:49
397	soporte	asdfasdf	1	37	91	2025-04-14	ABIERTO	12:09:23
405	soporte	asdf	1	37	96	2025-04-28	CERRADO	09:17:30
384	soporte	Hola prueba desde Synology	1	51	81	2025-04-01	ABIERTO	15:09:57
404	soporte	Prueba de notificaciones	1	55	95	2025-04-28	CERRADO	10:32:29
383	soporte	Esta es una prueba	1	50	80	2025-03-31	ABIERTO	15:38:57
395	soporte	Esta es una prueba para saber si carga correctamente la notificación y puede ser abierta\n	1	33	89	2025-04-14	CERRADO	11:22:05
414	soporte	comentarios	1	22	97	2025-05-17	NUEVO	09:50:02
407	soporte	prueba	1	57	80	2025-05-09	ABIERTO	11:51:15
391	soporte	Esto es una prueba	1	53	85	2025-04-12	ABIERTO	13:13:31
390	soporte	sadfg	1	37	84	2025-04-12	ABIERTO	13:12:01
387	soporte	prueba	1	49	78	2025-04-01	ABIERTO	15:59:20
382	soporte	Prueba	1	33	79	2025-03-31	ABIERTO	15:38:02
389	soporte	Descripcion de prueba tres	1	52	83	2025-04-01	ABIERTO	18:05:18
385	soporte	Esto es una prueba de local	1	33	82	2025-04-01	ABIERTO	15:54:46
416	garantia	Esto es una descripcion	1	56	102	2025-05-19	NUEVO	12:35:58
408	soporte	Test	1	58	97	2025-05-13	NUEVO	11:26:44
417	soporte	prueba	1	62	103	2025-05-19	NUEVO	14:53:30
415	soporte	ejemplo de desccripción	1	61	101	2025-05-19	CERRADO	09:52:58
413	soporte	Esto es una descripción	1	60	100	2025-05-16	CERRADO	17:10:05
409	soporte	Te	1	59	98	2025-05-13	NUEVO	12:55:25
392	soporte	asdfasd	1	37	86	2025-04-22	ABIERTO	12:16:49
406	factura	Agregando descripción	1	56	79	2025-05-14	NUEVO	09:46:34
410	soporte	Esto es una descripción 	1	56	99	2025-05-14	NUEVO	11:06:11
402	soporte	esto es una prueba para ver los detalles del ticket	1	33	87	2025-04-14	CERRADO	15:18:51
388	soporte	asdf	1	37	78	2025-04-12	ABIERTO	10:01:36
386	soporte	tfy	1	37	81	2025-05-06	ABIERTO	11:08:06
401	soporte	esta es una prueba	1	22	79	2025-04-14	CERRADO	13:54:37
399	soporte	asdfasdf	1	37	93	2025-04-14	CERRADO	12:16:28
400	Es importante	Es importante	1	38	54	2025-04-14	CERRADO	12:55:15
\.


--
-- TOC entry 4979 (class 0 OID 24626)
-- Dependencies: 246
-- Data for Name: tipo_identificador; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.tipo_identificador (numero_identificador, nombre_identificador) FROM stdin;
3	FACTURA
2	NUMERO DE SERIE
4	MODELO / MARCA
\.


--
-- TOC entry 4981 (class 0 OID 32885)
-- Dependencies: 249
-- Data for Name: usuario; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.usuario (numero_usuario, correo, nombre_usuario, password, rol, numero_agente) FROM stdin;
10	carlos@sistemasescom.com	Carlos	$2a$10$5VaVxUP6RoCzauGyQRyipeJ1cxqXlT/hvA/sYHNUqQAjM3G8m/ZIO	1	\N
14	adolfo@sistemasescom.com	adolfo	$2a$10$4ewCMpewIKIjLeRuKkoCbu5QgwLF3xYPNicgkmY584LVAGodGD.f6	0	\N
15	prueba@prueba.com	prueba	$2a$10$Vihl7sxkrH0JizbQkw86Pev4/GCbKIJWUoLInuNSx1nqVcxWKjGgu	1	\N
\.


--
-- TOC entry 4987 (class 0 OID 33116)
-- Dependencies: 257
-- Data for Name: usuario_notificacion; Type: TABLE DATA; Schema: soporte; Owner: postgres
--

COPY soporte.usuario_notificacion (numero_usuario_notificacion, visto, numero_usuario, numero_notificacion) FROM stdin;
4	f	10	344
2	f	10	343
3	t	14	344
1	t	14	343
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

