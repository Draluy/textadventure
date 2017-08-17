package services.tracery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by draluy on 17/08/2017.
 */
class ModifierServiceTest {

    @Test
    void process() {
        Assertions.assertEquals(ModifierService.instance.process("trompeur", "f"), "trompeuse");
        Assertions.assertEquals(ModifierService.instance.process("grand", "f"), "grande");
        Assertions.assertEquals(ModifierService.instance.process("hideux", "f"), "hideuse");
        Assertions.assertEquals(ModifierService.instance.process("spirituel", "f"), "spirituelle");
        Assertions.assertEquals(ModifierService.instance.process("sauvage", "f"), "sauvage");
        Assertions.assertEquals(ModifierService.instance.process("accueillant", "f"), "accueillante");
        Assertions.assertEquals(ModifierService.instance.process("alourdi", "f"), "alourdie");
        Assertions.assertEquals(ModifierService.instance.process("mystérieux", "f"), "mystérieuse");
        Assertions.assertEquals(ModifierService.instance.process("déterminé", "f"), "déterminée");
        Assertions.assertEquals(ModifierService.instance.process("jovial", "f"), "joviale");
        Assertions.assertEquals(ModifierService.instance.process("gai", "f"), "gaie");
    }

}