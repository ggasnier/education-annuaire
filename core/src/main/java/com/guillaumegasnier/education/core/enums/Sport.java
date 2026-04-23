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

    ACTIVIT_SPORT_AQUATIQUES_PLEINE_NATURE("Activités aquatiques en pleine nature", "🚣", List.of("ACTIVIT. SPORT. AQUATIQUES PLEINE NATURE", "ACTIVITÉS SPORTIVES AQUATIQUES PLEINE NATURE")),
    ACTIVIT_SPORT_TERRESTRES_PLEINE_NATURE("Activités terrestres en pleine nature", "🌲", List.of("ACTIVITES SPORT.TERRESTRES PLEINE NATURE", "ACTIVITÉS PHYSIQUES PLEINE NATURE")),
    TRAIL("Trail", "🌲", List.of("TRAIL", "COURSE EN PLEINE NATURE", "COURSE DE PLEINE NATURE (TRAIL)")),
    RANDO("Randonnée", "🌲", List.of("RANDONNÉE EN MONTAGNE")),
    ACTIVITES_SPORTIVES_DE_MONTAGNE("Activités sportives de montagne", "🏔️", List.of("ACTIVITES SPORTIVES DE MONTAGNE", "ACTIVITÉS SPORTIVES DE MONTAGNE")),
    ACTIVITES_SPORTIVES_SUR_ROLLERS("Activités sportives sur rollers", "🛼", List.of("ACTIVITES SPORTIVES SUR ROLLERS")),
    ACTIVITES_SPORTIVES_SUBAQUATIQUES("Activités sportives subaquatiques", "🤿", List.of("ACTIVITES SPORTIVES SUBAQUATIQUES")),
    ARTS_MARTIAUX_AUTRES("ARTS MARTIAUX (AUTRES QUE LE JUDO)", "🥋", List.of("ARTS MARTIAUX (AUTRES QUE LE JUDO)")),
    ARBITRAGE("Arbitrage", "🏁", List.of("ARBITRAGE")),
    ARBITRAGE_BASKET_BALL("Arbitrage Basket-ball", "🏀🏁", List.of("ARBITRAGE BASKET-BALL", "BASKET (ARBITRE)")),
    ARBITRAGE_FOOTBALL("Arbitrage football", "⚽🏁", List.of("ARBITRAGE FOOTBALL", "FOOTBALL (ARBITRE)")),
    ARBITRAGE_SPORTIF("Arbitrage sportif", "🏁", List.of("ARBITRAGE SPORTIF")),
    ATHLETISME("Athlétisme", "🏃", List.of("ATHLETISME", "ATHLÉTISME")),
    AVIRON("Aviron", "🚣", List.of("AVIRON")),
    VAA("Va'a (pirogue polynésienne)", "🚣", List.of("VA'A (PIROGUE POLYNÉSIENNE)")),
    BADMINTON("Badminton", "🏸", List.of("BADMINTON", "BADMINTON OU BADTEN)", "SPORTS DE RAQUETTE (TENNIS")),
    BASEBALL("Baseball", "⚾", List.of("BASEBALL")),
    BASKET_BALL("Basket-ball", "🏀", List.of("BASKET-BALL", "BASKET")),
    BASKET_BALL_F("Basket-ball (féminin)", "🏀", List.of("BASKET-BALL FÉMININ")),
    BASKET_BALL_M("Basket-ball (masculin)", "🏀", List.of("BASKET-BALL MASCULIN")),
    BEACH_VOLLEY("Beach-volley", "🏐", List.of("BEACH VOLLEY", "VOLLEY SUR SABLE (BEACH VOLLEY)")),
    BMX("BMX", "🚲", List.of("BMX", "BICROSS (BMX)")),
    BOWLING("Bowling", "🎳", List.of("BOWLING")),
    BOXE("Boxe", "🥊", List.of("BOXE", "MULTIBOXE")),
    BOXE_ANGLAISE("Boxe anglaise", "🥊", List.of("BOXE ANGLAISE")),
    BOXE_FRANCAISE("Boxe française", "🥊", List.of("BOXE FRANCAISE", "BOXE FRANÇAISE")),
    BOXE_THAI("Muay-thaï", "🥊", List.of("BOXE THAI", "BOXE THAÏ")),
    KICKBOXING("Kick-boxing", "🥊", List.of("KICKBOXING")),
    PARAPENTE("Parapente", "🥊", List.of("PARAPENTE")),
    BOCCIA("Boccia", "🎯", List.of("BOCCIA")),
    CANOE_KAYAK("Canoë-kayak", "🛶", List.of("CANOE KAYAK", "CANOE ET CANOE KAYAK", "CANOË-KAYAK")),
    CHAR_A_VOILES("Char à voiles", "⛵", List.of("CHAR A VOILES")),
    COURSE_ORIENTATION("Course d'orientation", "🧭", List.of("COURSE D'ORIENTATION")),
    CYCLISME("Cyclisme", "🚴", List.of("CYCLISME")),
    MECANIQUE("Mécanique", "🏎️", List.of("SPORTS MECANIQUE (AUTO-MOTO)")),
    CYCLISME_PISTE("Cyclisme sur piste", "🚴", List.of("CYCLISME PISTE")),
    DANSE("Danse", "💃", List.of("DANSE SPORTIVE", "DANSE", "DANSE CONTEMPORAINE")),
    HIPHOP("Hip-hop", "💃", List.of("HIP-HOP")),
    DISCIPLINES_NORDIQUES("Disciplines nordiques", "🎿", List.of("DISCIPLINES NORDIQUES")),
    EQUITATION("Equitation", "🏇", List.of("EQUITATION", "HIPPOLOGIE ET EQUITATION", "HIPPOLOGIE & EQUITATION", "ÉQUITATION")),
    ESCALADE("Escalade", "🧗", List.of("ESCALADE")),
    MONTAGNE_ESCALADE("Montagne escalade", "🧗", List.of("MONTAGNE ESCALADE")),
    ESCRIME("Escrime", "🤺", List.of("ESCRIME")),
    FOOTBALL("Football", "⚽", List.of("FOOTBALL")),
    FOOTBALL_MIXTE("Football (mixte)", "⚽", List.of("FOOTBALL (MIXTE)")),
    FOOTBALL_AMERICAIN("Football américain", "🏈", List.of("FOOTBALL AMERICAIN", "FOOTBALL AMÉRICAIN")),
    FOOTBALL_F("Football féminin", "⚽", List.of("FOOTBALL FEMININ")),
    FOOTBALL_M("Football", "⚽", List.of("FOOTBALL MASCULIN")),
    FUTSAL("Futsal", "⚽", List.of("FOOTBALL EN SALLE (FUTSAL)", "FUTSAL")),
    FUTSAL_M("Futsal (masculin)", "⚽", List.of("FUTSAL GARÇON")),
    GOLF("Golf", "⛳", List.of("GOLF")),
    BICROSS("Bicross", "🚲", List.of("BICROSS")),
    CIRQUE("Arts du cirque", "🎪", List.of("ARTS DU CIRQUE", "ACTIVITES SPORTIVES DE CIRQUE", "CIRQUE")),
    CROSS_TRAINING("Cross-training", "💪", List.of("CROSS TRAINING")),
    GRS("Gymnastique rythmique et sportive", "🤸", List.of("GRS", "GYMNASTIQUE RYTHMIQUE ET SPORTIVE")),
    GYM_AEROBIC("Gym aérobic", "🤸", List.of("GYM AÉROBIC")),
    GYMNASTIQUE("Gymnastique", "🤸", List.of("GYMNASTIQUE", "GYMNASTIQUE (SANS PRÉCISION)")),
    GYMNASTIQUE_RYTHMIQUE("Gymnastique rythmique", "🤸", List.of("GYMNASTIQUE RYTHMIQUE")),
    GYMNASTIQUE_AEROBIQUE("Gymnastique aérobic", "🤸", List.of("GYMNASTIQUE AEROBIQUE", "GYMNASTIQUE AÉROBIC")),
    GYMNASTIQUE_ARTISTIQUE("Gymnastique artistique", "🤸", List.of("GYMNASTIQUE ARTISTIQUE")),
    GYMNASTIQUE_ACROBATIQUE("Gymnastique acrobatique", "🤸", List.of("GYMNASTIQUE ACROBATIQUE", "ACROSPORT")),
    GYMNASTIQUE_F("Gymnastique (féminine)", "🤸", List.of("GYMNASTIQUE FEMININE")),
    GYMNASTIQUE_M("Gymnastique (masculine)", "🤸", List.of("GYMNASTIQUE MASCULINE")),
    GYMNASTIQUE_FORME("Gymnastique de forme", "🤸", List.of("GYMNASTIQUE DE FORME")),
    HALTEROPHILIE("Haltérophilie", "🏋️", List.of("HALTÉROPHILIE", "HALTÉROPHILIE ET MUSCULATION")),
    HANDBALL("Handball", "🤾", List.of("HANDBALL")),
    HANDBALL_F("Handball (féminin)", "🤾", List.of("HANDBALL FEMININ")),
    HANDBALL_M("Handball (masculin)", "🤾", List.of("HANDBALL MASCULIN")),
    SPORT_PARTAGE("Sport partagé", "♿", List.of("SPORT PARTAGÉ (ENTRE ÉLÈVES VALIDES ET ÉLÈVES EN SITUATION DE HANDICAP)")),
    HANDISPORT("Handisport", "♿", List.of("HANDISPORT")),
    HANDI_ATHLETISME("Athlétisme Handisport", "♿🏃", List.of("HANDI-ATHLÉTISME")),
    HANDI_BASKET("Handi-basket", "♿🏀", List.of("HANDI-BASKET")),
    HANDI_TENNIS_DE_TABLE("Handi-tennis de table", "♿🏓", List.of("HANDI-TENNIS DE TABLE")),
    HOCKEY_SUR_GAZON("Hockey sur gazon", "🏑", List.of("HOCKEY SUR GAZON")),
    HOCKEY_SUR_GLACE("Hockey sur glace", "🏒", List.of("HOCKEY SUR GLACE")),
    JUDO("Judo", "🥋", List.of("JUDO")),
    JUDO_FEMININ("Judo (féminin)", "🥋", List.of("JUDO FEMININ")),
    JU_JITSU("Ju-jitsu", "🥋", List.of("JU-JITSU", "JIU-JITSU")),
    KARATE("Karaté", "🥋", List.of("KARATE", "KARATÉ")),
    LUTTE("Lutte", "🤼", List.of("LUTTE")),
    LANCERS_ATHLETIQUES("Lancers athlétiques", "☄️", List.of("LANCERS ATHLETIQUES")),
    MOTOCYCLISME("Motocyclisme", "🏍️", List.of("MOTOCYCLISME")),
    MULTI_ACTIVITES("Multi-activités", "🤹", List.of("MULTI-ACTIVITÉS", "MULTISPORTS")),
    NAGE_AVEC_PALMES("Nage avec palmes", "🏊", List.of("NAGE AVEC PALMES")),
    NATATION_ACTIVITES_SPORTIVES_AQUATIQUES("Natation et activités sportives aquatiques", "🏊", List.of("NATATION ET ACTIV.SPORTIV. AQUATIQUES", "NATATION ET ACTIVITÉS SPORTIVES AQUATIQUES")),
    NATATION("Natation", "🏊", List.of("NATATION")),
    NATATION_SYNCHRONISEE("Natation synchronisée", "👯‍♀️", List.of("NATATION SYNCHRONISEE", "NATATION ARTISTIQUE")),
    PADDLE("Paddle", "🏄", List.of("PADDLE")),
    PATINAGE("Patinage", "⛸️", List.of("PATINAGE", "PATINAGE SUR GLACE")),
    PATINAGE_ARTISTIQUE("Patinage artistique", "⛸️", List.of("PATINAGE ARTISTIQUE")),
    PELOTE_BASQUE("Pelote basque", "⚾", List.of("PELOTE BASQUE")),
    PENTATHLON("Pentathlon", "🏃", List.of("PENTATHLON")),
    PLANCHE_A_VOILE("Planche à voile", "⛵", List.of("PLANCHE À VOILE", "PLANCHE A VOILE")),
    PLONGEON("Plongeon", "🏊", List.of("PLONGEON")),
    PLONGEE("Plongée", "🤿", List.of("PLONGEE", "PLONGÉE")),
    ROLLER("Roller/Skateboard", "🛼", List.of("ROLLER", "ROLLER SKATING", "ROLLER ET SKATEBOARD", "PLANCHE À ROULETTES (SKATEBOARD)", "QUAD (ROLLER SKATING)")),
    ROLLER_HOCKEY("Roller hockey", "🛼", List.of("ROLLER HOCKEY", "HOCKEY SUR PATINS (ROLLER HOCKEY)")),
    RAID_MULTISPORTS("Raid multisports", "🏃🚴🛶", List.of("RAID MULTISPORTS", "RAID MULTISPORTS DE NATURE", "RAID MULTISPORT")),
    RAQUETTE("Raquette", "🎾", List.of("RAQUETTE")),
    RUGBY("Rugby à XV", "🏉", List.of("RUGBY", "RUGBY (SANS PRÉCISION)")),
    RUGBY_MIXTE("Rugby (mixte)", "🏉", List.of("RUGBY (MIXTE)")),
    RUGBY_13("Rugby à XIII", "🏉", List.of("RUGBY À 13")),
    RUGBY_7("Rugby à VII", "🏉", List.of("RUGBY À 7")),
    RUGBY_F("Rugby (féminin)", "🏉", List.of("RUGBY FEMININ")),
    RUGBY_M("Rugby (masculin)", "🏉", List.of("RUGBY MASCULIN")),
    SAUVETAGE("Sauvetage", "🛟", List.of("SAUVETAGE")),
    SAUVETAGE_COTIER("Sauvetage côtier", "🛟", List.of("SAUVETAGE CÔTIER", "SAUVETAGE AQUATIQUE", "SURF ET SAUVETAGE CÔTIER", "SAUVETAGE CÔTIER SPORTIF")),
    SAUVETAGE_SPORTIF("Sauvetage sportif", "🛟", List.of("SAUVETAGE SPORTIF", "NATATION SPORTIVE ET SAUVETAGE")),
    SHORT_TRACK("Short-track", "⛸️", List.of("SHORT TRACK", "SHORT-TRACK", "PISTE COURTE (SHORT TRACK)")),
    SKATEBOARD("Skateboard", "🛹", List.of("SKATEBOARD")),
    SKI_ALPIN("Ski", "⛷️", List.of("SKI ALPIN", "SKI ET MONTAGNE", "SKI (MULTIDISCIPLINES)", "SKI (SANS PRÉCISION)")),
    SKI_BIATHLON("Ski biathlon", "🎿🔫", List.of("SKI BIATHLON", "BIATHLON")),
    SKI_DE_FOND("Ski de fond", "🎿", List.of("SKI DE FOND", "SKI NORDIQUE DE FOND", "MARCHE NORDIQUE (SKI NORDIQUE DE FOND)")),
    SKI_FREESTYLE("Ski acrobatique", "⛷️", List.of("SKI FREESTYLE")),
    SKI_NORDIQUE("Ski nordique", "🎿", List.of("SKI NORDIQUE")),
    SNOWBOARD("Snowboard", "🏂", List.of("SNOWBOARD", "SURF ALPIN", "SURF DES NEIGES")),
    SOFTBALL("Softball", "🥎", List.of("SOFTBALL")),
    SPORTS_DE_GLACE("Sports de glace", "❄️", List.of("SPORTS DE GLACE")),
    SPORTS_INDIVIDUELS("Sports individuels", "👤", List.of("SPORTS INDIVIDUELS")),
    SPORTS_NAUTIQUES("Sports nautiques", "🚤", List.of("SPORTS NAUTIQUES")),
    SQUASH("Squash", "🎾", List.of("SQUASH")),
    SURF("Surf", "🏄", List.of("SURF")),
    WAKEBOARD("Wakeboard", "🏄", List.of("WAKEBOARD")),
    PADEL("Padel", "🏄", List.of("PADEL")),
    TAEKWONDO("Taekwondo", "🥋", List.of("TAEKWONDO", "TAE KWON DO")),
    TENNIS("Tennis", "🎾", List.of("TENNIS")),
    TENNIS_DE_TABLE("Tennis de table", "🏓", List.of("TENNIS DE TABLE")),
    TENNIS_DE_TABLE_P("Para tennis de table", "🏓", List.of("PARA TENNIS DE TABLE")),
    TIR("Tir", "🔫", List.of("TIR", "TIR SPORTIF")),
    TIR_ARC("Tir à l'arc", "🏹", List.of("TIR A L'ARC", "TIR À L'ARC")),
    TRAMPOLINE("Trampoline", "🤸", List.of("TRAMPOLINE", "GYMNASTIQUE ARTISTIQUE ET TRAMPOLINE")),
    DUATHLON("Duathlon", "🚴🏃", List.of("DUATHLON")),
    TRIATHLON("Triathlon", "🏊🚴🏃", List.of("TRIATHLON", "SPORTS ENCHAÎNÉS")),
    TUMBLING("Tumbling", "🤸", List.of("TUMBLING", "CULBUTE ACROBATIQUE (TUMBLING)")),
    ULTIMATE("Ultimate", "🥏", List.of("ULTIMATE", "ULTIMATE (FRISBEE)", "ULTIME-PASSE (ULTIMATE)")),
    VOILE("Voile", "⛵", List.of("VOILE")),
    VOL_LIBRE("Vol libre", "🪂", List.of("VOL LIBRE")),
    VOLLEY_BALL("Volley-ball", "🏐", List.of("VOLLEY BALL", "VOLLEY-BALL")),
    VOLTIGE_EQUESTRE("Voltige équestre", "🏇", List.of("VOLTIGE ÉQUESTRE")),
    VTT("VTT", "🚵", List.of("VTT", "VTT ET CYCLISME", "VÉLO TOUT-TERRAIN", "VTT-RAID")),
    PECHE("Pêche", "🎣", List.of("PÊCHE")),
    KITESURF("Kitesurf", "🪁", List.of("KITESURF")),
    PROVENCAL("Jeu provençal", "🎯", List.of("PÉTANQUE ET JEU PROVENÇAL", "PETANQUE")),
    ECHECS("Échecs", "♟️", List.of("ÉCHECS", "ECHECS")),
    LONGUE_PAUME("Longue paume", "🎾", List.of("LONGUE PAUME")),
    KARTING("Karting", "🏎️", List.of("KARTING")),
    SPORT_ADAPTE_MULTISPORTS("Sport adapté multisports", "♿", List.of("SPORT ADAPTÉ MULTISPORTS"));

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
        log.warn("Pas de sport trouvé pour {}", s.trim());
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
