package org.endeavourhealth.common.fhir.schema;

import org.endeavourhealth.common.fhir.FhirValueSetUri;

public enum Religion {

    //list sourced from https://www.datadictionary.nhs.uk/data_dictionary/attributes/r/red/religious_or_other_belief_system_affiliation_code_de.asp?shownav=1

    //Baha'i
    BAHAI("A1","Baha'i"),
    //Buddhist
    BUDDHIST("B1", "Buddhist"),
    MAHAYANA_BUDDHIST("B2", "Mahayana Buddhist"),
    NEW_KADAMPA_TRADITION_BUDDHIST("B3", "New Kadampa Tradition Buddhist"),
    NICHIREN_BUDDHIST("B4", "Nichiren Buddhist"),
    PURE_LAND_BUDDHIST("B5", "Pure Land Buddhist"),
    THERAVADA_BUDDHIST("B6", "Theravada Buddhist"),
    TIBETAN_BUDDHIST("B7", "Tibetan Buddhist"),
    ZEN_BUDDHIST("B8", "Zen Buddhist"),
    //Christian
    CHRISTIAN("C1", "Christian"),
    AMISH("C2", "Amish"),
    ANABAPTIST("C3", "Anabaptist"),
    ANGLICAN("C4", "Anglican"),
    APOSTOLIC_PENTECOSTALIST("C5", "Apostolic Pentecostalist"),
    ARMENIAN_CATHOLIC("C6", "Armenian Catholic"),
    ARMENIAN_ORTHODOX("C7", "Armenian Orthodox"),
    BAPTIST("C8", "Baptist"),
    BRETHREN("C9", "Brethren"),
    BULGARIAN_ORTHODOX("C10", "Bulgarian Orthodox"),
    CALVINIST("C11", "Calvinist"),
    CATHOLIC_NOT_ROMAN_CATHOLIC("C12", "Catholic: Not Roman Catholic"),
    CELTIC_CHRISTIAN("C13", "Celtic Christian"),
    CELTIC_ORTHODOX_CHRISTIAN("C14", "Celtic Orthodox Christian"),
    CHINESE_EVANGELICAL_CHRISTIAN("C15", "Chinese Evangelical Christian"),
    CHRISTADELPHIAN("C16", "Christadelphian"),
    CHRISTIAN_EXISTENTIALIST("C17", "Christian Existentialist"),
    CHRISTIAN_HUMANIST("C18", "Christian Humanist"),
    CHRISTIAN_SCIENTISTS("C19", "Christian Scientists"),
    CHRISTIAN_SPIRITUALIST("C20", "Christian Spiritualist"),
    CHURCH_IN_WALES("C21", "Church in Wales"),
    CHURCH_OF_ENGLAND("C22", "Church of England"),
    CHURCH_OF_GOD_OF_PROPHECY("C23", "Church of God of Prophecy"),
    CHURCH_OF_IRELAND("C24", "Church of Ireland"),
    CHURCH_OF_SCOTLAND("C25", "Church of Scotland"),
    CONGREGATIONALIST("C26", "Congregationalist"),
    COPTIC_ORTHODOX("C27", "Coptic Orthodox"),
    EASTERN_CATHOLIC("C28", "Eastern Catholic"),
    EASTERN_ORTHODOX("C29", "Eastern Orthodox"),
    ELIM_PENTECOSTALIST("C30", "Elim Pentecostalist"),
    ETHIOPIAN_ORTHODOX("C31", "Ethiopian Orthodox"),
    EVANGELICAL_CHRISTIAN("C32", "Evangelical Christian"),
    EXCLUSIVE_BRETHREN("C33", "Exclusive Brethren"),
    FREE_CHURCH("C34", "Free Church"),
    FREE_CHURCH_OF_SCOTLAND("C35", "Free Church of Scotland"),
    FREE_EVANGELICAL_PRESBYTERIAN("C36", "Free Evangelical Presbyterian"),
    FREE_METHODIST("C37", "Free Methodist"),
    FREE_PRESBYTERIAN("C38", "Free Presbyterian"),
    FRENCH_PROTESTANT("C39", "French Protestant"),
    GREEK_CATHOLIC("C40", "Greek Catholic"),
    GREEK_ORTHODOX("C41", "Greek Orthodox"),
    INDEPENDENT_METHODIST("C42", "Independent Methodist"),
    INDIAN_ORTHODOX("C43", "Indian Orthodox"),
    JEHOVAHS_WITNESS("C44", "Jehovah's Witness"),
    JUDAIC_CHRISTIAN("C45", "Judaic Christian"),
    LUTHERAN("C46", "Lutheran"),
    MENNONITE("C47", "Mennonite"),
    MESSIANIC_JEW("C48", "Messianic Jew"),
    METHODIST("C49", "Methodist"),
    MORAVIAN("C50", "Moravian"),
    MORMON("C51", "Mormon"),
    NAZARENE_CHURCH("C52", "Nazarene Church"), //Synonym: Nazarene
    NEW_TESTAMENT_PENTACOSTALIST("C53", "New Testament Pentacostalist"),
    NONCONFORMIST("C54", "Nonconformist"),
    OLD_CATHOLIC("C55", "Old Catholic"),
    OPEN_BRETHREN("C56", "Open Brethren"),
    ORTHODOX_CHRISTIAN("C57", "Orthodox Christian"),
    PENTECOSTALIST("C58", "Pentecostalist"), //Synonym: Pentacostal Christian
    PRESBYTERIAN("C59", "Presbyterian"),
    PROTESTANT("C60", "Protestant"),
    PLYMOUTH_BRETHREN("C61", "Plymouth Brethren"),
    QUAKER("C62", "Quaker"),
    RASTAFARI("C63", "Rastafari"),
    REFORMED_CHRISTIAN("C64", "Reformed Christian"),
    REFORMED_PRESBYTERIAN("C65", "Reformed Presbyterian"),
    REFORMED_PROTESTANT("C66", "Reformed Protestant"),
    ROMAN_CATHOLIC("C67", "Roman Catholic"),
    ROMANIAN_ORTHODOX("C68", "Romanian Orthodox"),
    RUSSIAN_ORTHODOX("C69", "Russian Orthodox"),
    SALVATION_ARMY_MEMBER("C70", "Salvation Army Member"),
    SCOTTISH_EPISCOPALIAN("C71", "Scottish Episcopalian"),
    SERBIAN_ORTHODOX("C72", "Serbian Orthodox"),
    SEVENTH_DAY_ADVENTIST("C73", "Seventh Day Adventist"),
    SYRIAN_ORTHODOX("C74", "Syrian Orthodox"),
    UKRAINIAN_CATHOLIC("C75", "Ukrainian Catholic"),
    UKRAINIAN_ORTHODOX("C76", "Ukrainian Orthodox"),
    UNIATE_CATHOLIC("C77", "Uniate Catholic"),
    UNITARIAN("C78", "Unitarian"),
    UNITED_REFORM("C79", "United Reform"),
    ZWINGLIAN("C80", "Zwinglian"),
    //Hindu
    HINDU("D1", "Hindu"),
    ADVAITIN_HINDU("D2", "Advaitin Hindu"),
    ARYA_SAMAJ_HINDU("D3", "Arya Samaj Hindu"),
    SHAKTI_HINDU("D4", "Shakti Hindu"),
    SHIVA_HINDU("D5", "Shiva Hindu"),
    VAISHNAVA_HINDU("D6", "Vaishnava Hindu"), //Synonym: Hare Krishna
    //Jain
    JAIN("E1", "Jain"),
    //Jewish
    JEWISH("F1", "Jewish"),
    ASHKENAZI_JEW("F2", "Ashkenazi Jew"),
    HAREDI_JEW("F3", "Haredi Jew"),
    HASIDIC_JEW("F4", "Hasidic Jew"),
    LIBERAL_JEW("F5", "Liberal Jew"),
    MASORTI_JEW("F6", "Masorti Jew"),
    ORTHODOX_JEW("F7", "Orthodox Jew"),
    REFORM_JEW("F8", "Reform Jew"),
    //Muslim
    MUSLIM("G1", "Muslim"),
    AHMADI("G2", "Ahmadi"),
    DRUZE("G3", "Druze"),
    ISMAILI_MUSLIM("G4", "Ismaili Muslim"),
    SHIITE_MUSLIM("G5", "Shi'ite Muslim"),
    SUNNI_MUSLIM("G6", "Sunni Muslim"),
    //Pagan
    PAGAN("H1", "Pagan"),
    ASATRUAR("H2", "Asatruar"),
    CELTIC_PAGAN("H3", "Celtic Pagan"),
    DRUID("H4", "Druid"),
    GODDESS("H5", "Goddess"),
    HEATHEN("H6", "Heathen"),
    OCCULTIST("H7", "Occultist"),
    SHAMAN("H8", "Shaman"),
    WICCAN("H9", "Wiccan"),
    //Sikh
    SIKH("I1", "Sikh"),
    //Zoroastrian
    ZOROASTRIAN("J1", "Zoroastrian"),
    //Other
    AGNOSTIC("K1", "Agnostic"),
    ANCESTRAL_WORSHIP("K2", "Ancestral Worship"),
    ANIMIST("K3", "Animist"),
    ANTHROPOSOPHIST("K4", "Anthroposophist"),
    BLACK_MAGIC("K5", "Black Magic"),
    BRAHMA_KUMARI("K6", "Brahma Kumari"),
    BRITISH_ISRAELITE("K7", "British Israelite"),
    CHONDOGYO("K8", "Chondogyo"),
    CONFUCIANIST("K9", "Confucianist"),
    DEIST("K10", "Deist"),
    HUMANIST("K11", "Humanist"),
    INFINITE_WAY("K12", "Infinite Way"),
    KABBALIST("K13", "Kabbalist"),
    LIGHTWORKER("K14", "Lightworker"),
    NEW_AGE_PRACTITIONER("K15", "New Age Practitioner"),
    NATIVE_AMERICAN_RELIGION("K16", "Native American Religion"),
    PANTHEIST("K17", "Pantheist"),
    PEYOTIST("K18", "Peyotist"),
    RADHA_SOAMI("K19", "Radha Soami"), //Synonym: Sant Mat
    RELIGION_OTHER("K20", "Religion (Other Not Listed)"),
    SANTERI("K21", "Santeri"),
    SATANIST("K22", "Satanist"),
    SCIENTOLOGIST("K23", "Scientologist"),
    SECULARIST("K24", "Secularist"),
    SHUMEI("K25", "Shumei"),
    SHINTO("K26", "Shinto"),
    SPIRITUALIST("K27", "Spiritualist"),
    SWEDENBORGIAN("K28", "Swedenborgian"), //Synonym: Neo-Christian
    TAOIST("K29", "Taoist"),
    UNITARIAN_UNIVERSALIST("K30", "Unitarian-Universalist"),
    UNIVERSALIST("K31", "Universalist"),
    VODUN("K32", "Vodun"),
    YORUBA("k33", "Yoruba"),
    //None
    ATHEIST("L1", "Atheist"),
    NOT_RELIGIOUS("L2", "Not Religious"),
    //Declines to Disclose
    RELIGION_NOT_GIVEN("M1", "Religion not given - PATIENT refused"),
    //Unknown
    PATIENT_RELIGION_UNKNOWN("N1", "Patient Religion Unknown");


    private String code = null;
    private String description = null;

    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public String getSystem() {
        return FhirValueSetUri.VALUE_SET_RELIGIONS;
    }

    Religion(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Religion fromCode(String v) {
        for (Religion c: Religion.values()) {
            if (c.code.equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown code [" + v + "]");
    }
}
