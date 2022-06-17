CREATE TABLE IF NOT EXISTS public.offers
(
    offer_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    desc_offer character varying(15) COLLATE pg_catalog."default" NOT NULL,
    starting date NOT NULL,
    ending date NOT NULL,
    CONSTRAINT offers_pkey PRIMARY KEY (offer_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.offers
    OWNER to postgres;









CREATE TABLE IF NOT EXISTS public.customers
(
    customer_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    full_name character varying(40) COLLATE pg_catalog."default" NOT NULL,
    birth_date date NOT NULL,
    address character varying(40) COLLATE pg_catalog."default" NOT NULL,
    telephone text COLLATE pg_catalog."default" NOT NULL,
    email character varying COLLATE pg_catalog."default" NOT NULL,
    offer_id integer,
    CONSTRAINT customers_pkey PRIMARY KEY (customer_id),
    CONSTRAINT telephone UNIQUE (telephone)
        INCLUDE(telephone),
    CONSTRAINT offer_id FOREIGN KEY (offer_id)
        REFERENCES public.offers (offer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.customers
    OWNER to postgres;









CREATE TABLE IF NOT EXISTS public.quotation
(
    quotation_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    code text COLLATE pg_catalog."default" NOT NULL,
    quotation_date date NOT NULL,
    price numeric NOT NULL,
    offer_id integer,
    customer_id integer NOT NULL,
    CONSTRAINT quotation_pkey PRIMARY KEY (quotation_id),
    CONSTRAINT customer_id FOREIGN KEY (customer_id)
        REFERENCES public.customers (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT offer_id FOREIGN KEY (offer_id)
        REFERENCES public.offers (offer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.quotation
    OWNER to postgres;










INSERT INTO public.offers(
	 desc_offer, starting, ending)
	VALUES ( 'Base', '2022-01-01', '2023-01-01');

INSERT INTO public.offers(
	 desc_offer, starting, ending)
	VALUES ( 'Premium', '2022-01-01', '2024-01-01');

INSERT INTO public.offers(
	 desc_offer, starting, ending)
	VALUES ( 'Deluxe', '2022-01-01', '2025-01-01');




INSERT INTO public.customers(
	 full_name, birth_date, address, telephone, email, offer_id)
	VALUES ('Mario Rossi', '1960-01-01', 'Via Roma 14, Milano', '+393313216549', 'mario@me.com', '1');



INSERT INTO public.quotation(
	 code, quotation_date, price, offer_id, customer_id)
	VALUES ('A123', '2022-06-17', '99.99', '1', '1');





SELECT offer_id, desc_offer, starting, ending
	FROM public.offers;


SELECT customer_id, full_name, birth_date, address, telephone, email, offer_id
	FROM public.customers;


SELECT quotation_id, code, quotation_date, price, offer_id, customer_id
	FROM public.quotation;

