CREATE TABLE IF NOT EXISTS public.users (
    id BIGSERIAL NOT NULL,
    uuid UUID NOT NULL,
    name CHARACTER VARYING(255) NOT NULL,
    email CHARACTER VARYING(40) NOT NULL,
    password CHARACTER VARYING(255) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT user_pk PRIMARY KEY (id)
);