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


--
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--
CREATE TABLE public.account (
    id character varying(36) NOT NULL,
    password character varying(255),
    username character varying(255),
    verified boolean
);

ALTER TABLE public.account OWNER TO postgres;

--
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--
ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);