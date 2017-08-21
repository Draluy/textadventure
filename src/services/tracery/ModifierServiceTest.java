package services.tracery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by draluy on 17/08/2017.
 */
class ModifierServiceTest {

    @Test
    void processFeminine() {
        Assertions.assertEquals(ModifierService.instance.process("trompeur", new String[]{"f"}), "trompeuse");
        Assertions.assertEquals(ModifierService.instance.process("grand", new String[]{"f"}), "grande");
        Assertions.assertEquals(ModifierService.instance.process("hideux", new String[]{"f"}), "hideuse");
        Assertions.assertEquals(ModifierService.instance.process("spirituel", new String[]{"f"}), "spirituelle");
        Assertions.assertEquals(ModifierService.instance.process("sauvage", new String[]{"f"}), "sauvage");
        Assertions.assertEquals(ModifierService.instance.process("accueillant", new String[]{"f"}), "accueillante");
        Assertions.assertEquals(ModifierService.instance.process("alourdi", new String[]{"f"}), "alourdie");
        Assertions.assertEquals(ModifierService.instance.process("mystérieux", new String[]{"f"}), "mystérieuse");
        Assertions.assertEquals(ModifierService.instance.process("déterminé", new String[]{"f"}), "déterminée");
        Assertions.assertEquals(ModifierService.instance.process("jovial", new String[]{"f"}), "joviale");
        Assertions.assertEquals(ModifierService.instance.process("gai", new String[]{"f"}), "gaie");
    }

    @Test
    void processPlurals() {
        Assertions.assertEquals(ModifierService.instance.process("trompeur", new String[]{"s"}), "trompeurs");
        Assertions.assertEquals(ModifierService.instance.process("grand", new String[]{"s"}), "grands");
        Assertions.assertEquals(ModifierService.instance.process("hideux", new String[]{"s"}), "hideux");
        Assertions.assertEquals(ModifierService.instance.process("spirituel", new String[]{"s"}), "spirituels");
        Assertions.assertEquals(ModifierService.instance.process("sauvage", new String[]{"s"}), "sauvages");
        Assertions.assertEquals(ModifierService.instance.process("accueillant", new String[]{"s"}), "accueillants");
        Assertions.assertEquals(ModifierService.instance.process("alourdi", new String[]{"s"}), "alourdis");
        Assertions.assertEquals(ModifierService.instance.process("mystérieux", new String[]{"s"}), "mystérieux");
        Assertions.assertEquals(ModifierService.instance.process("déterminé", new String[]{"s"}), "déterminés");
        Assertions.assertEquals(ModifierService.instance.process("jovial", new String[]{"s"}), "joviaux");
        Assertions.assertEquals(ModifierService.instance.process("gai", new String[]{"s"}), "gais");
    }

    @Test
    void processFemininePlurals() {
        Assertions.assertEquals(ModifierService.instance.process("trompeur", new String[]{"f", "s"}), "trompeuses");
        Assertions.assertEquals(ModifierService.instance.process("trompeur", new String[]{"s", "f"}), "trompeuses");
        Assertions.assertEquals(ModifierService.instance.process("grand", new String[]{"s", "f"}), "grandes");
        Assertions.assertEquals(ModifierService.instance.process("hideux", new String[]{"s", "f"}), "hideuses");
        Assertions.assertEquals(ModifierService.instance.process("spirituel", new String[]{"s", "f"}), "spirituelles");
        Assertions.assertEquals(ModifierService.instance.process("sauvage", new String[]{"s", "f"}), "sauvages");
        Assertions.assertEquals(ModifierService.instance.process("accueillant", new String[]{"s", "f"}), "accueillantes");
        Assertions.assertEquals(ModifierService.instance.process("alourdi", new String[]{"s", "f"}), "alourdies");
        Assertions.assertEquals(ModifierService.instance.process("mystérieux", new String[]{"s", "f"}), "mystérieuses");
        Assertions.assertEquals(ModifierService.instance.process("déterminé", new String[]{"s", "f"}), "déterminées");
        Assertions.assertEquals(ModifierService.instance.process("jovial", new String[]{"s", "f"}), "joviales");
        Assertions.assertEquals(ModifierService.instance.process("gai", new String[]{"s", "f"}), "gaies");
    }
}