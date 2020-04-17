SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

CREATE TABLE public.nobel_prize (
    id uuid NOT NULL,
    category character varying(255) NOT NULL,
    laureate_name character varying(255) NOT NULL,
    laureate_surname character varying(255) NOT NULL,
    year integer NOT NULL
);

ALTER TABLE public.nobel_prize OWNER TO back_test;

ALTER TABLE ONLY public.nobel_prize
    ADD CONSTRAINT nobel_prize_pkey PRIMARY KEY (id);

