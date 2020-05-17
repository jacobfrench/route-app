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
-- Name: confirmation_token; Type: TABLE; Schema: public; Owner: postgres
--
CREATE TABLE public.confirmation_token (
    id character varying(36) NOT NULL,
    confirmation_token character varying(255),
    created_date timestamp without time zone,
    account_id character varying(36) NOT NULL
);

ALTER TABLE public.confirmation_token OWNER TO postgres;

--
-- Name: confirmation_token confirmation_token_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--
ALTER TABLE ONLY public.confirmation_token
    ADD CONSTRAINT confirmation_token_pkey PRIMARY KEY (id);

--
-- Name: confirmation_token fkh665h9dopriepwsv009ovyjic; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--
ALTER TABLE ONLY public.confirmation_token
    ADD CONSTRAINT fkh665h9dopriepwsv009ovyjic FOREIGN KEY (account_id) REFERENCES public.account(id);
