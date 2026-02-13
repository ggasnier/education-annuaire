package com.guillaumegasnier.education.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

@Slf4j
@Getter
@AllArgsConstructor
public enum Sport {

    ACTIVIT_SPORT_AQUATIQUES_PLEINE_NATURE("Activités aquatiques en pleine nature", "🚣", List.of("ACTIVIT. SPORT. AQUATIQUES PLEINE NATURE")),
    ACTIVIT_SPORT_TERRESTRES_PLEINE_NATURE("Activités terrestres en pleine nature", "🌲", List.of("ACTIVITES SPORT.TERRESTRES PLEINE NATURE")),
    ACTIVITES_SPORTIVES_DE_MONTAGNE("Activités sportives de montagne", "🏔️", List.of("ACTIVITES SPORTIVES DE MONTAGNE")),
    ACTIVITES_SPORTIVES_SUR_ROLLERS("Activités sportives sur rollers", "🛼", List.of("ACTIVITES SPORTIVES SUR ROLLERS")),
    ACTIVITES_SPORTIVES_SUBAQUATIQUES("Activités sportives subaquatiques", "🤿", List.of("ACTIVITES SPORTIVES SUBAQUATIQUES")),
    ARTS_MARTIAUX_AUTRES("ARTS MARTIAUX (AUTRES QUE LE JUDO)", "🥋", List.of("ARTS MARTIAUX (AUTRES QUE LE JUDO)")),
    ARBITRAGE("Arbitrage", "🏁", List.of("ARBITRAGE")),
    ARBITRAGE_BASKET_BALL("Arbitrage Basket-ball", "🏀🏁", List.of("ARBITRAGE BASKET-BALL")),
    ARBITRAGE_FOOTBALL("Arbitrage football", "⚽🏁", List.of("ARBITRAGE FOOTBALL", "FOOTBALL (ARBITRE)")),
    ARBITRAGE_SPORTIF("Arbitrage sportif", "🏁", List.of("ARBITRAGE SPORTIF")),
    ATHLETISME("Athlétisme", "🏃", List.of("ATHLETISME", "ATHLÉTISME")),
    AVIRON("Aviron", "🚣", List.of("AVIRON")),
    BADMINTON("Badminton", "🏸", List.of("BADMINTON")),
    BASEBALL("Baseball", "⚾", List.of("BASEBALL")),
    BASKET_BALL("Basket-ball", "🏀", List.of("BASKET-BALL", "BASKET")),
    BASKET_BALL_F("Basket-ball (féminin)", "🏀", List.of("BASKET-BALL FÉMININ")),
    BASKET_BALL_M("Basket-ball (masculin)", "🏀", List.of("BASKET-BALL MASCULIN")),
    BEACH_VOLLEY("Beach-volley", "🏐", List.of("BEACH VOLLEY")),
    BMX("BMX", "🚲", List.of("BMX")),
    BOWLING("Bowling", "🎳", List.of("BOWLING")),
    BOXE("Boxe", "🥊", List.of("BOXE")),
    BOXE_ANGLAISE("Boxe anglaise", "🥊", List.of("BOXE ANGLAISE")),
    BOXE_FRANCAISE("Boxe française", "🥊", List.of("BOXE FRANCAISE", "BOXE FRANÇAISE")),
    BOXE_THAI("Muay-thaï", "🥊", List.of("BOXE THAI")),
    CANOE_KAYAK("", "🛶", List.of("CANOE KAYAK", "CANOE ET CANOE KAYAK", "CANOË-KAYAK")),
    CHAR_A_VOILES("", "⛵", List.of("CHAR A VOILES")),
    COURSE_ORIENTATION("Course d'orientation", "🧭", List.of("COURSE D'ORIENTATION")),
    CYCLISME("Cyclisme", "🚴", List.of("CYCLISME")),
    MECANIQUE("Mécanique", "🏎️", List.of("SPORTS MECANIQUE (AUTO-MOTO)")),
    CYCLISME_PISTE("Cyclisme sur piste", "🚴", List.of("CYCLISME PISTE")),
    DANSE("Danse", "💃", List.of("DANSE SPORTIVE", "DANSE")),
    DISCIPLINES_NORDIQUES("", "🎿", List.of("DISCIPLINES NORDIQUES")),
    EQUITATION("Equitation", "🏇", List.of("EQUITATION", "HIPPOLOGIE ET EQUITATION", "HIPPOLOGIE & EQUITATION", "ÉQUITATION")),
    ESCALADE("Escalade", "🧗", List.of("ESCALADE")),
    ESCRIME("Escrime", "🤺", List.of("ESCRIME")),
    FOOTBALL("Football", "⚽", List.of("FOOTBALL")),
    FOOTBALL_MIXTE("Football (mixte)", "⚽", List.of("FOOTBALL (MIXTE)")),
    FOOTBALL_AMERICAIN("Football américain", "🏈", List.of("FOOTBALL AMERICAIN", "FOOTBALL AMÉRICAIN")),
    FOOTBALL_F("Football féminin", "⚽", List.of("FOOTBALL FEMININ")),
    FOOTBALL_M("Football", "⚽", List.of("FOOTBALL MASCULIN")),
    FUTSAL("Futsal", "⚽", List.of("FOOTBALL EN SALLE (FUTSAL)", "FUTSAL")),
    FUTSAL_M("Futsal (masculin)", "⚽", List.of("FUTSAL GARÇON")),
    GOLF("Golf", "⛳", List.of("GOLF")),
    BICROSS("", "🚲", List.of("BICROSS")),
    CIRQUE("Arts du cirque", "🎪", List.of("ARTS DU CIRQUE", "ACTIVITES SPORTIVES DE CIRQUE")),
    GRS("", "🤸", List.of("GRS", "GYMNASTIQUE RYTHMIQUE ET SPORTIVE")),
    GYM_AEROBIC("", "🤸", List.of("GYM AÉROBIC")),
    GYMNASTIQUE("Gymnastique", "🤸", List.of("GYMNASTIQUE", "GYMNASTIQUE (SANS PRÉCISION)")),
    GYMNASTIQUE_RYTHMIQUE("Gymnastique rythmique", "🤸", List.of("GYMNASTIQUE RYTHMIQUE")),
    GYMNASTIQUE_AEROBIQUE("Gymnastique aérobic", "🤸", List.of("GYMNASTIQUE AEROBIQUE")),
    GYMNASTIQUE_ARTISTIQUE("Gymnastique artistique", "🤸", List.of("GYMNASTIQUE ARTISTIQUE")),
    GYMNASTIQUE_ACROBATIQUE("Gymnastique acrobatique", "🤸", List.of("GYMNASTIQUE ACROBATIQUE")),
    GYMNASTIQUE_F("Gymnastique (féminine)", "🤸", List.of("GYMNASTIQUE FEMININE")),
    GYMNASTIQUE_M("Gymnastique (masculine)", "🤸", List.of("GYMNASTIQUE MASCULINE")),
    HALTEROPHILIE("", "🏋️", List.of("HALTÉROPHILIE", "HALTÉROPHILIE ET MUSCULATION")),
    HANDBALL("Handball", "🤾", List.of("HANDBALL")),
    HANDBALL_F("Handball (féminin)", "🤾", List.of("HANDBALL FEMININ")),
    HANDBALL_M("Handball (masculin)", "🤾", List.of("HANDBALL MASCULIN")),
    HANDISPORT("Handisport", "♿", List.of("HANDISPORT")),
    HANDI_ATHLETISME("Athlétisme Handisport", "♿🏃", List.of("HANDI-ATHLÉTISME")),
    HANDI_BASKET("", "♿🏀", List.of("HANDI-BASKET")),
    HANDI_TENNIS_DE_TABLE("", "♿🏓", List.of("HANDI-TENNIS DE TABLE")),
    HOCKEY_SUR_GAZON("", "🏑", List.of("HOCKEY SUR GAZON")),
    HOCKEY_SUR_GLACE("", "🏒", List.of("HOCKEY SUR GLACE")),
    JUDO("Judo", "🥋", List.of("JUDO")),
    JUDO_FEMININ("Judo (féminin)", "🥋", List.of("JUDO FEMININ")),
    JU_JITSU("", "🥋", List.of("JU-JITSU")),
    KARATE("", "🥋", List.of("KARATE")),
    LUTTE("", "🤼", List.of("LUTTE")),
    LANCERS_ATHLETIQUES("", "☄️", List.of("LANCERS ATHLETIQUES")),
    MOTOCYCLISME("", "🏍️", List.of("MOTOCYCLISME")),
    MULTI_ACTIVITES("", "🤹", List.of("MULTI-ACTIVITÉS", "MULTISPORTS")),
    NAGE_AVEC_PALMES("", "🏊", List.of("NAGE AVEC PALMES")),
    NATATION_ACTIVITES_SPORTIVES_AQUATIQUES("", "🏊", List.of("NATATION ET ACTIV.SPORTIV. AQUATIQUES", "NATATION ET ACTIVITÉS SPORTIVES AQUATIQUES")),
    NATATION("Natation", "🏊", List.of("NATATION")),
    NATATION_SYNCHRONISEE("Natation synchronisée", "👯‍♀️", List.of("NATATION SYNCHRONISEE", "NATATION ARTISTIQUE")),
    PADDLE("", "🏄", List.of("PADDLE")),
    PATINAGE("", "⛸️", List.of("PATINAGE", "PATINAGE SUR GLACE")),
    PATINAGE_ARTISTIQUE("", "⛸️", List.of("PATINAGE ARTISTIQUE")),
    PELOTE_BASQUE("", "⚾", List.of("PELOTE BASQUE")),
    PENTATHLON("", "🏃🔫", List.of("PENTATHLON")),
    PLANCHE_A_VOILE("", "⛵", List.of("PLANCHE À VOILE", "PLANCHE A VOILE")),
    PLONGEON("", "🏊", List.of("PLONGEON")),
    PLONGEE("", "🤿", List.of("PLONGEE")),
    ROLLER("Patinage à roulettes", "🛼", List.of("ROLLER", "ROLLER SKATING")),
    RAID_MULTISPORTS("", "🏃🚴🛶", List.of("RAID MULTISPORTS")),
    RAQUETTE("", "🎾", List.of("RAQUETTE")),
    RUGBY("Rugby à XV", "🏉", List.of("RUGBY", "RUGBY (SANS PRÉCISION)")),
    RUGBY_MIXTE("Rugby (mixte)", "🏉", List.of("RUGBY (MIXTE)")),
    RUGBY_13("Rugby à XIII", "🏉", List.of("RUGBY À 13")),
    RUGBY_F("Rugby (féminin)", "🏉", List.of("RUGBY FEMININ")),
    RUGBY_M("Rugby (masculin)", "🏉", List.of("RUGBY MASCULIN")),
    SAUVETAGE("Sauvetage", "🛟", List.of("SAUVETAGE")),
    SAUVETAGE_COTIER("Sauvetage côtier", "🛟", List.of("SAUVETAGE CÔTIER", "SAUVETAGE AQUATIQUE")),
    SAUVETAGE_SPORTIF("Sauvetage sportif", "🛟", List.of("SAUVETAGE SPORTIF")),
    SHORT_TRACK("", "⛸️", List.of("SHORT TRACK")),
    SKATEBOARD("Skateboard", "🛹", List.of("SKATEBOARD")),
    SKI_ALPIN("Ski alpin", "⛷️", List.of("SKI ALPIN")),
    SKI_BIATHLON("Ski biathlon", "🎿🔫", List.of("SKI BIATHLON")),
    SKI_DE_FOND("Ski de fond", "🎿", List.of("SKI DE FOND", "SKI NORDIQUE DE FOND")),
    SKI_FREESTYLE("Ski acrobatique", "⛷️", List.of("SKI FREESTYLE")),
    SKI_NORDIQUE("Ski nordique", "🎿", List.of("SKI NORDIQUE")),
    SNOWBOARD("", "🏂", List.of("SNOWBOARD")),
    SOFTBALL("", "🥎", List.of("SOFTBALL")),
    SPORTS_DE_GLACE("", "❄️", List.of("SPORTS DE GLACE")),
    SPORTS_INDIVIDUELS("", "👤", List.of("SPORTS INDIVIDUELS")),
    SPORTS_NAUTIQUES("", "🚤", List.of("SPORTS NAUTIQUES")),
    SQUASH("", "🎾", List.of("SQUASH")),
    SURF("", "🏄", List.of("SURF")),
    TAEKWONDO("", "🥋", List.of("TAEKWONDO")),
    TENNIS("Tennis", "🎾", List.of("TENNIS")),
    TENNIS_DE_TABLE("Tennis de table", "🏓", List.of("TENNIS DE TABLE")),
    TIR("", "🔫", List.of("TIR", "TIR SPORTIF")),
    TIR_ARC("", "🏹", List.of("TIR A L'ARC", "TIR À L'ARC")),
    TRAMPOLINE("", "🤸", List.of("TRAMPOLINE")),
    TRIATHLON("Triathlon", "🏊🚴🏃", List.of("TRIATHLON")),
    TUMBLING("", "🤸", List.of("TUMBLING")),
    ULTIMATE("", "🥏", List.of("ULTIMATE")),
    VOILE("", "⛵", List.of("VOILE")),
    VOL_LIBRE("", "🪂", List.of("VOL LIBRE")),
    VOLLEY_BALL("Volley-ball", "🏐", List.of("VOLLEY BALL", "VOLLEY-BALL")),
    VOLTIGE_EQUESTRE("", "🏇", List.of("VOLTIGE ÉQUESTRE")),
    VTT("", "🚵", List.of("VTT")),
    WATER_POLO("Water-polo", "🤽", List.of("WATER POLO", "WATER-POLO"));

    private final String nom;
    private final String emoji;
    private final List<String> code;

    @Nullable
    public static Sport transformation(@NonNull String s) {
        for (Sport sport : Sport.values()) {
            if (sport.getCode().contains(s.trim())) {
                return sport;
            }
        }
        log.info("Pas de sport trouvé pour {}", s.trim());
        return null;
    }

    public String getNom() {
        if (this.nom.isBlank())
            return this.name();
        return this.nom;
    }

    @Getter
    @AllArgsConstructor
    public enum Categorie {
        SS("Sections Sportives"),
        SE("Sport Etudes");

        private final String nom;
    }
}
