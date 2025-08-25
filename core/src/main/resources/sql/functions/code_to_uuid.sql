CREATE OR REPLACE FUNCTION code_to_uuid(code TEXT) RETURNS UUID AS
$$
BEGIN
    RETURN uuid_generate_v5('74738ff5-5367-5958-9aee-98fffdcd1876'::UUID, code);
END;
$$ LANGUAGE plpgsql;
