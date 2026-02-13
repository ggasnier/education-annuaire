ALTER TABLE public.formations
    ADD code_certification varchar NULL;
ALTER TABLE public.formations
    ADD CONSTRAINT fk_formations_certifications FOREIGN KEY (code_certification) REFERENCES public.certifications (code);