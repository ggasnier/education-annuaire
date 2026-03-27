package com.guillaumegasnier.education.core.validations.etablissements;

public interface IndicePositionSociale {


    /**
     * IPS de l'établissement
     */
    Double getIndice();

    /**
     * Ecart-type établissement
     */
    Double getEcartType();


    /**
     * IPS national privé
     */
    default Double getIndiceNationalPrive() {
        return null;
    }

    /**
     * IPS national public
     */
    default Double getIndiceNationalPublic() {
        return null;
    }

    /**
     * IPS national
     */
    default Double getIndiceNational() {
        return null;
    }

    /**
     * IPS académique privé
     */
    default Double getIndiceAcademiePrive() {
        return null;
    }

    /**
     * IPS académique public
     */
    default Double getIndiceAcademiePublic() {
        return null;
    }

    /**
     * IPS académique
     */
    default Double getIndiceAcademie() {
        return null;
    }

    /**
     * IPS départemental privé
     */
    default Double getIndiceDepartementPrive() {
        return null;
    }

    /**
     * IPS départemental public
     */
    default Double getIndiceDepartementPublic() {
        return null;
    }

    /**
     * IPS départemental
     */
    default Double getIndiceDepartement() {
        return null;
    }

}
