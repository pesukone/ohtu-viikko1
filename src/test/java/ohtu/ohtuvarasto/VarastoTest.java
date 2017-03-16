package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

	@Test
	public void eiVoiLisataYliKapasiteetin() {
		varasto.lisaaVarastoon(Double.MAX_VALUE);
		
		assertEquals(varasto.getSaldo(), varasto.getTilavuus(), vertailuTarkkuus);
	}
	
	@Test
	public void eiVoiLisataNegatiivista() {
		double saldo = varasto.getSaldo();
		varasto.lisaaVarastoon(-15);
		
		assertEquals(varasto.getSaldo(), saldo, vertailuTarkkuus);
	}
	
	@Test
	public void eiVoiOttaaNegatiivista() {
		assertEquals(varasto.otaVarastosta(-10), 0, vertailuTarkkuus);
	}
	
	@Test
	public void luodullaVarastollaOnOikeaSaldoJaTilavuus() {
		Varasto uusi = new Varasto(20);
		Varasto taytetty = new Varasto(20, 10);
		
		assertEquals(uusi.getTilavuus(), 20, vertailuTarkkuus);
		assertEquals(uusi.getSaldo(), 0, vertailuTarkkuus);
		
		assertEquals(taytetty.getTilavuus(), 20, vertailuTarkkuus);
		assertEquals(taytetty.getSaldo(), 10, vertailuTarkkuus);
	}
	
	@Test
	public void virheellinenTilavuusNollataan() {
		Varasto uusi = new Varasto(-30);
		Varasto taytetty = new Varasto(-30, -50);
		
		assertEquals(uusi.getTilavuus(), 0, vertailuTarkkuus);
		assertEquals(taytetty.getTilavuus(), 0, vertailuTarkkuus);
	}
	
	@Test
	public void virheellinenAlkusaldoNollataan() {
		Varasto taytetty = new Varasto(30, -3);
		
		assertEquals(taytetty.getSaldo(), 0, vertailuTarkkuus);
	}
	
	@Test
	public void varastoVoiVuotaaYli() {
		Varasto ylivuoto = new Varasto(30, 50);
		
		assertEquals(ylivuoto.getSaldo(), 30, vertailuTarkkuus);
	}
	
	@Test
	public void varastostaVoiYrittaaOttaaLiikaa() {
		double saldo = varasto.getSaldo();
		
		assertEquals(varasto.otaVarastosta(Double.MAX_VALUE), saldo, vertailuTarkkuus);
	}
	
	@Test
	public void toStringTulostaaOikeassaMuodossa() {
		double saldo = varasto.getSaldo();
		
		assertTrue(varasto.toString().contains(Double.toString(varasto.getSaldo())));
		assertTrue(varasto.toString().contains(Double.toString(varasto.paljonkoMahtuu())));
	}
	
	@Test
	public void kontradiktio() {
		assertTrue(false);
	}
}